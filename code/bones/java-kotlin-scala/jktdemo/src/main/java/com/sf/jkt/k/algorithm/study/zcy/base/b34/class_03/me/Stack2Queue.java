package com.sf.jkt.k.algorithm.study.zcy.base.b34.class_03.me;

import java.util.LinkedList;
import java.util.Stack;

public class Stack2Queue {
    static class TwoStackQueue<T>{
        //STACK FILO
      private   Stack<T>pushStak=new Stack<>();
      private   Stack<T>popStack = new Stack<>();

        //queue methods
        //push 尾部进
        public void push(T t){
            this.pushStak.push(t);
            while (!pushStak.isEmpty()){
                this.popStack.push(this.pushStak.pop());
            }
        }
        //pop 头部出 FIFO
        public T pop(){
            return this.popStack.pop();
        }
        //peek
        public  T peek(){
            return this.popStack.peek();
        }
    }

    public static class TwoQueueStack{

    }




    public static void main(String[] args) {
//        testStack();
        testQueue();
    }

    public static void testQueue(){
        LinkedList list = new LinkedList();
        list.add("a");
        list.add("b");
        list.push("c");
        list.push("d");

        System.out.println(list);
        System.out.println(list.poll());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
//        System.out.println(list.pop());
    }


    public static void testStack(){
        TwoStackQueue<String> sq= new TwoStackQueue();
        sq.push("a");
        sq.push("b");
        sq.push("c");
        sq.push("c");
        System.out.println(sq.pop());
        System.out.println(sq.pop());
        System.out.println(sq.pop());
        System.out.println(sq.peek());
        System.out.println(sq.pop());
    }
}
