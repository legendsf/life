//package com.sf.jkt.k.biz.rest.feign;
//
//import com.sf.jkt.j.spring.biz.web.controller.User;
//import com.sf.jkt.j.spring.biz.resttemplate.feign.UserServiceFacade;
//import com.sf.jkt.k.util.base.FeignBaseTest;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.FeignClient;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@EnableFeignClients(clients = FeignTest.UserServiceFeignClient.class)
//public class FeignTest extends FeignBaseTest {
//    @FeignClient(value = "loan-server", url = "http://localhost:8081/userV2")
//    public interface UserServiceFeignClient extends UserServiceFacade {
//    }
//
//    @Autowired
//    private UserServiceFeignClient userServiceFeignClient;
//
//    @Test
//    public void getUser() {
//        List<User> users = userServiceFeignClient.findAllUserV2();
//        assertTrue(!users.isEmpty());
//    }
//}
