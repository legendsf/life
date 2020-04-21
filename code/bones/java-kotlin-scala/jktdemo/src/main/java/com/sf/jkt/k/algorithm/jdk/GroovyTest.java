package com.sf.jkt.k.algorithm.jdk;

import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyTest {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000000; i++) {
            GroovyShell gs = new GroovyShell();
            Script script = gs.parse(" 'Hello, World';");
            Object result = script.run();
            assert result.equals("Hello, World");
        }
    }
}
