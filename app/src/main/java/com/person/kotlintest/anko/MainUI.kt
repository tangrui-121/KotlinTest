package com.person.kotlintest.anko

import android.app.ListActivity
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import com.person.kotlintest.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

/**
 * @anthor tr
 * @date 2021/5/13
 * @desc
 */
class MainUI : AnkoComponent<AnkoActivity> {
    override fun createView(ui: AnkoContext<AnkoActivity>) = with(ui) {
        verticalLayout {
            // 这个gravity对应的就是gravity，而在lparams闭包中，gravity对应的是layout_gravity
            gravity = Gravity.CENTER
            // 布局的属性params在闭包里面的lparams中设置，但是button、TextView等控件的属性params是在闭包外的lparams设置
            lparams(matchParent, matchParent)
            editText {
                hint = "userName"
                gravity = Gravity.CENTER
                // 监听输入框输入情况
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
            }.lparams(width = dip(250), height = 200)

            editText {
                hint = "password"
                top = 20
                gravity = Gravity.CENTER
            }.lparams(width = dip(250), height = 200)

            button("list") {
                backgroundColor = Color.parseColor("#FF9999")
                alpha = 0.5f
                // 点击事件
                onClick {
                    toast("Single Click")
                }
                // 长按事件
                onLongClick {
                    toast("Long Click")
                }
            }.lparams(dip(250), dip(50))

            button("setting") {
                backgroundColor = Color.parseColor("#FF7777")
                alpha = 0.5f
                // 点击事件
                onClick {
                    // anko封装的intent携带值跳转
                    toast("setting")
                }
            }.lparams(dip(250), dip(50)) {
                topMargin = dip(16)
            }

            button("custom_view") {
                backgroundColor = Color.parseColor("#FF7777")
                alpha = 0.5f
                // 点击事件
                onClick {
                    // anko封装的intent携带值跳转
                    startActivity<MainActivity>("aulton" to "aulton")
                }
            }.lparams(dip(250), dip(50)) {
                topMargin = dip(16)
            }
        }
    }
}
