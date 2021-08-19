package com.person.kotlintest.inline

/**
 * @anthor tr
 * @date 2021/6/8
 * @desc
 */
//let 源码
//public inline fun <T, R> T.let(block: (T) -> R): R {
//    contract {
//        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//    }
//    return block(this)
//}

public fun <T, R> T.let1(block: (T) -> R): R {
    return block(this)
}

/**
 * 调用以上两个方法 再查看字节码
 * inline会将方法平铺  也就导致了 可以直接return出循环体
 * 非inline的 则直接创建了一个function  return也只能退出当前ret
 * */

//在处理高阶函数时
//jvm会创建functi 为了避免频繁的创建，可以考虑使用inline

fun aaa(){
    val a = 0
    while (1 == 1){
        a.let{
            return
        }

    }

    while (1 == 1){
        a.ret{
            return@ret
        }
    }
}

inline fun <reified T, R> T.det(block: (T) -> R): R {
    val a = 0
    if (a is T) {//can

    }
    return block(this)
}

fun <T, R> T.ret(block: (T) -> R): R {
    val a = 0
//    if (a is T) {// error: Cannot check for instance of erased type: T
//
//    }
    return block(this)
}

inline fun <reified T> membersOf() = T::class.members


//对于inline函数中多个其他函数 可以使用noinline来不使用inline的优化

//在这里我们发现通过inline修饰的方法，会通过平坦式的方式直接在后面按执行顺依次调用。
//
//而没有使用inline修饰的方法，则会为block方法创建一个Function1实例。
//
//简单的理解就是未使用inline修饰的方式，会对带有函数式参数的方法，创建对于函数的实例，再将这个实例传递到方法参数中。该参数方法最终在原方法的内部被显示调用。
//
//所以inline做的优化就是将带有函数参数的方法简化成没函数式参数的直接调用。好处是提高程序的性能。
//
//当然需要注意的是，避免使用inline内联大型函数，减少方法中代码的增长。


fun main() {
    val indexOf = "che?300://ope?n/native/insurance_query?vin=LBV2Y3104JMK96049".indexOf("?", 4)
    println(indexOf)
}

