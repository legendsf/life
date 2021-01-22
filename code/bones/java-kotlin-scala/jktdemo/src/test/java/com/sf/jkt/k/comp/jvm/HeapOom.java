package com.sf.jkt.k.comp.jvm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HeapOom {
    byte[] b= new byte[1024*100];

    public static void main(String[] args) throws Exception{
        List<HeapOom> all = new ArrayList<>();
        while (true){
            all.add(new HeapOom());
            Thread.sleep(10);
        }

    }

    public static void url(){
        File file = new File("good.txt");
    }
}
