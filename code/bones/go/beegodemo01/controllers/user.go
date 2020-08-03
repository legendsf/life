package controllers

import (
	"beegodemo01/models"
	"github.com/astaxie/beego"
)

type UserController struct {
	beego.Controller
}
func (c *UserController) Get() {
	c.Data["username"]=c.Ctx.GetCookie("username")
	c.Data["sessname"]=c.GetSession("sessname")
	c.TplName="user.html"
}

func (c *UserController) DoAdd() {
	userName := c.GetString("username")
	password := c.GetString("password")
	c.Ctx.WriteString("用户中心"+userName+password)
}

func (c *UserController) GetUser() {
	goods := models.Goods{
		Id: "GOODSID",
		Name: "GoodsName",
	}
	c.Data["json"]=goods
	c.ServeJSON()
}
