package com.person.kotlintest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.person.kotlintest.anko.AnkoActivity
import com.person.kotlintest.download.UpdateActivity
import com.person.kotlintest.homedialog.HomeDialogActivity
import com.person.kotlintest.reflex.StorageManagerTest
import com.person.kotlintest.rxandroid.*
import com.person.kotlintest.socket.SocketActivity
import com.person.kotlintest.timeview.TimeViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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
                } else
                    return null
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "当前进程：${android.os.Process.myPid()}".log()
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
            startActivity(Intent(this, AnkoActivity::class.java))
        }
        eeeee.setLeftClickListener {
            Toast.makeText(this@MainActivity, "rrrrr", Toast.LENGTH_LONG).show()
        }
        tv_StorageManager.singleClick {
            startActivity(Intent(this, StorageManagerTest::class.java))
        }
        tv_timeview.singleClick {
            startActivity(Intent(this, TimeViewActivity::class.java))
        }
        tv_socket.singleClick {
            startActivity(Intent(this, SocketActivity::class.java))
        }
    }
}