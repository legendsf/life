/*
   vscode golang 安装失败
   https://www.cnblogs.com/java-le/p/13030197.html

 * @Author: your name
 * @Date: 2020-07-31 19:26:54
 * @LastEditTime: 2020-08-03 15:35:57
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /hgo/Users/songfei/data/code/life/code/bones/go/godemo/main.go
 */

package main

import (
	"fmt"
	"godemo/config"

	"github.com/jinzhu/configor"
)

func main() {
	fmt.Println("使用外部包测试：", configor.Config{})
	config.LoadConfig()

}
