package com.person.kotlintest.timeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.person.kotlintest.R
import kotlinx.android.synthetic.main.activity_time_view.*

class TimeViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_view)
    }

    override fun onStop() {
        super.onStop()
        timeview.setIsRun(false)
    }
}