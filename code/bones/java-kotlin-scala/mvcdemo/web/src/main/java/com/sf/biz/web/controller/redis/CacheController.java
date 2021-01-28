package com.sf.biz.web.controller.redis;

import com.google.gson.Gson;
import com.sf.biz.web.entity.User;
import com.sf.biz.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * spring cache
 * https://mrbird.cc/Spring-Boot%20cache.html
 */

@RestController
@RequestMapping("/cache")
public class CacheController {

   @Autowired
   UserService userService;



   @RequestMapping("/cacheUser")
   public String cache(@RequestParam("id") int id){
      User user= userService.selectByCacheId(id);
      return new Gson().toJson(user);
   }
}
