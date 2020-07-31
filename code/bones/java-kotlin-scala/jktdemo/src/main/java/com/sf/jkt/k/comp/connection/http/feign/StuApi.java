package com.sf.jkt.k.comp.connection.http.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface StuApi {
    @Headers("Content-Type: application/json")
    @RequestLine("GET /stu?id={id}")
    StuMsg getStuMsg(@Param("id") int id);

}
