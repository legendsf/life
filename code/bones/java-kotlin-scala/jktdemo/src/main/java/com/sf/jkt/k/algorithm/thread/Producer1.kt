package com.sf.jkt.k.algorithm.thread

import java.util.concurrent.TimeUnit

class Person1{
    var name:String=""
    var age:Int=0
    var isEmpty:Boolean=true

    var lock=java.lang.Object()
    fun produce(name:String,age:Int)= synchronized(lock){
        while(!isEmpty){
            lock.wait()
        }
        this.name="sf"
        Thread.sleep(100)
        this.age=12
        this.isEmpty=false
        lock.notifyAll()
    }

    fun consume()= synchronized(lock){
        while(this.isEmpty){
            lock.wait()
        }
        Thread.sleep(10)
        println("name:"+name+"|age:"+age)
        this.isEmpty=true
        lock.notifyAll()
    }

}

fun main() {
    val person=Person()
    Thread{
       for(i in 0..50){
           if(i%2==0){
               person.produce("sf",11)
           }else{
               person.produce("gd",12)
           }
       }
    }.start()

    Thread{
        for(i in 0..50){
            person.consume()
        }
    }.start()
    TimeUnit.SECONDS.sleep(10)
}