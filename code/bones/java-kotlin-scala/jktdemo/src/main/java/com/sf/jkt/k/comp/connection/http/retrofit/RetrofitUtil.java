package com.sf.jkt.k.comp.connection.http.retrofit;

import cn.hutool.core.date.StopWatch;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Data
@Slf4j
public class RetrofitUtil {
    private Retrofit retrofit;
    static RetrofitUtil retrofitUtil;
    public RetrofitUtil(){}
    public RetrofitUtil(String baseUrl){
        HttpLoggingInterceptor loginterceptor=new HttpLoggingInterceptor(new HttpLogger());
        loginterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient().newBuilder()
                .addNetworkInterceptor(loginterceptor)
//                .addInterceptor()
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitUtil getInstance(String baseUrl){
        if(retrofitUtil==null){
            synchronized (RetrofitUtil.class){
                if(null==retrofitUtil){
                    retrofitUtil=new RetrofitUtil(baseUrl);
                }
            }
        }
        return retrofitUtil;
    }

    public <T> T getRetrofitInterface(Class<T> tclaz){
       return  retrofit.create(tclaz);
    }




    public static class  HttpLogger implements HttpLoggingInterceptor.Logger{
        @Override
        public void log(String s) {
            log.info(s);
        }
    }



    public static class LoggingInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request= chain.request();
            log.info("myMessage",String.format("send url %s \n %s \n %s \n %s",
                    request.url(),chain.connection(),request.headers(),request.body()));
            StopWatch watch=new StopWatch("A");
            watch.start();
            Response response=chain.proceed(request);
            watch.stop();
            log.info("myMessage",String.format("Received response for %s headers: %s",response.request().url(),response.headers()));
            log.info(watch.prettyPrint());
            return response;


//            上传公共参数
//            Request request = chain.request();
//            Response response = null;
//            Request requestProcess = null ;
//            if("GET".equals(request.method())){
//                String url =  request.url().toString() + "&source=android";
//                Request.Builder builder =  request.newBuilder() ;
//                builder.get().url(url);
//                requestProcess =  builder.build();
//                response = chain.proceed(requestProcess);
//            } else {
//                FormBody.Builder builder = new FormBody.Builder() ;
//                RequestBody requestBody =  request.body() ;
//                if(requestBody instanceof FormBody){
//                    FormBody formBody = (FormBody)requestBody ;
//                    for (int i=0;i<formBody.size();i++){
//                        builder.add(formBody.encodedName(i),formBody.encodedValue(i));
//                    }
//                    builder.add("source","android");
//                }
//                requestProcess =  request.newBuilder().url(request.url().toString()).post(builder.build()).build() ;
//                response = chain.proceed(requestProcess);
//            }
//            return response;



        }
    }







}
