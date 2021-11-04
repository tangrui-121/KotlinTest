package com.person.kotlintest.hook

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.person.kotlintest.R
import com.person.kotlintest.hook.HookSetOnClickListenerHelper.hook
import com.person.kotlintest.log
import com.person.kotlintest.singleClick
import kotlinx.android.synthetic.main.activity_hook.*


/**
 * 1. 根据需求确定 要hook的对象
   2. 寻找要hook的对象的持有者，拿到要hook的对象
      (持有：B类 的成员变量里有 一个是A的对象，那么B就是A的持有者,如下)

      class B{
       A a;
      }
      class A{}
   3. 定义“要hook的对象”的代理类，并且创建该类的对象
   4. 使用上一步创建出来的对象，替换掉要hook的对象
 */
class HookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hook)

        val onClickListenerInstance = View.OnClickListener {
            "click A".log()
        }
        tv_A.setOnClickListener(ProxyOnClickListener(onClickListenerInstance))


//        tv_A.singleClick {
//            proxyOnClickListener
//        }

        tv_B.singleClick {
            "click B".log()
        }

//        tv_A.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                Toast.makeText(this@HookActivity, "别点啦，再点我咬你了...", Toast.LENGTH_SHORT).show()
//            }
//        })
//
//        hook(this, tv_A) //这个hook的作用，是 用我们自己创建的点击事件代理对象，替换掉之前的点击事件。

    }


}