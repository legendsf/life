package com.sf.ddd.third.api;

/***
 * 这一层最好不要强依赖 feign
 * 因为以后协议可能要变更
 * dubbo
 * grpc
 * etc...
 */
public interface IthirdApi {
    String hello(String msg);
    String helloId(String id);
    String helloTimeout();
}
