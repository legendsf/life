package redis

import (
	"fmt"
	"github.com/garyburd/redigo/redis"
	"godemo/biz/model"
	"testing"
)

func TestReis(test *testing.T)  {
	var c=Redis
	_,err:=c.Do("SET","mkey","mvalue")
	if err !=nil{
		fmt.Println("redis set failed",err)
	}
	username,err1 := redis.String(c.Do("GET","mkey"))
	if err1 != nil{
		fmt.Println(err1)
	}else {
		fmt.Println("Get mkey:",username)
	}
	//json
	persson := model.Person{1,"songfei"}


}
