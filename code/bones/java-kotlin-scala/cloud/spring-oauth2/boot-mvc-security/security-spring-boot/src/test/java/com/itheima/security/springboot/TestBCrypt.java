package com.itheima.security.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @version 1.0
 **/
//@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void testBCrypt(){

        //对密码进行加密
//        String hashpw = BCrypt.hashpw("456", BCrypt.gensalt());
//        System.out.println(hashpw);

        //校验密码
//        boolean checkpw = BCrypt.checkpw("123", "$2a$10$aFsOFzujtPCnUCUKcozsHux0rQ/3faAHGFSVb9Y.B1ntpmEhjRtru");
//        boolean checkpw2 = BCrypt.checkpw("123", "$2a$10$HuClcUqr/FSLmzSsp9SHqe7D51Keu1sAL7tUAAcb..FyILiLdFKYy");
        boolean checkpw3= BCrypt.checkpw("456","$2a$10$C1VeyyLZIn8/5TMX/bnaJuWPMqYi57.c7gQj71jpTOPlUT9wk5Ii2");
//        System.out.println(checkpw);
//        System.out.println(checkpw2);
        System.out.println(checkpw3);
    }
}
