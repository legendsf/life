package com.sf.jkt.k.comp.javaagent.myagent;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class TimeInterceptor2 {
    //@This Object thisObj,@Super Object superObj ,
    @RuntimeType
    public static Object intercept(@This Object thisObj,@Super Object superObj,@Origin Method method,
                                   @SuperCall Callable<?> callable,@AllArguments Object[] params) throws Exception {
        long start = System.currentTimeMillis();
        try {
            // 原有函数执行
            return callable.call();
        } finally {
            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
        }
    }




    @RuntimeType
    public static Object intercept( @SuperCall Callable<?> zuper,
                                   @Origin Method method) throws Exception {
        long start = System.currentTimeMillis();
        try {
            // 原有函数执行
            return zuper.call();
        } finally {
            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
        }
    }


}
