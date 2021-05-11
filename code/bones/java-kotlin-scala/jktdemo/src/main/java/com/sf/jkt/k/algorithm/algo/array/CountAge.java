package com.sf.jkt.k.algorithm.algo.array;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class CountAge {
    
    public static void main(String[] args) {


    }



    public static void  countage1(){
        Resource r=new ClassPathResource("");
        System.out.println(r.getFilename());
        System.out.println(CountAge.class.getResource(""));
        System.out.println(CountAge.class.getResource("/"));
        countAge(r.getFilename());
    }

    public  static  void countAge(String fileName){
        File f=new File(fileName);
        InputStream in;


        FileInputStream fis=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        int[] ages=new int[200];
        try{
            fis=new FileInputStream(f);
            isr=new InputStreamReader(fis);
            br=new BufferedReader(isr);
            String temp="";
            int total=0;
            while ((temp=br.readLine())!=null){
                int age=Integer.valueOf(temp);
               ages[age]++;
               total++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

        for(int i=0;i<ages.length;i++){
            System.out.println("第 "+i+" ："+ages[i]);
        }
    }

}
