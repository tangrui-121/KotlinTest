package com.person.kotlintest.委托

import kotlin.reflect.KProperty

/**
 * @anthor tr
 * @date 2021/6/25
 * @desc val/var <属性名> : <类型> by <基础对象>
 *
 *      一个类的属性不在该类中定义，而是直接委托给另一个对象来处理
 */

class Example {
    //被委托属性
    var prop: String by Delegate()
}

//基础类
class Delegate {
    private var _realValue: String = "tang"

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("getValue")
        return _realValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("setValue")
        _realValue = value
    }
}

fun main() {
    val e = Example()
    print(e.prop)
    e.prop = "rui"
    println(e.prop)
}
