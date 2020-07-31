package com.sf.jkt.k.comp.connection.http.retrofit;

import com.sf.jkt.k.comp.connection.http.feign.StuMsg;
import io.reactivex.Observable;

import java.util.concurrent.Future;

public class RetrofitDemo {
    public static void main(String[] args)throws Exception {
        testRetrofit();
    }

    public static void testRetrofit()throws Exception{
        Observable<StuMsg> stuMsg=RetrofitUtil.getInstance("http://localhost:8002")
                .getRetrofitInterface(RetrofitInterface.class).getStuMsg(1);
        Future<StuMsg> f= stuMsg.toFuture();
        System.out.println(f.get());
        System.out.println("hello ");
    }
}
