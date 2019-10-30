package com.sf.jkt.k.algorithm.face.cas

import java.math.BigInteger

fun main() {
    var arr2 = intArrayOf(1, 2, 3)
    arr2.reverse()
    arr2.forEach { print(it) }
    println()
    testAdd1()
    println()
}

fun testAdd1() {
    var arr2 = intArrayOf(9, 9, 9, 9, 9)
    var arr1 = intArrayOf(9, 9, 9)
    arr2 = intArrayOf(9, 8, 7, 6, 5)
    arr1 = intArrayOf(5, 6, 7, 8)
    testAdd(arr1, arr2).forEach { print(it) }
}


/**
 *  arr1=99999
 *  arr2=999
 */

fun testAdd(arrL1: IntArray, arrL2: IntArray): IntArray {
    // assert arr1.length>arr2.length
    var arr1 = arrL1
    var arr2 = arrL2
    if (arr1.size < arr2.size) {
        var temp = arr1
        arr1 = arr2
        arr2 = temp
    }
    arr1.reverse()
    arr2.reverse()

    var remain = 0
    for ((index, e) in arr1.withIndex()) {
        if (index < arr2.size) {
            var temp = e + arr2[index] + remain
            if (temp < 10) {
                arr1[index] = temp
            } else {
                arr1[index] = temp - 10
                remain = 1
            }
        } else {
            var temp = e + remain;
            if (temp < 10) {
                arr1[index] = temp
            } else {
                arr1[index] = temp - 10
                remain = 1
            }
        }
    }
    if (remain == 1) {
        var arr3 = IntArray(arr1.size + 1)
        for ((index, e) in arr1.withIndex()) {
            arr3[index] = e
        }
        arr3[arr1.size] = 1
        arr3.reverse()
        return arr3
    } else {
        arr1.reverse()
        return arr1
    }
}


fun testFload() {
    var i1 = Integer.MAX_VALUE
    val i2 = Integer.MAX_VALUE
    val i3 = i1 * i2
    println(i3)
    var i4 = i1 + i2;
    println(i4)
    println(i4 + 4)
    var bi1 = BigInteger.valueOf(i1.toLong())
    var bi2 = BigInteger.valueOf(i2.toLong())
    var bi3 = bi1.add(bi2)
    println(bi3)
    var bi4 = bi1.multiply(bi2)
    println(bi4)
    println(Long.MAX_VALUE)
    println(Double.MAX_VALUE)
}