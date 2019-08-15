package com.sf.jkt.k.comp.javaagent.myagent;

import com.sf.jkt.k.comp.javaagent.advice.AdviceProfiledTransformer;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class MyAgent2 {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is an perform monitor agent.argentArgs=" + agentArgs);

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(ElementMatchers.<MethodDescription>any()) // 拦截任意方法
                        .intercept(MethodDelegation.to(TimeInterceptor2.class)); // 委托
            }

        };


        AgentUtil.INSTANCE.createBuilder()
                .type(ElementMatchers.nameStartsWith("com.sf")) // 指定需要拦截的类
                .transform(transformer)
                .transform(new AdviceProfiledTransformer())
                .with(AgentUtil.INSTANCE.getDefaultListener())
                .installOn(inst);
    }
}