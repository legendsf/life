package com.sf.jkt.j.spring.biz.exception.bad;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "用户相关业务的接口",tags = {"视频相关的 controller"})
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ApiOperation(value = "欢迎",notes = "欢迎界面")
    @ApiImplicitParam(name = "msg",value = "欢迎的 message",required = true,dataType = "String",paramType = "form")
    public String hello(String msg){
        return  "Hello: "+msg;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user, BindingResult bindingResult){
        for(ObjectError error: bindingResult.getAllErrors()){
            return error.getDefaultMessage();
        }
        return userService.addUser(user);
    }
}
