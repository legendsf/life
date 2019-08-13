package com.sf.jkt.k.util;

import com.sf.jkt.k.entity.MUser;

import java.util.ArrayList;
import java.util.Collection;

public class JavaTest {
    static Class<?> getMClass() {
        return MUser.class;
    }

    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        System.out.println(KotlinBaseTestKt.joinToString(collection, ",", "[", "]"));
    }
}
