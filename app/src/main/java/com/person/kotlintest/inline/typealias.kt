package com.person.kotlintest.inline

import java.io.File

/**
 * @anthor tr
 * @date 2021/6/8
 * @desc
 */
public typealias A = String
public typealias B = File

fun main() {
    val name: A = "ZhangSan"
    val name1: String = "ZhangSan"
    val myFile = B("myPath")
    val myFile1 = File("myPath")

    println("name==name1 : ${name == name1}")
    println("myFile==myFile1 : ${myFile == myFile1}")
}