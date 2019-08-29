package com.sf.jkt.k.algorithm.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jregex {
    public static void main(String[] args) {
        Pattern p =Pattern.compile("([^ ]*) ([^ ]*)");
        Matcher m=p.matcher("31/Aug/2015:00:04:37 +0800");
        System.out.println(m.find());
        System.out.println(m.groupCount());
        System.out.println(m.group(0));
        System.out.println(m.group(1));
        System.out.println(m.group(2));
        p=Pattern.compile("[^\"]+");
        m=p.matcher("31/Aug/2015:00:04:37 +0800");
        System.out.println(m.find());
        System.out.println(m.group());
    }
}
