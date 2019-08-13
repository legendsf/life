package com.sf.jkt.k.mock

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class MockitoTest{

    @Test
    fun createMockObject(){
        //mock interface
       var mockedList=mock(List::class.java)
        Assert.assertTrue(mockedList is List)
       //mock obj
        var mockedArrayList=mock(ArrayList::class.java)
        Assert.assertTrue(mockedArrayList is ArrayList)
    }
    @Test
    fun testConfigMockObject(){

    }

    @Test
    fun testArray(){
        var arr= mutableListOf("a","b")
    }
}