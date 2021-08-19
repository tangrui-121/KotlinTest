package com.person.kotlintest.委托

/**
 * @anthor tr
 * @date 2021/6/25
 * @desc class <类名>(b : <基础接口>) : <基础接口> by <基础对象>
 *
 *     一个类的方法不在该类中定义，而是直接委托给另一个对象来处理
 */

interface Base {
    fun print()
}

class BaseImpl(val value: String) : Base {
    override fun print() {
        println(value)
    }
}

class B(impl: BaseImpl) : Base by impl{

}

fun main() {
    val b = BaseImpl("nihao")
    B(b).print()// 最终调用了 Base#print()
}