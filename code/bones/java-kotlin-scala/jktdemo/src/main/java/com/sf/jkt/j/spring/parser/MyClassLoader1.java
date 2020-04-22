package com.sf.jkt.j.spring.parser;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class   MyClassLoader1 extends ClassLoader {
    protected Class<?> findClass(String name) {
        try {
            if (name.contains(".")) {
                name = name.replaceAll("\\.", "\\\\");
            }
            String path = "D:\\tmp\\" + name + ".class";
            FileInputStream in = new FileInputStream(path);

//            String str = "/faces/classes/" + name + ".class";
//            InputStream in = MyClassLoader.class.getResourceAsStream(str);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            in.close();
            baos.flush();
            byte[] classBytes = baos.toByteArray();

            return defineClass(null, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}