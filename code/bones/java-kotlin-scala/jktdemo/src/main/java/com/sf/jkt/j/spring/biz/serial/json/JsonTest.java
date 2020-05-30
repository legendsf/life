package com.sf.jkt.j.spring.biz.serial.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JSON
 */
public class JsonTest {


    /**
     * 这种一个线程共用一个是比较好的
     */
    private static final ThreadLocal<ObjectMapper> om = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper;
        }
    };


    /**
     * 线程安全
     */
    static Gson gson = new Gson();
    /**
     *
     */
    static ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) throws Exception {
        testDateTimeStamp();
        testDateStr();
        testDateStrGson();
        testDateStrFastJson();
    }



    /***
     * objectMapper 线程安全但锁冲突严重
     * https://www.codenong.com/3907929/
     * @throws Exception
     */
    public static void  testDateTimeStamp()throws Exception{
        Date date = new Date();
        String str = mapper.writeValueAsString(date);
        //1590459877760
        System.out.println(str);
    }

    public static void testDateStr() throws Exception{
        Date date = new Date();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        String str = mapper.writeValueAsString(date);
        //1590459877760
        System.out.println(str);

    }



    /***
     * JsonAutoDetect
     * objectMapper.registerModule 详解
     * https://www.jianshu.com/p/89f8040fe956
     * https://vimsky.com/examples/detail/java-method-org.codehaus.jackson.map.ObjectMapper.registerModule.html
     * @throws Exception
     */
    public static void testDateObjectMapper()throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.registerModule(new GuavaModule());
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * Gson 是线程安全的
     * https://stackoom.com/question/3KPZ3/Gson%E7%9A%84%E6%80%A7%E8%83%BD%E6%98%AF%E5%90%A6%E5%8F%AF%E4%BB%A5%E4%BB%8E%E9%AB%98%E5%BA%A6%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%9A%84%E5%BA%94%E7%94%A8%E7%A8%8B%E5%BA%8F%E4%B8%AD%E7%9A%84%E8%B5%84%E6%BA%90%E6%B1%A0%E4%B8%AD%E5%8F%97%E7%9B%8A
     * @throws Exception
     */
    public static void testDateStrGson()throws Exception{
        Date date = new Date();
        String str=gson.toJson(date);
        System.out.println(str);
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson=builder.create();
        System.out.println(gson.toJson(date));
    }

    /**
     * 处理日期
     * https://blog.csdn.net/liupeifeng3514/article/details/79166566
     *  处理超大 JSON
     *  https://blog.csdn.net/liupeifeng3514/article/details/79179767?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1
     */
    public static void  testDateStrFastJson(){
        Date date = new Date();
        String str=JSON.toJSONString(date);
        System.out.println(str);
        str=JSON.toJSONStringWithDateFormat(date,"yyyy-MM-dd HH:mm:ss");
        System.out.println(str);
    }

    public static ObjectMapper getObjectMapper() {
        return om.get();
    }

}
