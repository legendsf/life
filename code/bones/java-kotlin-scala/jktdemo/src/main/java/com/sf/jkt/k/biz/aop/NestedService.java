package com.sf.jkt.k.biz.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/****
 * 解決 spring aop 嵌套调用不能正常拦截问题
 * 因为用的cglib 的动态代理类
 * proxy.service1()#this.service2()
 * this 代表原始类，因为原始类是没被weaving 的
 * 故而要autowired 这个单例类，因为这个单例是经过weaving之后的代理类：
 * nestedService.getClass()= com.sf.jkt.k.biz.aop.NestedService$$EnhancerBySpringCGLIB$$e73835bb
 * 所以service2也是通过weaving后的代理类来进行的
 */
@Service
public class NestedService {
    @Autowired
    public NestedService nestedService;

    public void service1() {
        System.out.println("service1:");
        nestedService.service2();
    }

    public void service2() {
        System.out.println("service2:");
        System.out.println(nestedService.getClass().getName());
    }

}
