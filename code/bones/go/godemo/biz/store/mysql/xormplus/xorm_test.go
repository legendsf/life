package xormplus

import (
	"fmt"
	_ "github.com/go-sql-driver/mysql"
	"github.com/xormplus/xorm"
	"testing"
)

func db() *xorm.Engine  {
	engine,err := xorm.NewEngine("mysql","root:123456@tcp(127.0.0.1:3306)/test?charset=utf8mb4")
	if err!=nil{
		fmt.Println("连接数据库失败",err)
	}
	return engine
}

func TestXorm(test *testing.T)  {
	var engine=db()
	//查询
	//results,err :=engine.QueryBytes("select * from person")
	results,err :=engine.QueryString("select * from person")
	if err!=nil{
		fmt.Println(err)
	}
	fmt.Println(results)
	for k,v := range results{
		fmt.Println(k)//index
		fmt.Println(v)
		id:=string(v["id"])
		name:=string(v["name"])
		fmt.Println(id,name)
	}
	//新增

	//修改
	//删除
}