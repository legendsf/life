package com.sf.jkt.k.algorithm.jdk;

import java.io.File;
import java.net.URL;

public class ResourceTest {
    public static void main(String[] args) {
        ResourceTest test = new ResourceTest();
        test.getResource();
    }

    public void getResource() {
        //空返回 到当前package的目录：/E:/data/life/code/bones/java-kotlin-scala/jktdemo/target/classes/com/sf/jkt/k/algorithm/jdk/
        URL url = ResourceTest.class.getResource("");
        System.out.println(url.getPath());
        //返回到jar包的classes的根目录：/E:/data/life/code/bones/java-kotlin-scala/jktdemo/target/classes/
        url = ResourceTest.class.getResource("/");
        System.out.println(url.getPath());
        url = ResourceTest.class.getResource("/docker/docker.txt");
        System.out.println(url.getPath());

        URL url1 =this.getClass().getClassLoader().getResource("resource.txt");
        System.out.println(url1.toString());

        String fstr=ResourceTest.class.getResource("")+"resource.txt";
        File file=new File(fstr);
        System.out.println(file.exists());
    }
}
