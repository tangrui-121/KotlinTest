package com.person.kotlintest.dsl

/**
 * @anthor tr
 * @date 2021/5/20
 * @desc
 */
class Dependency {

    val libs = mutableListOf<String>()
    fun implementation(lib: String) {
        libs.add(lib)
    }

    fun dependencies(block: Dependency.() -> Unit): List<String> {
        val dependency = Dependency()
        dependency.block()
        return dependency.libs
    }
}

fun main() {
    val list = Dependency().dependencies {
        implementation("11111111111111111")
        implementation("22222222222222222")
        implementation("33333333333333333")
    }
    for (a in list) {
        println(a)
    }

    val user = User("tangrui", 28)
    println(user.toString())

    val user1 = User().create {
        name = "tangrui1"
        age = 29
    }
    println(user1.toString())
}

data class User(var name: String = "", var age: Int = 0) {
    override fun toString(): String {
        return "my name is $name, i am $age years old"
    }

    fun create(block: User.() -> Unit): User {
        val user = User()
        block(user)
        return user
    }
}