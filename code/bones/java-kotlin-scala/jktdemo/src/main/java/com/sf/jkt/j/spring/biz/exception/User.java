package com.sf.jkt.j.spring.biz.exception;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {
    @NotNull(message = "用户ID不能为空")
    Long id;
    @NotNull(message = "用户message不能为空")
            @Size(min = 6,max = 11,message = "账号长度必须为6-11个字符")
    String account;
    @NotNull(message = "用户密码不能为空")
            @Size(min = 6,max = 11,message = "密码必须为 6-11个字符")
    String password;
    @NotNull(message = "用户邮箱不能为空")
            @Email(message = "邮箱格式不正确")
    String email;
}
