package xorm

import (
	"database/sql"
	"fmt"
	"godemo/config/model"
	"testing"
	"time"
)

/** 从数据库生成 model
  ./xorm reverse mysql "root:123456@tcp(127.0.0.1:3306)/test?charset=utf8mb4" templates/goxorm ~/tmp/gomodel
*/
func testFromDbToModel()  {

}

func TestMysql(test *testing.T)  {
	person := model.Person{
		Name: "name1",
	}
	fmt.Println(person)
	if err := Db.Ping();err !=nil{
		fmt.Println(err)
		return
	}
	defer Db.Close()
	fmt.Println("数据库连接成功")
	mmap,_:=Db.QueryString("select * from doctor_tb where name='钟南山'")
	fmt.Println(mmap)

	//新增
	doc3 := model.DoctorTb{
		2,"钟南山",48,1,time.Now(),
	}
	i3,_ := Db.InsertOne(doc3)
	doc3.Id=1
	doc3.Name="wangyisheng"
	Db.InsertOne(doc3)
	fmt.Println("新结果",i3)

	var doc model.DoctorTb
	b,_ := Db.Where("name=?","songfei").Get(&doc)
	if b{
		fmt.Println(doc)
	}else {
		fmt.Println("数据不存在")
	}

	doc4 :=model.DoctorTb{Name: "songfei"}
	b,_=Db.Get(&doc4)
	fmt.Println("doc4",doc4)
	//查询列表
	docList := make([]model.DoctorTb,0)
	Db.Where("age > ? or name like ? ",40, "%song%").Find(&docList)
	fmt.Println("docList",docList)
	docList2 := make([]model.DoctorTb,0)
	Db.Where("age > ?",40).Or("name like ?","%song%").Find(&docList2)
	fmt.Println("docList2",docList2)

	//查询分页
	docList3 := make([]model.DoctorTb,0)
	page := 0
	pageSize :=2
	limit :=pageSize
	start := page * pageSize
	totalCount,_ := Db.Where("age > ? or name like ?",40 ,"%song%") .Limit(limit,start).FindAndCount(&docList3)
	fmt.Println("总记录数",totalCount,"docList3",docList3)
	//直接用 sql 查询
	docList4 := make([]model.DoctorTb,0)
	Db.SQL("select * from doctor_tb where age > ?",40).Find(&docList4)
	fmt.Println("docList4",docList4)
	//指定表名查询
	doc6 := model.DoctorTb{Id: 3}
	Db.Table("doctor_tb").Get(&doc6)
	fmt.Println("doc6",doc6)
	//修改
	doc5 := model.DoctorTb{Name: "钟医生"}
	//新增
	isOk,_:=Db.InsertOne(&model.Person{Name: "songfei2", })
	fmt.Println("isOk",isOk)
	//删除
	isOk2,_:=Db.Delete(&model.Person{Name: "songfei2"})
	fmt.Println("isOk2",isOk2)
	//删除2
	isOk3,_:=Db.InsertOne(&model.Person{Name: "songfei3" })
	fmt.Println("isOk3",isOk3)
	result,_:=Db.Exec("delete from person where Name=?","songfei3")
	lastId,_ := result.LastInsertId()
	rowAffect,_ :=result.RowsAffected()
	fmt.Println("result",result,lastId,rowAffect)
	//修改
	iUpdate,_ :=Db.ID(1).Update(&doc5)
	fmt.Println("更新结果",iUpdate)
	//事务
	session := Db.NewSession()
	defer  session.Close()
	session.Begin()
	var result4 sql.Result
	result4,err4 := session.Exec("delete from doctor_tb where Id=?",6)
	if err4 !=nil{
		session.Rollback()
		return
	}
	if af4,_:=result4.RowsAffected();af4!=1{
		fmt.Println("删除失败")
		session.Rollback()
		return
	}
	fmt.Println(result4.RowsAffected())
	result4,err4 =session.Exec("delete from doctor_tb where Id=?",10)
	if err4 !=nil{
		session.Rollback()
		return
	}
	if af4,_:=result4.RowsAffected();af4!=1{
		fmt.Println("删除失败")
		session.Rollback()
		return
	}
	err4 = session.Commit()
	if err4 != nil{
		return
	}
	fmt.Println("事务执行成功")

}
/**
	sqlMap bug ?1 这种不识别，只识别 id=? id in (?,?)

 */
