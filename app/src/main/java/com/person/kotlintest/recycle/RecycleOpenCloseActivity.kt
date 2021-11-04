package com.person.kotlintest.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.person.kotlintest.R
import kotlinx.android.synthetic.main.activity_recycle_open_close.*

class RecycleOpenCloseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_open_close)

        val list = arrayListOf<MsgBean>()
        for (a in 1..10) {
            list.add(MsgBean("wwwwwwwwwww" + a, "eeeeeeeee" + a, "qqqqqqqqqqqqqq" + a))
        }
        val adapter = MsgAdapter(this)
        adapter.setLists(list)
        rv_oc.setLayoutManager(LinearLayoutManager(this))
        rv_oc.setAdapter(adapter)

        rv_oc.getItemAnimator()?.setChangeDuration(300)
        rv_oc.getItemAnimator()?.setMoveDuration(300)

    }
}