package com.sf.config.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/***
 * @ConfigurationProperties 必须有 setter 才能注入
 * map 这种 spring 能识别
 */
@Service
@ConfigurationProperties( prefix = "demo1",ignoreInvalidFields = true)
public class Demo1Config {
  private String msg="";
  private HashMap<String, String> pool = new HashMap<>();

  public HashMap<String, String> getPool() {
    return pool;
  }

//  public String getMsg() {
//    return msg;
//  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setPool(HashMap<String, String> pool) {
    this.pool = pool;
  }
}
