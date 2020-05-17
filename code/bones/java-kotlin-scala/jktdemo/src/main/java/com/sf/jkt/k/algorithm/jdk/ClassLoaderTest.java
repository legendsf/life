package com.sf.jkt.k.algorithm.jdk;

import com.sf.jkt.j.spring.biz.classload.Mhello;
import com.sf.jkt.j.spring.biz.classload.parser.Hello;
import com.sf.jkt.j.spring.biz.classload.parser.MyClassLoader;
import com.sf.jkt.j.spring.biz.classload.parser.MyClassLoader1;

import java.io.File;
import java.net.URLClassLoader;

/**
 * https://blog.csdn.net/qq_27540131/article/details/51817669
 * https://blog.csdn.net/qq_41380422/article/details/83718353
 * <p>
 * AppClassLoader
 * <p>
 * AppClassLoader应用类加载器，又称系统类加载器，负责在JVM启动时加载来自命令java中的classpath或者java.class.path系统属性或者CLASSPATH操作系统属性所指定的JAR类包和类路径
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Bootstrap ClassLoder、ExtClassLoader、AppClassLoader这么多ClassLoader，它们是从哪里加载class的，这个问题jdk源码中sun.misc.Launcher已经给出回答：Bootstrap ClassLoder加载的是System.getProperty("sun.boot.class.path");、
 * ExtClassLoader加载的是System.getProperty("java.ext.dirs")、AppClassLoader加载的是System.getProperty("java.class.path")，
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        new File("");
        System.out.println(File.separator);
        String pkg = "com.sf.k";
        String rep = pkg.replaceAll("\\.", "\\\\");
        System.out.println(rep);
        testClassLoader1();
    }

    public static void testClassLoader1() throws Exception {
        MyClassLoader1 cl = new MyClassLoader1();
        //由于该class 已经被 AppclassLoader 加载，加载 classPath 下所有类
        //所以双亲委派模型，先委派给父亲，AppClassLoader已经加载，那么就直接返回
        Class<?> clz = cl.loadClass("com.sf.jkt.j.spring.biz.classload.parser.Mhello");
        System.out.println(clz.getClassLoader());
        System.out.println(clz.getName());
        Hello hello=(Hello) clz.newInstance();
        hello.hello("ssssss");
        System.out.println(Hello.class.isInstance(hello));
        System.out.println(hello instanceof  Hello);
        System.out.println(Hello.class.isAssignableFrom(clz));
        System.out.println(Mhello.class.isInstance(hello));
        System.out.println("*******end");
    }

    public static void testClassLoader() throws Exception {
        //class.forName 用的也是APPclassLoader 加载，AppClassLoader classPath
        System.out.println(Class.forName("com.sf.jkt.k.algorithm.jdk.ClassLoaderTest").getClassLoader());

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
        System.out.println("obj1.classLoader" + obj1.getClassLoader());
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
//        Class<?> mclz = Class.forName("Mhello");// 是用AppclassLoader 去加载
//        boolean flag2 = mclz.isInstance(obj);


    }
}

class ClassLoader1 extends URLClassLoader {
    ClassLoader1() {
        super(null, null);
    }
}




