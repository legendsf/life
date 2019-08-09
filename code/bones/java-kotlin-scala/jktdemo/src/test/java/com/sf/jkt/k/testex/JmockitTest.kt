//package com.sf.jkt.k.testex
//
//import mockit.Expectations
//import mockit.Mocked
//import mockit.Verifications
//import mockit.integration.junit4.JMockit
//import mockit.internal.expectations.argumentMatching.ArgumentMatcher
//import org.junit.Assert
//import org.junit.Test
//import org.junit.runner.RunWith
//
//class MyObject {
//    fun hello(name: String): String {
//        return "hello:" + name
//    }
//}
//
//
//@RunWith(JMockit::class)
//class JmockitTest {
//    @Mocked
//    lateinit var obj: MyObject
//
//    @Test
//    fun testHello() {
//        var expect = object : Expectations() {
//            init {
//                obj.hello("zhangsan")
////                returns("hello zhangsan")
//            }
//        }
////        assert("hello zhangsan")
//    }
//
//    @Test
//    fun mockPersonTest(@Mocked target: PersonService) {
//        var expect = object : Expectations() {
//            init {
//                target.showName(anyString)
//                result = "test1"
//                target.showAge(anyInt)
//                result = -1
//            }
//        }
//
//        Assert.assertTrue("test1".equals(target.showName("test2")))
////        Assert.assertTrue(-1!=target.showAge(20))
//        Assert.assertTrue(-1 == target.showAge(20))
//        Assert.assertTrue(-1 == target.showAge(20))
//
//        var verif = object : Verifications() {
//            init {
//                target.showName("test1")
//                times = 0
//                target.showAge(12)
////                times=1
//                times = 0
//                target.showAge(20)
//                times = 2
//            }
//        }
//    }
//}