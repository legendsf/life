package util

import (
	"fmt"
	_ "github.com/go-sql-driver/mysql"
	"github.com/xormplus/xorm"
)

var engine *xorm.Engine

func main() {
	var err error
	engine,err =xorm.NewEngine("mysql","root:123456@tcp(127.0.0.1:3306)/test?charset=utf8mb4")
	if err!=nil{
		fmt.Println("数据库连接错误:",err)
	}
}