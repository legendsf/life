package com.sf.jkt.k.algorithm.thread



class Person{
    var name:String=""
    var age:Int=0
    var isEmpty:Boolean=true
    var lock=java.lang.Object()

    fun produce(name:String,age:Int)= synchronized(lock){
        while (!isEmpty){
            lock.wait()
        }
        this.name=name
        Thread.sleep(100)
        this.age=age
        isEmpty=false
        lock.notifyAll()
    }

    fun consume()= synchronized(lock){
        while(isEmpty){
            lock.wait()
        }
        Thread.sleep(10)
        println("name:"+this.name+"|age:"+this.age)
        isEmpty=true
        lock.notifyAll()
    }

}

fun main() {
    var person=Person()
    Thread{
        for(i in 0..50){
            if(i%2==0){
                person.produce("Tom",11)
            }else{
                person.produce("larry",21)
            }

        }
    }.start()
    Thread{
        for(i in 0..50){
            person.consume()
        }
    }.start()

    Thread.sleep(20000)
}