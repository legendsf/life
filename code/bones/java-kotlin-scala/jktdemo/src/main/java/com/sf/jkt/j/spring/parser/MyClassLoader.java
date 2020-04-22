package com.sf.jkt.j.spring.parser;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class MyClassLoader extends ClassLoader {
    protected Class<?> findClass(String name) {
        try {
            String path = "D:\\tmp\\" + name + ".class";
//            path = "E:\\data\\life\\code\\bones\\java-kotlin-scala\\jktdemo\\src\\main\\resources\\faces\\classes\\" + name + ".class";
//            path="/E:/data/life/code/bones/java-kotlin-scala/jktdemo/target/classes/faces/classes/Mhello.class";
            System.out.println(path);
            InputStream in = new FileInputStream(path);

            String str = "/faces/classes/" + name + ".class";
//            in = MyClassLoader.class.getResourceAsStream(str);
            URL url = MyClassLoader.class.getResource(str);
//            String pathstr=url.getPath();
//             String filestr=url.getFile();

//            File file = new File(url.getFile());
//            in = new FileInputStream(url.getFile());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            in.close();
            baos.flush();
            byte[] classBytes = baos.toByteArray();
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}