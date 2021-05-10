package com.person.kotlintest.download

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.person.kotlintest.R
import com.person.kotlintest.log
import com.person.kotlintest.singleClick
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    val TAG = "UpdateDialogFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        update.singleClick {
            val dialog = UpdateDialog.newInstance()
            dialog.setUpdateDialogCallback(object : UpdateDialog.UpdateDialogCallback {
                override fun onUpdateCanceled() {
                    "升级弹框取消了".log()
                }
            })
            dialog.show(
                this.supportFragmentManager,
                TAG
            )
        }
    }
}