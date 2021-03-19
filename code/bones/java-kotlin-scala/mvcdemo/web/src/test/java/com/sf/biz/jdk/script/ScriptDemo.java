package com.sf.biz.jdk.script;

import javax.script.*;
import java.util.List;
import java.util.Random;

public class ScriptDemo {
    public static void main(String[] args)throws Exception{
        testJavaScript();
        testFactories();
    }

    public static void testFactories(){
        ScriptEngineManager mgr = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = mgr.getEngineFactories();

        for (ScriptEngineFactory factory : factories) {

            System.out.println("ScriptEngineFactory Info");

            String engName = factory.getEngineName();
            String engVersion = factory.getEngineVersion();
            String langName = factory.getLanguageName();
            String langVersion = factory.getLanguageVersion();

            System.out.printf("\tScript Engine: %s (%s)%n", engName, engVersion);

            List<String> engNames = factory.getNames();
            for(String name : engNames) {
                System.out.printf("\tEngine Alias: %s%n", name);
            }

            System.out.printf("\tLanguage: %s (%s)%n", langName, langVersion);

        }

    }

    public static void testJavaScript()throws Exception{
        String exp = "a.nextInt(10)+b";
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        if(scriptEngine instanceof Compilable) {
            CompiledScript compiledScript = ((Compilable)scriptEngine).compile(exp);
            Bindings bindings = new SimpleBindings();
            bindings.put("a", new Random());
            bindings.put("b", 5);
            Object result = compiledScript.eval(bindings);
            System.out.println(exp + "=" + result);

            bindings = new SimpleBindings();
            bindings.put("a", new Random());
            bindings.put("b", 5);
            result = compiledScript.eval(bindings);
            System.out.println(exp + "=" + result);
        } else {
            scriptEngine.put("a", new Random());
            scriptEngine.put("b", 15);
            Object result = scriptEngine.eval(exp);
            System.out.println(exp + "=" + result);
        };
    }
}
