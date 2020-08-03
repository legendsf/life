package models

import (
	"encoding/xml"
	"fmt"
	"testing"
)

func TestGoodsXml(test *testing.T)  {
	msg :="<Goods>\n    <Id>id1</Id>\n    <Name>name1</Name>\n</Goods>"
	goods := Goods{}
	err := xml.Unmarshal([]byte(msg),&goods)
	if err!=nil{
		fmt.Println(err)
	}
	fmt.Println(goods)
}