func TestSqlMap(test *testing.T)  {
	//sqlmap 测试
	mm2:=Db.GetSqlMap("json_selectDoctorById")
	fmt.Println(mm2)
	var doc1  []model.DoctorTb
	Db.SqlMapClient("json_selectDoctorById",2).Find(&doc1)
	Db.SqlMapClient("json_selectDoctorById",1).Find(&doc1)
	fmt.Println("doc1",doc1)
	var doc7 model.DoctorTb
	Db.SqlMapClient("json_selectDoctorById",2).Get(&doc7)
	fmt.Println("doc7",doc7)
	paraMap := map[string]interface{}{"id":2}
	var doc2 model.DoctorTb
	Db.SqlMapClient("xml_selectDoctorById",2).Get(&doc2)
	fmt.Println("doc2",doc2)
	list, _ :=Db.SqlMapClient("xml_selectDoctorByIds",1,2).Query().List()
	fmt.Println("list",list)
	var doc6 model.DoctorTb
	paraMap["Id"]=2
	fmt.Println(paraMap)
	Db.SqlMapClient("xml_selectDoctorByIdOne",&paraMap).Get(&doc6)
	fmt.Println("doc6",doc6)
	var doc3 model.DoctorTb
	Db.SQL("select * from doctor_tb where id=?id",&paraMap).Get(&doc3)
	fmt.Println("doc3",doc3)
	Db.SQL("select * from doctor_tb where id=?",2).Get(&doc3)
	fmt.Println("doc3",doc3)

}

func TestSqlTemplate(test *testing.T)  {
	var person model.Person
	paraMap :=map[string]interface{}{"id":5,"name":"songfei2"}
	Db.SqlTemplateClient("person.tpl",&paraMap).Get(&person)
	fmt.Println(person)
	var doc1 model.DoctorTb
	// xorm pongo2 有 bug 不好用
	paraMap1 := map[string]interface{}{"id":2,"name":"songfei2"}
	Db.SqlTemplateClient("doctor.stpl",&paraMap1).Get(&doc1)
	fmt.Println(doc1)
}

func TestPongoTemplate(test *testing.T)  {
	sql_key_4_1 := "select.example.stpl" //模板文件名,SqlTemplate的key

	//执行的 sql：select * from user where id=7
	//如部分参数未使用，请记得使用对应类型0值，如此处name参数值为空字符串，模板使用指南请详见pongo2
	paramMap_4_1 := map[string]interface{}{"count": 2, "id": 7, "name": ""}
	results, err := Db.SqlTemplateClient(sql_key_4_1, &paramMap_4_1).Query().List()

	//执行的 sql：select * from user where name='xormplus'
	//如部分参数未使用，请记得使用对应类型0值，如此处id参数值为0，模板使用指南请详见pongo2
	paramMap_4_2 := map[string]interface{}{"id": 0, "count": 0, "name": "xormplus"}
	results, err = Db.SqlTemplateClient(sql_key_4_1, &paramMap_4_2).Query().List()
	fmt.Println(results,err)

	//执行的 sql：select * from user where name='xormplus'
	//sql_key_7_1 := "select.example.stpl" //配置文件名,SqlTemplate的key
	//var users []User
	//paramMap_7_1 := map[string]interface{}{"id": 0, "count": 0, "name": "xormplus"}
	//err := engine.SqlTemplateClient(sql_key_7_1, &paramMap_7_1).Find(&users)

}