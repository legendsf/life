package com.sf.jkt.j.spring.biz.exception.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "用户对象",description = "这是用户对象")
public class User {
    @NotNull(message = "用户ID不能为空")
            @ApiModelProperty(hidden = true)
   public Long id;
    @NotNull(message = "用户message不能为空")
            @Size(min = 6,max = 11,message = "账号长度必须为6-11个字符")
            @ApiModelProperty(value = "账户",name = "account",example = "imoocuser",required = true)
   public String account;
    @NotNull(message = "用户密码不能为空")
            @Size(min = 6,max = 11,message = "密码必须为 6-11个字符")
            @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
  public   String password;
    @NotNull(message = "用户邮箱不能为空")
            @Email(message = "邮箱格式不正确")
            @ApiModelProperty(value = "邮箱",name="email",example = "623667783@qq.com",required = true)
  public   String email;
}
