package models

type Goods struct {
	Id string `json:"id" longValue:"goodsId"`
	Name string `json:"goodsName" form:"goodsName2"`
}
