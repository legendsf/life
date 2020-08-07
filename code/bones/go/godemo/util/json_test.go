package util

import (
	"fmt"
	"github.com/buger/jsonparser"
	"github.com/tidwall/gjson"
	"testing"
)
func TestJson(test *testing.T)  {
	const json = `{"name":{"first":"song","last":"fei"},"age":47}`
	value :=gjson.Get(json,"name.last")
	fmt.Println("value",value)
}

func TestJson2(test *testing.T)  {
	const json = `{"name":{"first":"Janet","last":"Prichard"},"age":47}`

	value :=gjson.Get(json,"name.last")
	fmt.Println("value",value)
}

func TestJsonParser(test *testing.T)  {
	data :=[]byte(`{
		"name":{"first":"song","last":"fei"},
		"age":47
	}`)
	result,err :=jsonparser.GetString(data,"name","last")
	if err !=nil{
		fmt.Println("err",err)
	}else {
		fmt.Println("result",result)
	}

	content,valueType,offset,err := jsonparser.Get(data,"name","last")
	if err !=nil{
		fmt.Println("err",err)
	}else {
		fmt.Println(content,valueType,offset)
	}
	result1,err := jsonparser.ParseString(content)
	if err !=nil{
		fmt.Println("err",err)
	}else {
		fmt.Println(result1)
	}

	err3 := jsonparser.ObjectEach(data, func(key []byte, value []byte, dataType jsonparser.ValueType, offset int) error {
		fmt.Printf("key %s value %s type %s ",string(key),string(value),dataType)
		return nil
	},"name")
	if err3 != nil{
		fmt.Println("err3",err3)
	}

}

func TestJsonparser2(test *testing.T)  {
	data := []byte(`{
  "person": {
    "name":{
      "first": "Leonid",
      "last": "Bugaev",
      "fullName": "Leonid Bugaev"
    },
    "github": {
      "handle": "buger",
      "followers": 109
    },
    "avatars": [
      { "url": "https://avatars1.githubusercontent.com/u/14009?v=3&s=460", "type": "thumbnail" }
    ]
  },
  "company": {
    "name": "Acme"
  }
}`)

	result, err := jsonparser.GetString(data, "person", "name", "fullName")
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(result)

	content, valueType, offset, err := jsonparser.Get(data, "person", "name", "fullName")
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(content, valueType, offset)
	//jsonparser提供了解析bool、string、float64以及int64类型的方法，至于其他类型，我们可以通过valueType类型来自己进行转化
	result1, err := jsonparser.ParseString(content)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(result1)

	err = jsonparser.ObjectEach(data, func(key []byte, value []byte, dataType jsonparser.ValueType, offset int) error {
		fmt.Printf("key:%s\n value:%s\n Type:%s\n", string(key), string(value), dataType)
		return nil
	}, "person", "name")

}
