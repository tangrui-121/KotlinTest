package com.person.kotlintest.委托

import kotlin.properties.Delegates

/**
 * @anthor tr
 * @date 2021/6/25
 * @desc
 *
 *   一个局部变量不在该方法中定义，而是直接委托给另一个对象来处理
 */


fun main() {
    val aaa :String by lazy {
        println("init")
        "nihao"
    }

    "nihao".length
    String()

    println(aaa)
    println(aaa)

    val user: User = User()
    user.bbb = "rui"
    user.bbb = "li"
}

class User{
    var bbb: String by Delegates.observable("tang"){property, oldValue, newValue ->
        println("旧值：$oldValue -> 新值：$newValue")
    }
}
