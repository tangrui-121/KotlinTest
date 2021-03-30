package com.person.kotlintest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.person.kotlintest.homedialog.HomeDialogActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickEvents()
    }

    private fun clickEvents() {
        tv_homedialog.singleClick {
            startActivity(Intent(this, HomeDialogActivity::class.java))
        }
    }
}