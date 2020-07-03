package com.sf.jkt.j.spring.biz.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetryService {
   //aspect 增强实效，不能 retry
   public void test(){
      devide(1,0);
   }

   @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(
           delay = 2000L,multiplier = 2
   ))
   public void devide(double a,double b){
      log.info("开始消除除法运算");
      if(b==0){
         throw new RuntimeException();
      }
      log.info("{}/{}={}",a,b,a/b);

   }
   @Recover
   public void recover(Exception e){
      log.error("被除数不能为 0",e);
   }
}
