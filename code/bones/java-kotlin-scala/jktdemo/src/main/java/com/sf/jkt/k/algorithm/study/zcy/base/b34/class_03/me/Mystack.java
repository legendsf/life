package com.sf.jkt.k.algorithm.study.zcy.base.b34.class_03.me;

import java.util.Stack;

public class Mystack {
    static class  MArrayStack<T>{
        Object[] objs;
        int size=0;

        public MArrayStack(int capacity) {
            this.objs = new Object[capacity];
        }

        public void push(T t){
            if(size>objs.length-1){
                throw  new RuntimeException("stack full");
            }
            objs[size++]=t;
        }

        public T  pop(){
            if(size>0){
                T t=(T)objs[size];
                objs[size]=null;
                size--;
                return t;
            }else {
                return null;
            }
        }

    }

    public static class MArrayStack2<T>{
        Object[] objs;
        int size;

        public MArrayStack2(int capacity) {
            objs=new Object[capacity];
            size=0;
        }
        //push
        public void push(T t){
            if(size==objs.length){
                throw new RuntimeException("stack is full");
            }
            objs[size++]=t;
        }
        //pop
        public T pop(){
            if(size==0){
                return null;
            }
            T r=(T)objs[--size];
            objs[size]=null;
            return r;
        }
        //peek
        public T peek(){
            if(size==0){
                return null;
            }
            return (T)objs[size-1];
        }
    }

    public static class MArrayQueue<T>{
        Object[] objs;
        int size;
        int first;
        int last;

        public MArrayQueue(int capacity) {
            objs=new Object[capacity];
            size=0;
            first=0;
            last=0;
        }
        //push
        //尾部进入，头部出
        public void push(T t){
            if(size==objs.length){
                throw new RuntimeException("queue is full");
            }
            size++;
            objs[last]=t;
            last= last==objs.length-1?0:last+1;
        }


        //pop
        public T pop(){
            if(size==0){
                return null;
            }
            T t=(T)objs[first];
            objs[first]=null;
            size--;
            first= first==objs.length-1 ? 0: first+1;
            return t;
        }
        //peek
        public T peek(){
            if(size==0){
                return null;
            }
            return (T)objs[first];

        }

    }

    public static class MArrayQueue2<T>{
        Object[] objs;
        int size;
        int first;
        int last;

        public MArrayQueue2(int capacity) {
            if(capacity<0){
                throw new IllegalArgumentException("CAPACITY LE 0");
            }
            objs=new Object[capacity];
            size=0;
            first=0;
            last=0;
        }

        //push
            public void push(T t){
                if(size==objs.length){
                    throw new RuntimeException("QUEUE IS FULL");
                }
                size++;
                objs[last]=t;
                last= last==objs.length-1?0:last+1;
            }
        //pop
            public T pop(){
                if(size==0){
                    return null;
                }
                T t = (T)objs[first];
                objs[first]=null;
                size--;
                first = first== objs.length-1? 0: first+1;
                return t;
            }
        //peek
            public T peek(){
                if(size==0){
                    return null;
                }
                return  (T)objs[first];
            }

    }

    //minStack

    public static class  MinStack<T extends Comparable>{
        private Stack<T> data=new Stack<>();
        private Stack<T> mins =new Stack<>();

        public MinStack() {
        }

        //push
        public void push(T obj){
            if(obj==null){
                throw new IllegalArgumentException("NULL NOT ALLOWED");
            }
            this.data.push(obj);
            if(mins.isEmpty()){
                this.mins.push(obj);
            }else {
                if(this.getmin().compareTo(obj)>=0){
                   this.mins.push(obj);
                }
            }
        }

        //pop
        public T pop(){
           T t= this.data.pop();
           if(this.getmin().compareTo(t)==0){
             this.mins.pop();
           }
           return t;
        }
        //peek
        public T peek(){
            return this.data.peek();
        }
        //getmin
        public T getmin(){
            return this.mins.peek();
        }
    }



    public static void main(String[] args) {
//        testArrayStakQueue();
        testGetMin();
    }

    private static void testArrayStakQueue() {
        //        MArrayStack2<String> ms= new MArrayStack2<>(2);
//        MArrayQueue<String> ms= new MArrayQueue<>(2);
        MArrayQueue2<String> ms= new MArrayQueue2<>(2);
        ms.push("a");
        ms.push("b");
        try{
            ms.push("c");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(ms.pop());
        System.out.println(ms.peek());
        System.out.println(ms.pop());
        System.out.println(ms.pop());
    }

    public static void testGetMin(){
        MinStack stack2 = new MinStack();
        stack2.push(3);
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.pop());
        System.out.println(stack2.pop());
    }
}
