package com.sf.jwt.infrastructures.config.interceptor;

import com.google.gson.Gson;
import com.sf.jwt.infrastructures.JwtUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    static Gson gson = new Gson();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI= request.getRequestURI();
        Map<String, String> resp = new HashMap<>();
        String token = request.getHeader("_token");
        if (StringUtils.isBlank(token)) {
            token = request.getHeader("token");
        }
        Map<String, String> jwtMap = JwtUtil.verifyGetMap(token);
        if (BooleanUtils.toBoolean(jwtMap.get("STATE"))) {
            return true;
        } else {
            resp.put("state","false");
            resp.put("msg",jwtMap.get("JWT_MSG"));
            String respStr= gson.toJson(resp);
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().println(respStr);
            return false;
        }
    }
}
