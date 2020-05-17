package com.sf.jkt.j.spring.biz.resttemplate;

import kotlin.text.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Slf4j
public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        logRequest(request,body);
        ClientHttpResponse response = execution.execute(request,body);
        logResponse(response);
        response.getHeaders().add("headerName","VALUE");
        return response;

    }

    private void  logRequest(HttpRequest request,byte[] body)throws  IOException{
        log.debug("=============request begin=============");
        log.debug("URL:{}",request.getURI());
        log.debug("Method:{}",request.getMethod());
        log.debug("Headers:{}",request.getHeaders());
        log.debug("Request body:{}",new String(body,"UTF-8"));
        log.debug("=============request end=============");

    }

    private void logResponse(ClientHttpResponse response)throws IOException{

        log.debug("=============request begin=============");
        log.debug("Status Code:{}",response.getStatusCode());
        log.debug("Status text:{}",response.getStatusText());
        log.debug("Headers:{}",response.getHeaders());
        log.debug("Response body:{}", StreamUtils.copyToString(response.getBody(), Charsets.UTF_8));
        log.debug("=============request end=============");
    }



}
