package com.sf.jkt.k.comp.javaagent.myagent;


import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class TimeInterceptor3 {
    //@This Object thisObj,@Super Object superObj ,
    @RuntimeType
    public static Object intercept(@Advice.This Object thisObj, @Super Object superObj, @Advice.Origin Method method,
                                   @SuperCall Callable<?> callable, @Advice.AllArguments Object[] params) throws Exception {
        long start = System.currentTimeMillis();
        try {
            // 原有函数执行
            return callable.call();
        } finally {
            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
        }
    }



}
