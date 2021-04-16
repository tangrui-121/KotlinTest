package com.person.kotlintest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.person.kotlintest.homedialog.HomeDialogActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        layoutInflater.factory2 = object : LayoutInflater.Factory2 {
            override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
                "11111111111111".log()
                return null
            }

            override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
                "22222222222222".log()
                if (name == "TextView") {
                    val iv = ImageView(context)
                    iv.setImageResource(R.mipmap.ic_launcher)
                    return iv
                }
                else
                    return null
            }
        }
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