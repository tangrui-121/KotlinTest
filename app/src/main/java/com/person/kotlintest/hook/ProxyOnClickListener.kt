package com.person.kotlintest.hook

import android.view.View
import com.person.kotlintest.log

/**
 * @anthor tr
 * @date 2021/10/11
 * @desc
 */
class ProxyOnClickListener(val click: View.OnClickListener?) : View.OnClickListener {

    override fun onClick(v: View?) {
        "点击事件被hook到了".log()
        click?.onClick(v)
    }
}