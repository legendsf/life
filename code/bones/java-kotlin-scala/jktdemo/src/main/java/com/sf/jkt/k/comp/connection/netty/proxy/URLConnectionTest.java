package com.sf.jkt.k.comp.connection.netty.proxy;

import cn.hutool.http.HttpUtil;
import org.apache.commons.codec.Charsets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/***
 * okhttp 在后端使用
 * https://www.v2ex.com/t/678083
 */
public class URLConnectionTest {
    public static void main(String[] args) throws Exception{
        testUrlConnection();
        testHutoolHttp();
    }

    public static void testOKhttp()throws Exception{

    }

    public static void testHutoolHttp(){
        String result1= HttpUtil.get("https://www.baidu.com");
        System.out.println(result1);
//        String url = "http://www.sogou.com";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("query", 10086);
//        // 无参GET请求
//       String result = HttpUtil.get(url);
//        System.out.println(result);
//        // 带参GET请求
//        String result2 = HttpUtil.get(url, paramMap);
//        System.out.println(result2);
//
//
//        HashMap<String, Object> paramMap1 = new HashMap<>();
//        paramMap1.put("city", "北京");
//        result= HttpUtil.post("http://www.baidu.com", paramMap1);
//        System.out.println(result);
    }

    public static void testUrlConnection()throws Exception{
        URL url = new URL("http://www.baidu.com");
        URLConnection urlConnection=url.openConnection();
        HttpURLConnection httpURLConnection=(HttpURLConnection)urlConnection;
        httpURLConnection.setRequestMethod("GET");
        //连接
        httpURLConnection.connect();
        int responseCode=httpURLConnection.getResponseCode();
        String respMsg="";
        if(responseCode==HttpURLConnection.HTTP_OK){
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream(), Charsets.UTF_8));
            StringBuilder sbd = new StringBuilder();
            String l;
            while ((l=bufferedReader.readLine())!=null){
                sbd.append(l).append("\n");
            }
            respMsg=sbd.toString();
        }
        System.out.println(respMsg);
    }
}
