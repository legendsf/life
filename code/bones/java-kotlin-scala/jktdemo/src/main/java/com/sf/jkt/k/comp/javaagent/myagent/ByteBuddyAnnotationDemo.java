package com.sf.jkt.k.comp.javaagent.myagent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

public class ByteBuddyAnnotationDemo {
    public static void main(String[] args) {

    }

    public static void test1(String[] args) {
        DynamicType.Builder<Object> builder = new ByteBuddy().subclass(Object.class).name("Dynamic");
        builder = builder.defineMethod("method", void.class, Visibility.PUBLIC)
                .intercept(MethodDelegation.to(new Object() {

                    @RuntimeType
                    public void intercept(@This Object o) {
                        System.out.println("Executing code...");
                    }

                }));

//        builder = builder.method(ElementMatchers.named("method")).
//                intercept(MethodDelegation.to(new Object(){
//
//                    @RuntimeType
//                    public void intercept(@This Object o) {
//                        System.out.println("Executing other code...");
//                    }
//
//                }));

        try {
            Class cls = builder.make()
                    .load(ByteBuddyAnnotationDemo.class.getClassLoader())
                    .getLoaded();

            Object obj = cls.newInstance();
            cls.getDeclaredMethod("method").invoke(obj, args);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
