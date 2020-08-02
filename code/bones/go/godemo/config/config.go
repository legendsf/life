/*
 * @Author: your name
 * @Date: 2020-07-31 19:45:03
 * @LastEditTime: 2020-07-31 19:45:04
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /hgo/Users/songfei/data/code/life/code/bones/go/godemo/config/config.go
 */ 

 package config

import (
	"fmt"
)

var Message = "hello world";
 
func LoadConfig(){
	fmt.Println(Message)
	fmt.Println("hello loadConfig")
} 

func loadConfig1(){
	fmt.Println("hello loadConfig1")
}