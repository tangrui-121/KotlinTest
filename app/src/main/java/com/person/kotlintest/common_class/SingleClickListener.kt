package com.person.kotlintest.common_class

import android.view.View

/**
 * 处理单击防抖
 *
 * internal表示只有本模块才能使用
 */
internal abstract class SingleClickListener : View.OnClickListener {
    private val delay = 600
    private var lastClickTime: Long = 0L
    protected abstract fun onSingleClick(v: View?)

    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > delay) {
            lastClickTime = currentTime
            onSingleClick(v)
        }
    }
}