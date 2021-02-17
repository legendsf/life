package com.sf.jwt.interfaces.facade;

import com.sf.jwt.application.service.UserAppService;
import com.sf.jwt.infrastructures.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserAppService userService;

    @GetMapping("/login")
    public Map<String, Object> login(String name, String password, HttpServletRequest request) {
//        request.getSession().setAttribute("username", name);//创建 session,系统会默认创建一个
        return  userService.authorization(name,password);
    }



}
