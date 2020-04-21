package com.sf.jkt.k.algorithm.jdk;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        testClassLoader();
    }

    public static void testClassLoader() throws Exception {
        ClassLoader classLoader = String.class.getClassLoader();
        ClassLoader appClassLoader = ClassLoaderTest.class.getClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootClassLoader = extClassLoader.getParent();//c++;
        MyClassLoader myClassLoader = new MyClassLoader();
        ClassLoader cl = MyClassLoader.class.getClassLoader();
        ClassLoader mp = myClassLoader.getParent();
//        ClassLoader1 classLoader1 = new ClassLoader1();
//        System.out.println(classLoader1.getParent());
        Class<?> obj = myClassLoader.loadClass("Mhello");
        System.out.println(obj.getClass());
        MyClassLoader1 myClassLoader1 = new MyClassLoader1();
        Class<?> obj1 = myClassLoader1.loadClass("Mhello");
        System.out.println(obj.getName());
        System.out.println(obj1.getName());
        Object object0 = obj.newInstance();
        Object object1 = obj1.newInstance();
        boolean flag = obj.isInstance(object0);
        boolean flag1 = obj.isInstance(object1);
        System.out.println(flag);
        System.out.println(flag1);
        System.out.println(obj == obj1);
        System.out.println(obj.equals(obj1));

        Object objj = myClassLoader.loadClass("Mhello");
        System.out.println(obj.getClass());
        myClassLoader1 = new MyClassLoader1();
        Object objj1 = myClassLoader1.loadClass("Mhello");
        System.out.println(objj.equals(objj1));

    }
}

class ClassLoader1 extends URLClassLoader {
    ClassLoader1() {
        super(null, null);
    }
}



class MyClassLoader1 extends ClassLoader {
    protected Class<?> findClass(String name) {
        try {
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
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

