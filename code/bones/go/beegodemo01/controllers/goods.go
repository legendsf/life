package controllers

import (
	"beegodemo01/models"
	"crypto/md5"
	"encoding/xml"
	"fmt"
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/logs"
	"io"
	"time"
)

type GoodsController struct {
	beego.Controller
}

func (c *GoodsController) Get() {
	c.Data["title"]="商品 title"
	goods := models.Goods{
		Id: "id1",
		Name: "name1",
	}
	c.Data["goods"]=goods
	goodsList :=[]models.Goods{
		goods,
		{
			Id: "id2",
			Name: "Name2",
		},
	}
	c.Data["goodsList"]=goodsList
	c.Data["strList"]=[]string{"java","php","golang"}
	goodsMap :=map[string]string{
		"k1":"v1",
		"k2":"v2",
	}
	c.Data["goodsMap"]=goodsMap

	c.Data["isHome"]=false
	c.Data["isSchool"]=true
	c.Data["n1"]=5
	c.Data["n2"]=3
	c.Data["now"]=time.Now()
	c.Data["unix"]=time.Now().Unix()
	c.Data["mtime"]=models.Unix2Date(time.Now().Unix())
	c.Data["html"]="<h2>这是一个后台渲染的h2</h2>"
	port:=beego.AppConfig.String("prod::httpport")
	c.Data["port"]=port
	c.Data["k1sf"]=beego.AppConfig.String("k1sf")
	hmd5 :=md5.New()
	io.WriteString(hmd5,"thanks")
	io.WriteString(hmd5,"thankl")
	fmt.Printf("%x",hmd5.Sum(nil))
	c.Ctx.SetCookie("username","admin",1000)
	//路径访问 cookie
	//c.Ctx.SetCookie("user","admin",10,"/goods")
	//子域共享 cookie
	//c.Ctx.SetCookie("user","admin",10,"/",".itying.com")

	c.SetSession("sessname","sess")
	c.Data["sessname"]=c.GetSession("sessname")
	//c.Data["sessname"]="good"
	c.Data["username"]= c.Ctx.GetCookie("username")
		c.TplName = "goods.tpl"
	//c.TplName="apiRedirect.html"
}

func (c *GoodsController) RedirectGoods() {
	c.TplName="apiRedirect.html"
}

func (c *GoodsController) GetGoodsInfo() {
	goods := models.Goods{
		Id: "id1",
		Name: "name1",
	}
	c.Data["json"]=goods
	c.ServeJSON()
}

func (c *GoodsController) GetGoodsXml() {
	goods := models.Goods{
		Id: "id1",
		Name: "name1",
	}
	c.Data["xml"]=goods
	c.ServeXML()
}


func (c *GoodsController) Xml() {
	reqBytes :=c.Ctx.Input.RequestBody
	reqStr :=string(reqBytes)
	logs.Info(reqStr)
	reqStr ="<Goods>\n    <Id>id1</Id>\n    <Name>name1</Name>\n</Goods>"
	logs.Info(reqStr)
	goods :=models.Goods{}
	var err error
	if err=xml.Unmarshal(reqBytes,&goods);err!=nil{
		c.Data["json"]=err.Error()
	}else {
		c.Data["json"]=goods;
	}
	c.ServeJSON()
	//c.Ctx.WriteString(reqStr)
}