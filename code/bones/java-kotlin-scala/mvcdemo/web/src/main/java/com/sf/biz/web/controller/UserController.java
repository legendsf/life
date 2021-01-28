package com.sf.biz.web.controller;

import com.google.gson.Gson;
import com.sf.biz.web.entity.User;
import com.sf.biz.web.mapper.UserDynamicSqlSupport;
import com.sf.biz.web.mapper.UserMapper;
import com.sf.biz.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CacheConfig(cacheNames = "user2",cacheManager = "redissonCacheManager")
public class UserController {
    Gson gson=new Gson();
    @Autowired
    UserService userService;
    @RequestMapping("/all")
    public List<User> getAll(){
       return userService.getAllUser();
    }

    @RequestMapping("/byId")
    @Cacheable(key = "#p0")
    public User selectById(@RequestParam Integer id){
       return userService.selectById(id);
    }

    /**
     * http://localhost:8083/user/byIds?id=1&id=2
     * @param ids
     * @return
     */
    @RequestMapping("/byIds")
    public List<User> selectById(@RequestParam("id") List<Integer> ids){
        return userService.selectByIds(ids);
    }

    /**
     * http://localhost:8083/user/byNames?name=sf&name=gd
     * @param names
     * @return
     */
    @RequestMapping("/byNames")
    public List<User> selectByNames(@RequestParam("name") List<String> names){
        return userService.selectByNames(names);
    }

    /**
     * 大括号要转义 user=%7b"id":"1","name":"sf"%7d
     * 逻辑：user={"id":1,"name":"sf"}
     * @param user
     * @return
     */
    @RequestMapping("/byUser")
    public User selectByUser(@RequestParam("user")String user){
       User user1=  gson.fromJson(user,User.class);
       return userService.selectByUser(user1);

    }

    @RequestMapping("/byUser2")
    @Cacheable(key = "#p0") //使用第一个参数
//      @Cacheable //默认使用 keygenerator 方法进行设置 key
    public User selectByUser2(@RequestParam("user")String user){
        User user1=  gson.fromJson(user,User.class);
        return userService.selectByUser2(user1);

    }

}
