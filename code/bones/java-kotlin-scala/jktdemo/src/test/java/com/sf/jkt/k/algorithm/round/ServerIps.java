package com.sf.jkt.k.algorithm.round;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerIps {
    public static final List<String> LIST= Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5",
            "192.168.0.6",
            "192.168.0.7",
            "192.168.0.8",
            "192.168.0.9",
            "192.168.0.10"
    );

    public static final Map<String,Integer> WEIGHT_LIST=new LinkedHashMap<String, Integer>();
    static {
        WEIGHT_LIST.put("A",5);
        WEIGHT_LIST.put("B",1);
        WEIGHT_LIST.put("C",1);

        //weight=55
        /*
        WEIGHT_LIST.put("192.168.0.1",10);
        WEIGHT_LIST.put("192.168.0.2",9);
        WEIGHT_LIST.put("192.168.0.3",8);
        WEIGHT_LIST.put("192.168.0.4",7);
        WEIGHT_LIST.put("192.168.0.5",6);
        WEIGHT_LIST.put("192.168.0.6",5);
        WEIGHT_LIST.put("192.168.0.7",4);
        WEIGHT_LIST.put("192.168.0.8",3);
        WEIGHT_LIST.put("192.168.0.9",2);
        WEIGHT_LIST.put("192.168.0.10",1);

         */
    }
}
