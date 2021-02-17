package com.sf.jwt.interfaces.facade;

import com.sf.jwt.infrastructures.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/session")
@Slf4j
public class SessionController {
    @GetMapping("/test")
    public Map<String, Object> test(String token){
        Map<String,Object> map=new HashMap<>();
        map.put("state",false);
        map.put("msg","请求失败");

        try {
            Map<String,String> jwtMap= JwtUtil.verifyGetMap(token);
            map.put("jwtMap",jwtMap);
            if(BooleanUtils.toBoolean(jwtMap.get("STATE"))){
                map.put("state",true);
                map.put("msg","请求成功");
            }
        }catch (Exception e){
            log.error("test",e);
            return map;
        }
        return  map;
    }

    @GetMapping("/test1")
    public String test1(HttpServletRequest request){
       String token=  request.getHeader("_token");
       Map<String,String> jwtMap=JwtUtil.decodeMapNoVerify(token);
       String userId= jwtMap.get("userId");
       String userName=jwtMap.get("userName");
       log.info("---userId:{}",userId);
        log.info("---userName:{}",userName);

        return "hello test1 verify ok!";
    }
}
