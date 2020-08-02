package controllers

import (
	"github.com/astaxie/beego"
)

type CmsController struct {
	beego.Controller
}

func (c *CmsController) GetByCmsId() {
	id :=c.Ctx.Input.Param(":id")
	c.Ctx.WriteString("api接口---"+id)
}

