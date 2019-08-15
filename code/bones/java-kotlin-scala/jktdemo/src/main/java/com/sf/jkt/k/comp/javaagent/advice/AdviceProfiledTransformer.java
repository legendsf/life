package com.sf.jkt.k.comp.javaagent.advice;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

/**
 * Created by dragon on 16/4/18.
 */
public class AdviceProfiledTransformer implements AgentBuilder.Transformer {

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription,
                                            ClassLoader classLoader, JavaModule module) {
        return builder.visit(Advice.to(AdviceProfiledInterceptor.class).on(ElementMatchers.named("profile")));
    }


}
