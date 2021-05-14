package com.person.kotlintest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.person.kotlintest.anko.AnkoActivity
import com.person.kotlintest.download.UpdateActivity
import com.person.kotlintest.homedialog.HomeDialogActivity
import com.person.kotlintest.rxandroid.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        layoutInflater.factory2 = object : LayoutInflater.Factory2 {
            override fun onCreateView(
                parent: View?,
                name: String,
                context: Context,
                attrs: AttributeSet
            ): View? {
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
        tv_update.singleClick {
            startActivity(Intent(this, UpdateActivity::class.java))
        }
        tv_rxandroid.singleClick {
            StreamTimer().next(TaskA())
                .next(TaskB(), 1000)
                .next(TaskC(), 2000)
                .start()
        }
        tv_rx.singleClick {
            RX.textRX()
        }
        tv_anko.singleClick {
            startActivity(Intent(this,AnkoActivity::class.java))
        }
    }
}