package com.sf.jkt.k.testex


open class Person(open var name: String, open var age: Int, open var friend: Person?) {
    override fun equals(other: Any?): Boolean {
        if (other !is Person) {
            return false
        }
        return this.name.equals(other.name)
    }
}

class PersonService{
    fun showName(name: String):String{
        println("Person show name: "+name)
        return name
    }
    fun showAge(age:Int):Int{
        println("Person show age:"+age)
        return age
    }
    fun getDefaultPerson():Person{
        return Person("miao",3,null)
    }
}

class CoderService{
    var desc="i am a big star"
    fun showWork(work:String):String{
        return work
    }
    fun showSalary(salary:Int):Int{
        return salary
    }
    fun getPersonName(person:Person):String{
        return person.name
    }
}

class PersonTest:Person(name = "defaultName",age=12,friend = null){

}

open class Demo {
    open var num = 3
    open fun foo() = "foo"
    open fun bar() = "bar"
}

class DemoTest : Demo() {
}


//class

fun main() {
    val person = Person("name", 10, null)
    println(person)
    println(DemoTest() is Demo)
    println(DemoTest() is DemoTest)
    println(PersonTest().name)
}