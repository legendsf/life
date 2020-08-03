package controllers

import (
	"beegodemo01/models"
	"fmt"
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/logs"
)

type ArticleController struct {
	beego.Controller
}

func (c *ArticleController) Get() {
	c.Ctx.WriteString("新闻列表")
}

func (c *ArticleController) Add() {
	goods:=models.Goods{
		Id: "id1",
		Name: "name1",
	}
	c.Data["json"]=	goods
	c.ServeJSON()
}

func (c *ArticleController) Edit()  {
	//id :=c.GetString("id")
	id,err :=c.GetInt("id")
	if err!=nil{
		fmt.Println(err)
		return
	}
	logs.Info(id)
	c.Ctx.WriteString("修改新闻"+fmt.Sprintf("%v",id))
}
