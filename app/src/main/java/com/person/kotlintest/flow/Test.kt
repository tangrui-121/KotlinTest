package com.person.kotlintest.flow

import com.person.kotlintest.inline.aaa
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

suspend fun main() {
    runBlocking {
        flowOf(1, 2, 3, 4, 5)
            .onEach {
                delay(it * 100L)
            }
            .collect {
                println(it)
            }


        flow {
            for (i in 6..11) {
                delay(100)
                emit(i)
            }
        }.collect{
            println(it)
        }
    }
}