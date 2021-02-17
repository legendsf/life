package com.sf.jwt.application.service;

import com.sf.jwt.domain.entity.User;
import com.sf.jwt.domain.service.UserServiceImpl;
import com.sf.jwt.infrastructures.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserAppService {
    @Autowired
    UserServiceImpl userService;

    public User getUser(User user){
        if(user==null){
            return null;
        }
        User dbUser= userService.getUser(user.getName(),user.getPassword());
        return dbUser;
    }
    public Map<String, Object> authorization(String name, String password) {
        Map<String, Object> result=new HashMap<>();
        result.put("state",false);
        result.put("msg","认证失败");
        try {
            User reqUser = new User();
            reqUser.setName(name);
            reqUser.setPassword(password);
            User dbUser = getUser(reqUser);
            log.info("dbUser:{}",dbUser);
            if (dbUser != null) {
                Map<String, String> payload = new HashMap<>();
                payload.put("userId", "" + dbUser.getId());
                payload.put("userName", dbUser.getName());
                String token = JwtUtil.getToken(payload);
                result.put("state", true);
                result.put("msg", "认证成功");
                result.put("token",token);
            }
        }catch (Exception ex){
            log.error("认证失败",ex);
            return  result;
        }
        return  result;
    }
}
