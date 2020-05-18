package com.sf.jkt.j.jdk8.stream;

import cn.hutool.core.lang.Assert;
import com.google.common.base.Stopwatch;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FormulaTest {
    public static void main(String[] args)throws Exception {
//        testFormula();
//         testFunctionalInterface();
//        testPersonFactory();
//        new Lambda4().testScopes();
//        testPredicate();
//        testAndThen();
//        testSupplier();
//        testConsumer();
//        testOptional();
//        testStream2();
//        testStopWatch();
//        testParallelStream();
//        testMath();
        testMap();
    }

    public static void testMath(){
        double d = Math.pow(2,10);
        System.out.println(d);
    }



    public static void testStopWatch() throws Exception{
        //自动计时器
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(1);
        long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(time);
        //非自动计时器
        Stopwatch stopwatch1 = Stopwatch.createUnstarted();
        stopwatch1.start();
        TimeUnit.SECONDS.sleep(1);
        stopwatch1.stop();
        time=stopwatch1.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(time);

    }

    public static void testMap(){
        int len =(int)Math.pow(2,5);
        Map<Integer,String> map= new HashMap(len,0.75f);
        for(int i=0;i<10;i++){
           map.putIfAbsent(i,"val"+i);
        }
        map.forEach((id,val)-> System.out.println(val));
        map.computeIfPresent(3,(num,val)->val+num);
        System.out.println(map.get(3));
        map.computeIfAbsent(23,num->"val"+num);
        System.out.println(map.containsKey(23));
        System.out.println(map.getOrDefault(42,"not found"));
        map.remove(3,"val3");
        Assert.isTrue(map.containsKey(3));
        map.remove(3,"val33");
        Assert.isFalse(map.containsKey(3));
        map.merge(9,"val9",(v,nv)->v.concat(nv));
        System.out.println(map.get(9));

    }

    public static void testParallelStream(){
        int max= 1000000;
        List<String> values=new ArrayList<>(max);
        for(int i=0;i<max;i++){
            UUID uuid= UUID.randomUUID();
            values.add(uuid.toString());
        }
        Stopwatch watch = Stopwatch.createUnstarted();
        watch.start();
        long count=values.stream().sorted().count();
        System.out.println(count);
        System.out.println(watch.stop().elapsed(TimeUnit.MILLISECONDS));
        Stopwatch watch2=Stopwatch.createStarted();
        long count1=values.parallelStream().sorted().count();
        System.out.println(watch2.stop().elapsed(TimeUnit.MILLISECONDS));

    }

    public static void testStream2(){
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted()
                .filter((s)->s.startsWith("A"))
                .forEach(System.out::println);
        boolean anyStartWithA = stringCollection.stream()
                .anyMatch((s)->s.startsWith("a"));
        System.out.println(anyStartWithA);
        boolean noneStartWithZ = stringCollection.stream().noneMatch((s)->s.startsWith("z"));
        System.out.println(noneStartWithZ);
        long startWithbCount=stringCollection.stream().filter((s)->s.startsWith("b")).count();
        System.out.println(startWithbCount);
        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1,s2)->s1+"#"+s2);
        reduced.ifPresent(System.out::println);

    }

    public static void testOptional(){
        Optional<String>  op = Optional.of("bam");
        System.out.println(op.isPresent());
        System.out.println(op.get());
        System.out.println(op.orElse("falback"));
        op.ifPresent((s)-> System.out.println(s.charAt(0)));
    }

    public static void testConsumer(){
        Consumer<Person> greeter = (p)-> System.out.println("hello"+p.firstName);
        greeter.accept(new Person("song","fei"));
    }

    public static void testSupplier(){
        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());
    }

    public static void testAndThen(){
       Function<String,Integer> toInteger = Integer::valueOf;
       Function<String,String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));

    }


    public static void testPredicate(){
        Predicate<String> predicate=(s)->s.length()>0;
        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo1"));
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNuLL = Objects::isNull;
        System.out.println(nonNull.test(null));
        System.out.println(isNuLL.test(Boolean.TRUE));
    }


    public static void testConvert(){
        //局部变量隐含 final
        int num=1;
        Converter<Integer,String> stringIntegerConverter = (from)->String.valueOf(from+num);
//        num=3; num 为 final,可以看为内部类
}

    public static void  testPersonFactory(){
        PersonFactory<Person> pf = Person::new;
        Person person = pf.create("fei","song");
    }

    public static void testFunctionalInterface(){
        Converter<String,Integer> converter = (from -> Integer.valueOf(from));
        System.out.println(converter.convert("123"));
//        converter=somthing::startWith;
//        String converted = converter.convert("Java");
//        System.out.println(converted);
    }
    public static void testFormula (){
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a*100);
            }
        };
        formula.calculate(100);
        formula.sqrt(100);
    }
}

@FunctionalInterface
interface Converter<F,T>{
    T convert(F from);
}


class Person{
    String firstName;
    String lastName;
    Person(){}
    Person(String firstName,String lastName){
       this.firstName=firstName;
       this.lastName=lastName;
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName,String lastName);
}

class Lambda4{
    //静态变量
    static  int outStaticNum=0;
    //实例变量
    int outerNum=0;
    void testScopes(){
        Converter<Integer,String> sconv1=(from)->{
            outerNum=23;
            return String.valueOf(from);
        };
        Converter<Integer,String> sconv2=(from)->{
            outStaticNum=72;
            return String.valueOf(72);
        };
        System.out.println(outerNum);
        System.out.println(outStaticNum);
        outerNum=5;

    }
}
