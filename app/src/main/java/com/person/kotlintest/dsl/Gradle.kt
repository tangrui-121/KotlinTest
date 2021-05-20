package com.person.kotlintest.dsl

/**
 * @anthor tr
 * @date 2021/5/20
 * @desc  万DSL基于扩展
 */

class Gradle {

    private val android_ = Android()
    fun android(android: Android.() -> Unit) {
        android_.apply(android)
    }

    private val dependencies_ = Dependencies()
    fun dependencies(dependencies: Dependencies.() -> Unit) {
        dependencies_.apply(dependencies)
    }

    operator fun invoke(gradle: Gradle.() -> Unit) {
        gradle()
    }

}

class Android {

    var compileSdkVersion: Int = 0
    var buildToolsVersion: String = ""

}

class Dependencies {
    fun implementation(jar: String) {
        // print
    }
}


fun test(gradle: Gradle) {

    gradle.invoke {

    }

    gradle {

        android {

        }

        android {
            compileSdkVersion = 30
            buildToolsVersion = "30.0.3"
        }

        dependencies {
            implementation("com.squareup.okhttp3:okhttp:3.12.0")
        }
    }
}


