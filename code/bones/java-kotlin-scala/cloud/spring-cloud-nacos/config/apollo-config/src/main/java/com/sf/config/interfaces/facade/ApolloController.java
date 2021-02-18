package com.sf.config.interfaces.facade;

import com.alibaba.druid.pool.DruidDataSource;
import com.sf.config.domain.mapper.UserMapper;
import com.sf.config.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/apollo")
public class ApolloController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSource druidDataSource;

    @Autowired
    private UserMapper userMapper;

    @Value("${demo.user.name}")
    private String userName;

    @Value("${demo.user.age}")
    private int age;

    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }
    @GetMapping("/dburl")
    public String dbUrl(@Value("${spring.datasource.url}") String url){
        return url;
    }
    @GetMapping("/demo")
    public String demo(){
        return userName+"  "+age;
    }

    @GetMapping("/mapper")
    public User getMapper(){
        final User user = userMapper.selectByNameAndPassword("sf", "sf");
        return user;
    }

    @GetMapping("/dataSource")
    public String getDataSourceInfo(){
        DruidDataSource druid =(DruidDataSource) dataSource;
        String msg="";
         msg += druid.isTestOnBorrow()+"\n";
         msg += druid.getMaxWait()+"\n";
         msg += druid.toString()+"\n";
         return msg;
    }
}
