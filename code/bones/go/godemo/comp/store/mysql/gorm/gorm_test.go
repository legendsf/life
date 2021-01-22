package gorm

import (
	"fmt"
	"github.com/jinzhu/gorm"
	"testing"
)

type UserInfo struct {
	gorm.Model
	Name string
	Gender string
	Hobby string
	Email string `gorm:"type:varchar(100)"`
	Num int `gorm:"AUTO_INCREMENT"`
	IgnoredMe string `gorm:_`
}

func TestGorm(test *testing.T)  {
	Gdb.AutoMigrate(&UserInfo{})
	u1 := UserInfo{Name: "七米",Gender: "男",Hobby: "篮球"}
	u2 := UserInfo{Name: "沙河娜扎",Gender: "女",Hobby: "足球"}
	Gdb.Create(&u1)
	Gdb.Create(&u2)
	var u= new (UserInfo)
	Gdb.First(u)
	fmt.Println("查询到的 u",u)
	var uu UserInfo
	Gdb.Find(&uu,"hobby=?","足球")
	fmt.Println("uu",uu)
	Gdb.Model(&u).Update("hobby","双色球")
	Gdb.Delete(&u)
}









