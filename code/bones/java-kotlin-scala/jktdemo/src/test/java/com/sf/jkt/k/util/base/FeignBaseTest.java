package com.sf.jkt.k.util.base;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FeignBaseTest.class)
@Import({ FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class })
public class FeignBaseTest {


}
