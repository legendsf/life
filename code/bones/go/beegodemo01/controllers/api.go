package controllers

import (
	"github.com/astaxie/beego"
)

type ApiController struct {
	beego.Controller
}

func (c *ApiController) Get() {
	//id :=c.Ctx.Input.Param(":id")
	//c.Ctx.WriteString("api接口---")
	c.Redirect("/user/getUser",302)
}

func (c *ApiController) mRedirect() {
	c.TplName="apiRedirect.html"
}

func (c *ApiController) GetById() {
	id :=c.Ctx.Input.Param(":id")
	c.Ctx.WriteString("api接口---"+id)
}
func (c *ApiController) GetByCmsId() {
	id :=c.Ctx.Input.Param(":id")
	c.Ctx.WriteString("api接口---"+id)
}

