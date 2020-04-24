package main

import (
	"fmt"
	"github.com/golang/protobuf/proto"
	"github.com/jinzhu/configor"
	"hgo2/mpackage"
	"hgo2/proto/test/user"
	"io/ioutil"
	"os"
)

func main() {
	fmt.Println("使用外部测试:", configor.Config{})
	mpackage.New()
	write()
	read()
}

func write() {
	p1 := &user.Person{
		Id:   1,
		Name: "小张",
		Phones: []*user.Phone{
			{Type: user.PhoneType_HOME, Number: "111"},
			{Type: user.PhoneType_WORK, Number: "222"},
		},
	}
	p2:=&user.Person{
		Id:2,
		Name: "小王",
		Phones: []* user.Phone{
			{Type: user.PhoneType_HOME,Number: "3333"},
			{Type: user.PhoneType_HOME,Number: "4444"},
		},
	}

	book:= &user.ContactBook{};
	book.Persons=append(book.Persons,p1)
	book.Persons=append(book.Persons,p2)

	data,_:=proto.Marshal(book)
	ioutil.WriteFile("./test.txt",data,os.ModePerm)

}

func read()  {
	data,_:=ioutil.ReadFile("./test.txt")
	book:=&user.ContactBook{}
	proto.Unmarshal(data,book)
	for _,v:=range book.Persons{
		fmt.Println(v.Id,v.Name)
		for _,vv:=range v.Phones {
			fmt.Println(vv.Type,vv.Number)
		}
	}
}

