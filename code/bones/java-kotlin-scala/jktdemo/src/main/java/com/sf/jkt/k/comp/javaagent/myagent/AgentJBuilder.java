package com.sf.jkt.k.comp.javaagent.myagent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;

public class AgentJBuilder {
    public static void createBuilder() {
        final ByteBuddy byteBuddy = new ByteBuddy()
                .with(TypeValidation.of(true));

        AgentBuilder agentBuilder = new AgentBuilder.Default(byteBuddy)
                .ignore(
                        nameStartsWith("net.bytebuddy.")
                                .or(nameStartsWith("org.slf4j."))
                                .or(nameStartsWith("org.apache.logging."))
                                .or(nameStartsWith("org.groovy."))
                                .or(nameContains("javassist"))
                                .or(nameContains(".asm."))
                                .or(nameStartsWith("sun.reflect"))
                                .or(ElementMatchers.<TypeDescription>isSynthetic()));
    }
}
