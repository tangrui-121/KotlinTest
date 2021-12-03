package com.person.kotlintest.Hilt

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.person.kotlintest.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class HiltActivity : AppCompatActivity() {

//    /**
//     * 例：全局使用的线程池、retrofit对象
//     * 以往：放在application中、单例对象
//     */
//    @Inject
//    lateinit var executor: ExecutorService
//
//    @Inject
//    lateinit var myA: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_hilt)
//
//        doTask()
//    }
//
//    fun doTask() {
//        Log.e("Hilt 全局共享 ", myA)
//
//        executor.execute {
//
//        }
//    }
//
//
//    /**
//     * 例：局部共享，ac的数据共享给view或者fragment
//     * 以往：强转 (context as MainActivity).user.name
//     */
//    @AndroidEntryPoint
//    class UserActivity : AppCompatActivity() {
//        @Inject
//        lateinit var user: String
//    }
//
//    @AndroidEntryPoint
//    class UserView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
//        @Inject
//        lateinit var user: String
//
//        fun display() {
//            Log.e("Hilt 局部共享 ", user)
//        }
//    }
}