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

