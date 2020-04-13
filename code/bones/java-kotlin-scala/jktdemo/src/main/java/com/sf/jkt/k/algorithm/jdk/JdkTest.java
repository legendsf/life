package com.sf.jkt.k.algorithm.jdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JdkTest {
    public static void main(String[] args) {
        mapDelete();
    }

    public static void mapDelete() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> ent = iterator.next();
            String k = ent.getKey();
            String v = ent.getValue();
            System.out.println(k + "---" + v);
            if (k.equals("1")) {
                iterator.remove();
            }
        }
        System.out.println(map.size());
    }


}
