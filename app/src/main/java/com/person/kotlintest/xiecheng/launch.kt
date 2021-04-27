package com.person.kotlintest.xiecheng

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 * @anthor tr
 * @date 2021/4/26
 * @desc
 */
fun main1() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("【${Thread.currentThread().name}】World!") // 在延迟后打印输出
    }
    println("【${Thread.currentThread().name}】Hello,") // 协程已在等待时主线程还在继续
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活

    runBlocking {
        delay(2000L)
    }

    thread {
        Thread.sleep(1000L)
    }
}

fun main2() = runBlocking {
    GlobalScope.launch {
        delay(1000L)
        println("world")
    }
    println("hello")
    delay(2000L)
}

//不要join
fun main3() = runBlocking { // this: CoroutineScope
    launch { // 在 runBlocking 作用域中启动一个新协程
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

suspend fun main4(){
    val job = GlobalScope.launch {
        delay(1000L)
        println("world")
    }
    println("hello")
    job.join()
}

fun main5() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope { // 创建一个协程作用域
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}

fun main6() = runBlocking {
    repeat(100_000) { // 启动大量的协程
        launch {
            delay(5000L)
            print(".")
        }
    }
}

suspend fun main() {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 在延迟后退出
}