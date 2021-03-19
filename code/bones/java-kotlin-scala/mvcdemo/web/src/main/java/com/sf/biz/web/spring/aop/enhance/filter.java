
package com.sf.biz.web.spring.aop.enhance;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;
 
public class filter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if(method.getName().equals("toString")) {
            return 1;
        }
        return 0;
    }
}