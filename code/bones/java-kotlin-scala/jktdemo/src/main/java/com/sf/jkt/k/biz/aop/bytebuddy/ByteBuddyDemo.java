package com.sf.jkt.k.biz.aop.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.Assert;

import java.util.Arrays;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class ByteBuddyDemo {
    public static void main(String[] args) throws Exception {
//        testReload();
//        new ByteBuddyDemo().testIntercept();
//        testDynamic();
//        testMake();
    }

    static void testReload() {
        ByteBuddyAgent.install();
        Foo foo = new Foo();
//        new ByteBuddy().redefine(Bar.class).
//                name(Foo.class.getName())
//                .make().load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
//        Assert.assertEquals(foo.m(), "bar");
        new ByteBuddy().rebase(Bar.class).
                name(Foo.class.getName())
                .make().load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        Arrays.stream(foo.getClass().getDeclaredMethods()).forEach(System.out::println);
        System.out.println(foo.m());
    }

    void testIntercept() throws Exception {
        Object dynamicFoo = new ByteBuddy()
                .subclass(Foo.class)
                // 匹配由Foo.class声明的方法
//                .method(isDeclaredBy(Foo.class)).intercept(FixedValue.value("One!"))
                // 匹配名为foo的方法
//                .method(named("foo")).intercept(FixedValue.value("Two!"))
                // 匹配名为foo，入参数量为1的方法
//                .method(named("foo").and(takesArguments(1))).intercept(FixedValue.value("Three!"))
                .make()
                .load(Foo.class.getClassLoader())
                .getLoaded()
                .newInstance();
//        System.out.println(dynamicFoo.m());
//        System.out.println(dynamicFoo.foo());
//        System.out.println(dynamicFoo.foo("hhhh"));
    }

    static void testLoadClass() {
    }

    static void testDynamic() throws Exception {
        Class<?> dynamic = new ByteBuddy().subclass(Object.class).method(named("toString"))
                .intercept(FixedValue.value("hello world"))
                .make()
                .load(ByteBuddyDemo.class.getClassLoader())
                .getLoaded();
        Object instance = dynamic.newInstance();
        Assert.assertEquals(instance.toString(), "hello world");
        System.out.println(instance.getClass().getCanonicalName());
    }

    static void testMake() throws Exception {

        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("example.Type")
                .make();
        System.out.println(dynamicType.toString());
        ByteBuddy byteBuddy = new ByteBuddy();
        byteBuddy.with(new NamingStrategy.SuffixingRandom("suffix"));
        DynamicType.Unloaded<?> dynamicType1 = byteBuddy.subclass(Object.class).make();
//        dynamicType.saveIn();//保存到文件夹中
//        dynamicType.inject();//注入到Jar包中
        System.out.println(dynamicType1.toString());
    }
}

class Foo {
    String m() {
        return "foo";
    }

    String foo() {
        return "hh";
    }

    String foo(String msg) {
        return msg;
    }
}

class Bar {
    String m() {
        return "bar";
    }
}
