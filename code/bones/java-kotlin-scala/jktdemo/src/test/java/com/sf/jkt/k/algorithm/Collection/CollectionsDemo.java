package com.sf.jkt.k.algorithm.Collection;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/mingyuli/article/details/79586855
 */
public class CollectionsDemo {
    static List<String> list= Arrays.asList("b","d","c","a","b","d","c","a");
    static class MyOpt implements UnaryOperator<String>{
        @Override
        public String apply(String s) {
            return s+"1111";
        }
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    static class UserOrderd implements Comparable{
        int id;
        String name;
        int age;
        String compareBy;

        @Override
        public int compareTo(@NotNull Object o) {
            UserOrderd u2=(UserOrderd) o;
            if(compareBy.equals("name")){
                return this.name.compareTo(u2.name) ;
            }
            if(compareBy.equals("age")){
                return this.age-u2.age;
            }
            return this.id-u2.id;
        }


    }

    static  enum Color{
        Black,BLUE,RED;
    }

    @Test
    public void testMap(){
        UserOrderd u1 =new UserOrderd(1,"sf",18,"name");
        UserOrderd u2 =new UserOrderd(1,"gd",18,"name");
        TreeMap<UserOrderd,String> treeMap=new TreeMap<>();
        treeMap.put(u1,"sf");
        treeMap.put(u2,"gd");
        System.out.println(treeMap);
    }

    /***
     *
     */
    @Test
    public void testComparator(){
        System.out.println("aa".compareTo(null));
        list.add("a");
        list.add("b");
        System.out.println(list);
    }

    @Test
    public void testCollections1(){
        final ArrayList<String> strings = Lists.newArrayList("a", "b", "a", "c");
        System.out.println(strings);
        strings.removeIf(x->x.equals("c"));
        System.out.println(strings);
        strings.removeAll(Collections.singletonList("a"));
        System.out.println(strings);
        //交换
        System.out.println(list);
        Collections.swap(list,0,1);
        System.out.println(list);
        final ArrayList<String> dest = new ArrayList<>(Arrays.asList(new String[strings.size()]));
        Collections.copy(dest,strings);
        System.out.println(dest);
        //复制
        //翻转
        Collections.reverse(list);
        System.out.println(list);
        Comparator comparator=Comparator.naturalOrder();
        Comparator comparator1=Comparator.reverseOrder();
        final Comparator comparator2 = Collections.reverseOrder(comparator);
        //排序
        Collections.sort(list,comparator);
        System.out.println(list);
        Collections.sort(list,comparator1);
        System.out.println(list);
        Collections.sort(list,comparator2);
        System.out.println(list);

    }

    /***
     * https://blog.csdn.net/u012501054/article/details/80774521
     */
    @Test
    public void testDisJoint(){
        List<String> list1 = Arrays.asList("aa","bb");
        List<String> list2= Arrays.asList("bb","cc");
        //没有交集
        System.out.println(Collections.disjoint(list1,list2));
        List<String> list3=new ArrayList<>(Arrays.asList(new String[list1.size()]));
        List<String> list4=new ArrayList<>(Arrays.asList(new String[list1.size()]));
        System.out.println(list3.retainAll(list4));
        Collections.copy(list3,list1);
        Collections.copy(list4,list2);
        System.out.println(list3);
        System.out.println(list4);
        //retain 做交集
        System.out.println(list3.retainAll(list4));
        System.out.println(list3);
        List<String> list =Collections.nCopies(5,"hello");
        System.out.println(list);

    }

    /**
     * https://blog.csdn.net/yaomingyang/article/details/80660378
     *
     */
    @Test
    public void testCollections(){
        List<String> list=new ArrayList<>();
        list.add("aa");
        list.add("bb");
        List objl=list;
        objl.add(new Date());
        System.out.println(objl);
        List checkedList=Collections.checkedList(list,String.class);
        checkedList.add(new Date());
        System.out.println(checkedList);
    }

