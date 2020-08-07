package redis

import (
	"fmt"
	"github.com/garyburd/redigo/redis"
)

var Redis redis.Conn

func init() {
	var err error
	Redis,err = redis.Dial("tcp","127.0.0.1:6379")
	if err != nil{
		fmt.Println("连接 redis 失败",err)
		return
	}
}
