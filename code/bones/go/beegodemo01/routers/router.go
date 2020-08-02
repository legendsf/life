package routers

import (
	"beegodemo01/controllers"
	"github.com/astaxie/beego"
)

func init() {
    beego.Router("/", &controllers.MainController{})
    beego.Router("/goods",&controllers.GoodsController{})
	beego.Router("/goods/mredirect",&controllers.GoodsController{},"get:RedirectGoods")
	beego.Router("/goods/xml",&controllers.GoodsController{},"post:Xml")
	beego.Router("/goods/getGoodsXml",&controllers.GoodsController{},"Get:GetGoodsXml")
	beego.Router("/article",&controllers.ArticleController{})
	beego.Router("/article/add",&controllers.ArticleController{},"Get:Add")
	beego.Router("/article/edit",&controllers.ArticleController{},"Get:Edit")
	beego.Router("/user", &controllers.UserController{})
	beego.Router("/user/getUser", &controllers.UserController{},"Get:GetUser")
	beego.Router("/user/doAdd",&controllers.UserController{},"Post:DoAdd")
    //下面两个 api 的有冲突
    //测试重定向
	beego.Router("/api/",&controllers.ApiController{},"get:Get")
	beego.Router("/api/redirect",&controllers.ApiController{},"get:Redirect")
	beego.Router("/api/:id",&controllers.ApiController{},"get:GetById")
	beego.Router("/api/cms_:id([0-9]+).html",&controllers.CmsController{},"get:GetByCmsId")
	beego.Router("/cms/cms_:id([0-9]+).html",&controllers.CmsController{},"get:GetByCmsId")

}
