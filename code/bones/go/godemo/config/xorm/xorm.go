package xorm

import (
	"fmt"
	_ "github.com/go-sql-driver/mysql"
	"github.com/xormplus/xorm"
	"github.com/xormplus/xorm/log"
)

var Db *xorm.Engine

func init() {
	var err error
	Db,err = xorm.NewEngine("mysql",
		"root:123456@tcp(127.0.0.1:3306)/test?charset=utf8mb4")
	if err!=nil{
		fmt.Println("数据库创建失败",)
		return
	}
	err=Db.Ping()
	if err!=nil{
		fmt.Println("数据库连接成功")
	}
	//打印日志
	Db.ShowSQL(true)
	Db.Logger().SetLevel(log.LogLevel(0))
	registerSqlMap()
	registerSqlTemplate()
	//自动监控文件更新
	Db.StartFSWatcher()
}

func registerSqlMap()  {
	fmt.Println("初始化 sqlMapclient")
	err :=Db.RegisterSqlMap(xorm.Json("./sql/mysql",".json"))
	if err !=nil{fmt.Println(err)}
	err = Db.RegisterSqlMap(xorm.Xml("./sql/mysql",".xml"))
	if err !=nil{fmt.Println(err)}
}

func registerSqlTemplate()  {
	fmt.Println("初始化 sqlTemplate")
	err := Db.RegisterSqlTemplate(xorm.Pongo2("./sql/mysql", ".stpl"))
	err = Db.RegisterSqlTemplate(xorm.Default("./sql/mysql", ".tpl"))
	if err !=nil{
		fmt.Println("注册 sqlTemplate 失败",err)
	}
}







