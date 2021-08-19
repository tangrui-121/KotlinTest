package com.person.kotlintest.泛型

import java.util.*

/**
 * @anthor tr
 * @date 2021/7/1
 * @desc
 */

fun addInt(x: Int, y: Int): Int {
    return x + y
}

fun addFloat(x: Float, y: Float): Float {
    return x + y
}

fun main() {


    val list1 = ArrayList<String>()
    list1.add("abc")

    val list2 = ArrayList<Int>()
    list2.add(123)

    println(list1.javaClass == list2.javaClass)
}


