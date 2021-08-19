package com.person.kotlintest.Lambda

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.person.kotlintest.R
import com.person.kotlintest.databinding.ActivityLambdaBinding
import kotlinx.android.synthetic.main.activity_lambda.*
import java.math.BigDecimal

class LambdaActivity : AppCompatActivity(R.layout.activity_lambda) {

    private lateinit var binding: ActivityLambdaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLambdaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.aaaaa.setOnClickListener {

        }
        //只有一个函数可以省略括号 java实现里面不能搞
        aaaaa.setOnClickListener { }

        aaaaa.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }

        })

        //意思就是对于单个方法的java接口，可以简写成以接口类型作为前缀的Lambda表达式
        aaaaa.setOnClickListener(View.OnClickListener {


        })


//        对于最上面的写法的解释
//        SAM，即 Single Abstract Method ，就是对于只有单个非默认抽象方法接口 ，对于符合这个条件的接口称之为 SAM，这是Java 8 里的一个概念，Kotlin也是支持的官方文档里有一段对SAM的介绍，这里做一个总结：
//
//        Java 中的SAM在 Kotlin 中可以直接用 Lambda 来表示 ，当然前提是 Lambda 的所表示函数类型能够跟接口的中方法相匹配；
//        如果 Java 类有多个接受函数式接口的方法，可以指定特定的实现；
//        只适用于 Java 交互操作，因为 Kotlin 有高阶函数可以传递函数参数，所以不需要接口的实现；
//        SAM 转换只适用于接口，而不适用于抽象类，即使这些抽象类也只有一个抽象方法。
    }
}

interface OnClickListenerAAA {
    fun onClick(v: View?)
}

////例4
////java里定义的一个接口
//public interface OnClickListener {
//    void onClick(View v);
//}
////java中的定义
//public void setOnClickListener(OnClickListener l)
////Kotlin中可以这么定义
//public fun setOnClickListener(l: ((v:View!)->Unit)!): Unit
////kotlin调用
//view.setOnClickListener { //do something }

fun main() {
    val a = BigDecimal(0.1)
    println("a values is:$a")
    val b = BigDecimal("0.1")
    println("b values is:$b")
}
