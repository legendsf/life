package com.sf.jkt.k.algorithm.jdk;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.*;

public class ByteArrayOutputStreamTest {
    public static void main(String[] args) throws Exception {
        testStream();
    }

    public static void testStream() throws Exception {
        String path = new ClassPathResource("").getAbsolutePath();
        System.out.println(path);
        path = new org.springframework.core.io.ClassPathResource("/").getPath();
        System.out.println(path);

        File file = new ClassPathResource("/faces/classes/Mhello.class").getFile();

        try (
                InputStream ins = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int len = 0;
            byte[] dataBytes = null;
            while ((len = ins.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }
            baos.flush();
            dataBytes=baos.toByteArray();
//            ByteArrayInputStream bais = new ByteArrayInputStream(dataBytes);
//            bais.close();

        }

    }
}
