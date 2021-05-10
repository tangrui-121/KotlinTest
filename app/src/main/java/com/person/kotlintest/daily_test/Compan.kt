package com.person.kotlintest.daily_test

class Compan {

    init {

    }

    companion object {
        const val aaa = "aaa"

        fun bbb() {
            println("bbb")
        }

        class CCC {
            fun ccc(ccc: String) {
                println(ccc)
            }
        }
    }

    object DDD {
        const val ddd = "ddd"

        fun eee() {
            println("eee")
        }

        class FFF {
            fun fff(fff: String) {
                println(fff)
            }
        }
    }
}

fun main(args: Array<String>) {
    Compan.bbb()
    println(Compan.aaa)
    Compan.Companion.CCC().ccc("ccc")

    Compan.DDD.eee()
    println(Compan.DDD.ddd)
    Compan.DDD.FFF().fff("fff")


}