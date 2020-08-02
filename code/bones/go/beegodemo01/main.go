package main

import (
	"beegodemo01/models"
	_ "beegodemo01/routers"
	"github.com/astaxie/beego"
	_ "github.com/astaxie/beego/session/redis"
)

func hello(in string)(out string)  {
	out = in+" world"
	return out
}

func main() {
	beego.BConfig.WebConfig.Session.SessionProvider="redis"
	beego.BConfig.WebConfig.Session.SessionProviderConfig="127.0.0.1:6379"
	//大小写路由
	//beego.BConfig.RouterCaseSensitive=false
	//beego.BConfig.AppName="beego"
	//加载自定义配置文件 ini yml xml json
	//beego.LoadAppConfig("ini","conf/app2.conf")
	beego.AppConfig.Set("k1sf","v1sf")
	beego.AddFuncMap("hi",hello)
	beego.AddFuncMap("unix2date",models.Unix2Date)
	//http://localhost:8080/down/download.txt
	beego.SetStaticPath("/down","download")
	//beego.SetStaticPath("/down1","download1")
	beego.Run()
}

