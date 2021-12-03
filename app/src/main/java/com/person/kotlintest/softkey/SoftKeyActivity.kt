package com.person.kotlintest.softkey

import android.R.attr.keyHeight
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.person.kotlintest.R
import com.person.kotlintest.singleClick
import com.person.kotlintest.visible
import kotlinx.android.synthetic.main.activity_soft_key.*


class SoftKeyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soft_key)

        tv_test.singleClick {
            ll_soft.visible()
            ed_test.requestFocus()
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(ed_test, InputMethodManager.SHOW_IMPLICIT)
        }
    }


}