package com.person.kotlintest.kotlinnull

/**
 * @anthor tr
 * @date 2021/6/23
 * @desc
 */

open class Parent(open var name: String) {
    var nameLength: Int

    init {
        nameLength = name.length
    }
}

class Child(override var name: String) : Parent(name) {

    init {
        nameLength = name.length
    }
}

fun main(args: Array<String>) {
    Parent("xujiafeng")
    Child("xujiafeng")
}