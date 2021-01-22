package model

import (
	"time"
)

type UserTb struct {
	Id      int       `xorm:"not null pk autoincr INT"`
	Name    string    `xorm:"default '' comment('姓名') VARCHAR(50)"`
	Age     int       `xorm:"default 0 comment('年龄') INT"`
	Sex     int       `xorm:"default 0 comment('性别') INT"`
	Addtime time.Time `xorm:"DATETIME"`
}