    @Test
    public void testQueue2()throws Exception {
        SynchronousQueue sq = new SynchronousQueue<>();
        Runnable r1 = () -> {
            try {
                System.out.println(sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable r2 = () -> {
            try {
                sq.put("aaa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(r1).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(r2).start();
        TimeUnit.SECONDS.sleep(1);
    }

    /***
     * https://www.cnblogs.com/lemon-flm/p/7877898.html
     * Ex : add remove elment
     * null : offer pull
     * block : put take
     */
    @Test
    public void testQueue(){
        LinkedList list1 = new LinkedList();
        list1.addLast("good");
        list1.addLast("XX");
        list1.addFirst("hello");
        list1.offer("aaa");
        list1.offer("bbb");

        System.out.println(list1.element());


        System.out.println(list1);
        Comparator comparator=Comparator.naturalOrder();
        PriorityQueue<String> priorityQueue = new PriorityQueue<String>(comparator);
        priorityQueue.offer("a");
        priorityQueue.offer("b");
        priorityQueue.offer("c");
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
        ConcurrentLinkedQueue cq= new ConcurrentLinkedQueue<>();
        //公平：等待时间最长的任务先处理
        ArrayBlockingQueue arrayBlockingQueue= new ArrayBlockingQueue<>(10, true);

    }

    /***
     * https://blog.csdn.net/moakun/article/details/80617845
     */

    @Test
    public void testSet1(){
        EnumSet set=EnumSet.of(Color.BLUE,Color.RED);
        System.out.println(set);
        EnumSet enum2=EnumSet.complementOf(set);
        System.out.println(enum2);
        HashSet hs1=new HashSet<String>();
        hs1.add("a");
        hs1.add("a");
        hs1.add("b");
        System.out.println(hs1);
        LinkedList<String> ll=new LinkedList<>();
        System.out.println(ll.poll());
        LinkedHashSet lhs = new LinkedHashSet<>();


    }

    @Test
    public void testCollection2(){
        ArrayList list =new ArrayList<String>();
        list.add("a");
        list.get(0);
        LinkedList list1=new LinkedList();
        list1.addFirst("first");
        list1.addLast("last");
        System.out.println(list1.getFirst());
        System.out.println(list1.getLast());

        /**
         * ex add remove element
         * null offer pull peek
         * block put take; 针对 block
         */
        System.out.println(list1.element());
        System.out.println(list1.peek());

        Vector vector = new Vector();
        vector.add("a");
        vector.add("b");
        System.out.println(vector.firstElement());
        String[] str=new String[vector.size()];
        vector.copyInto(str);
        System.out.println(str);
        System.out.println(vector.toArray());
        System.out.println(vector.toArray(str));

    }

    @Test
    public void testBlockingQueue()throws Exception{
        /***
         * exception:add remove element
         * null: offer poll peek
         * block: put take
         * times offer(e,time,unit) poll(e,time,unit)
         */
        ArrayBlockingQueue<String> ab=new ArrayBlockingQueue<String>(2);
        ab.put("a");
        ab.put("b");

        ArrayBlockingQueue ab2 = new ArrayBlockingQueue(list.size(),false,list);
        System.out.println(ab2);
        List<String> list=new ArrayList<>();
        ab2.drainTo(list);
        System.out.println(list);
    }



    @Test
    public void testList(){
       list.replaceAll(new MyOpt());
        System.out.println(list);
    }

    /**
     * https://blog.csdn.net/cyywxy/article/details/81634280
     */
    @Test
    public void testIterator(){
        final Object[] objects = list.toArray();
        Arrays.stream(objects).forEach(System.out::println);
        String[] sa=new String[list.size()];
        list.toArray(sa);
        System.out.println(Arrays.stream(sa).collect(Collectors.toList()));
        list=new ArrayList<>(list);
        final ListIterator<String> stringListIterator = list.listIterator();
        System.out.println(stringListIterator.next());
        System.out.println(stringListIterator.nextIndex());
        System.out.println(stringListIterator.previous());
        System.out.println(stringListIterator.previousIndex());
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(list);
    }

    @Test
    public void testRetain(){
        Collection list1 = new ArrayList();
        list1.add("q1");
        list1.add("q2");
        list1.add("q3");
        list1.add("q4");
        Collection list2 = new ArrayList();
        list2.add("q1");
        list2.add("q2");
        list2.add("q3");
        list2.add("q5");
        // list1与list2做交集，结果集与list2做比较，如果相同返回true，否则返回false
        // System.out.println("containAll:" + list1.containsAll(list2));
        // retain是保留的意思，list1与list2做交集，结果集赋值给list1，如果list1被改变返回true，否则返回false
        System.out.println("retainAll:" + list1.retainAll(list2));//交集放回 list1
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
    }

    /***
     * https://blog.csdn.net/u010126792/article/details/62236367
     */
    @Test
    public void testNavigableSet(){

        System.out.println(list);
        final Spliterator<String> spliterator = list.spliterator();
        NavigableSet set=new TreeSet<String>();
        set.add("aa");
        set.add("bb");
        set.add("cc");
        set.add("dd");
        set.add("ee");
        System.out.println(set);
        System.out.println(set.ceiling("cc"));
        System.out.println(set.ceiling("cc"));
        System.out.println(set.ceiling("cd"));
        System.out.println(set.floor("ca"));

    }

    @Test
    public void testCollection1(){
        System.out.println(Collections.frequency(list,"a"));
        System.out.println(Collections.binarySearch(list, "d"));
        System.out.println(Collections.replaceAll(list,"b","wo"));
        System.out.println(Collections.replaceAll(list,"e","wowo"));
        System.out.println(list);
        Collections.rotate(list,1);
        System.out.println(list);
        Collections.swap(list,0,1);
        System.out.println(list);
    }
    @Test
    public void testCollection(){
        System.out.println(Collections.lastIndexOfSubList(list,Arrays.asList("b","d")));
        System.out.println(Collections.indexOfSubList(list,Arrays.asList("b","d")));
        System.out.println(Collections.indexOfSubList(list,Arrays.asList("b","c")));
        System.out.println(Collections.indexOfSubList(list, Arrays.asList("d")));
        System.out.println(Collections.min(list));
        System.out.println(Collections.max(list));
        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list, Comparator.naturalOrder());
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        Collections.fill(list,"me");
        System.out.println(list);
    }



}
