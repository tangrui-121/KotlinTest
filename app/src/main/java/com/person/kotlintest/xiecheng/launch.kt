package com.person.kotlintest.xiecheng

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 * @anthor tr
 * @date 2021/4/26
 * @desc
 */
fun main1() {
    val `object`: String = ""

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

suspend fun main4() {
    val job = GlobalScope.launch {
        delay(1000L)
        println("world")
    }
    println("hello")
    job.join() //不需要显示的sleep多长时间  join会等到子线程结束再finish
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

suspend fun main7() {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 在延迟后退出
}

fun main8() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(3300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
    job.cancel() // 取消该作业
    job.join() // 等待作业执行结束
    println("main: Now I can quit.")
}

fun main9() = runBlocking{
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
            // 每秒打印消息两次
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消一个作业并且等待它结束
    println("main: Now I can quit.")
}

fun main() {
    //线程代码
    println("Start ${Thread.currentThread().name}")
    Thread {
        Thread.sleep(1000L)
        println("Hello World ${Thread.currentThread().name}")
    }.start()
    println("End ${Thread.currentThread().name}")
}