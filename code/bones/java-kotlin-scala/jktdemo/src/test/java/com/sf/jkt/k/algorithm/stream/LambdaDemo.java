package com.sf.jkt.k.algorithm.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://blog.csdn.net/weixin_30423977/article/details/95420484?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
 *
 * 这篇非常好
 * https://zhuanlan.zhihu.com/p/124973501
 *
 * https://blog.csdn.net/zl1zl2zl3/article/details/54133258
 *https://blog.csdn.net/weixin_42360600/article/details/105710674
 *
 * https://www.cnblogs.com/jing99/p/13711174.html
 * https://blog.csdn.net/qq_17231297/article/details/108271720
 *https://www.cnblogs.com/linzhanfly/p/9686941.html
 * https://blog.csdn.net/zoujiawei6/article/details/83511013
 * https://blog.csdn.net/icarusliu/article/details/79495534?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
 *
 *
 *
 */

public class LambdaDemo {
    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    static class Stu{
        String name;
        int age;
    }

    @Test
    public void test1(){
        Map map=new HashMap();
        map.put("k1","v1");
        map.put("k2","v2");
        map.forEach((k,v)->{
            System.out.println("key: "+k+";value: "+v);
        });
        Arrays.asList("a","b","c").stream().forEach(System.out::println);
        Runnable r2= ()-> System.out.println("hello");
        r2.run();
    }

    @Test
    public void testCompose(){
        Function<String, String> f=(x)->{
            System.out.println("本体");
            return x;
        };
      String str=  f.compose((x)->{
            System.out.println("before");
            return "before "+String.valueOf(x);
        }).apply("传入值");
        System.out.println(str);
    }

    @Test
    public void testOperator(){
        Comparator<Integer> comparator=(x1,x2)->{
            return x1-x2;
        };
        BinaryOperator<Integer> bo=BinaryOperator.maxBy(comparator);
        System.out.println(bo.apply(2,1));
    }

    @Test
    public void testFilter(){
        Stream<Integer> stream=Stream.of(1,2,3);
        stream.filter(i->i>2).forEach(System.out::println);
        Stream.of("a","b").map(o->"hello: "+o).forEach(System.out::println);
    }
    @Test
    public void testCollect(){
     List<String> result=   Stream.of("a","b","李四").filter(str->!str.equals("李四")).collect(Collectors.toList());
        System.out.println(result);
        System.out.println(Stream.of("aa","bb").map(x->x.toUpperCase()).collect(Collectors.toList()));
        List list=Arrays.asList("张三","李四","王五");
        System.out.println(list.stream().filter(str -> str.equals("李四")).findFirst());
        System.out.println("list.stream().filter(str->str.equals(\"李四\")).findAny() = " + list.stream().filter(str -> str.equals("李四")).findAny());
        System.out.println(list.stream().filter(str -> str.equals("里")).findAny().orElse("找不到：里"));

    }

    /***
     * https://www.cnblogs.com/lijingran/p/8727507.html
     */
    @Test
    public void testFlatMap(){
        System.out.println(Stream.of(Arrays.asList("a"), Arrays.asList("b", "c"), Arrays.asList("1", "2", "3"))
                .flatMap(Collection::stream).collect(Collectors.toList()));
        new Random().ints(1,20).limit(10).forEach(System.out::print);
        new Random().ints(1,20).limit(10).peek(System.out::println)
                .filter(v->v>10).forEach(System.out::println);
        System.out.println(Stream.of("a", "b").reduce("", String::concat));
        System.out.println(new Random().ints(1, 20).limit(10).mapToObj(x -> "|" + x).reduce("", String::concat));
        System.out.println(Stream.of(-1, -2, -3, -4).reduce(Integer.MAX_VALUE, Integer::min));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).get());
        System.out.println(Stream.of(1, 3, 4, 2).reduce(0, Integer::sum));

    }

    @Test
    public void testStream(){
        System.out.println(Arrays.stream(new int[]{1, 2, 3}));
        System.out.println(Arrays.stream(new int[]{1, 2, 3}).sum());
        System.out.println(Stream.of(1, 2, 3).skip(2).limit(2).collect(Collectors.toList()));
        System.out.println(Stream.of(1, 2, 3).sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
    }

    @Test
    public void testSorted(){
        System.out.println(Stream.of(1, 2, 3, 4, 5).skip(1).limit(5).filter(x -> x > 3).reduce(Integer::sum).get());
        System.out.println(Stream.of("1", "2", 3).map(x->""+x).mapToInt(Integer::valueOf).sum());
        Stream.of(1,2,3).mapToInt(Integer::intValue).max().ifPresent(System.out::println);
        Stream.of(1,1,2,3,2,4,3).distinct().forEachOrdered(System.out::println);
    }
    @Test
    public void testMatch(){
        System.out.println(Stream.of(1, 2, 3).anyMatch(x -> x == 1));
        System.out.println(Stream.of(1, 2, 3).noneMatch(x -> x == 10));
        System.out.println(Stream.of(1, 1).allMatch(x -> x == 1));

    }

    @Test
    public void testCollect1(){
        System.out.println(Stream.of(1, 2, 3).map(Object::toString).collect(Collectors.joining(",")));
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.groupingBy(x -> x > 5)));
        Stu stu=new Stu("sf",11);
        Stu stu3=new Stu("sf",21);
        Stu stu1=new Stu("gd",12);
        Stu stu4=new Stu("gd",22);
        System.out.println(Arrays.asList(stu, stu1, stu3, stu4).stream().collect(Collectors.groupingBy(Stu::getName)));
        List stus=Arrays.asList(stu,stu1,stu3,stu4);
        System.out.println(stus.stream().collect(Collectors.groupingBy(Stu::getName, Collectors.averagingInt(Stu::getAge))));
//        System.out.println(stus.stream().collect(LinkedList::new, List::add, LinkedList::addAll));


    }

    @Test
    public void testGenerate(){
        Random random=new Random();
        Supplier<Integer> supplier=random::nextInt;
        System.out.println(Stream.generate(supplier).limit(10).collect(Collectors.toList()));
        IntStream.generate(()->(int)(System.nanoTime()%100)).limit(10).forEach(System.out::println);
        System.out.println(IntStream.range(1, 10).sum());

    }
    @Test
    public void testIntStream(){
        List<Integer> list = IntStream.range(0, 10).collect(ArrayList::new, List::add, List::addAll);
        System.out.println(IntStream.range(0, 10).boxed().collect(Collectors.toList()));
    }



    @Test
    public void testIterate(){
        System.out.println(Stream.iterate(0, n -> n + 3).limit(10).collect(Collectors.toList()));
    }

}
