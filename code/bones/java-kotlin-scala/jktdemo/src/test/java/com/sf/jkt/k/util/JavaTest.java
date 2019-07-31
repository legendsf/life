package com.sf.jkt.k.util;

import com.google.common.collect.Collections2;
import com.sf.jkt.k.entity.User;
import com.sf.jkt.k.util.KotlinBaseTestKt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JavaTest {
    static Class<?> getMClass() {
        return User.class;
    }

    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        System.out.println(KotlinBaseTestKt.joinToString(collection, ",", "[", "]"));
    }
}
