package gorm

import (
	"fmt"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

var Gdb *gorm.DB

func init() {
	var err error
	Gdb,err=gorm.Open("mysql","root:123456@tcp(127.0.0.1:3306)/test?charset=utf8mb4&parseTime=True&loc=Local")
	if err != nil{
		fmt.Println("数据库连接错误",err)
	}
}
