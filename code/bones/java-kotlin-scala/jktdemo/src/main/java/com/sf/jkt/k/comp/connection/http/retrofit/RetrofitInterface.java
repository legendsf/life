package com.sf.jkt.k.comp.connection.http.retrofit;


import com.sf.jkt.k.comp.connection.http.feign.StuMsg;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/restTest")
    Observable<StuMsg> getStuMsg(@Query("id") int id);


}
