package com.person.kotlintest.anko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.setContentView

class AnkoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // AnkoComponent和Activity相互绑定
        MainUI().setContentView(this@AnkoActivity)
    }
}