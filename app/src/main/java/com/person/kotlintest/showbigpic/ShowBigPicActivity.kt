package com.person.kotlintest.showbigpic

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.person.kotlintest.R
import kotlinx.android.synthetic.main.activity_show_big_pic.*

class ShowBigPicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_big_pic)

        rv.layoutManager = GridLayoutManager(this, 3)
        rv.itemAnimator = DefaultItemAnimator()
        val list = arrayListOf<String>()
        list.add("http://www.baidu.com/img/bdlogo.png")
        list.add("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
        list.add("http://www.baidu.com/img/bdlogo.png")
        list.add("http://www.baidu.com/img/bdlogo.png")
        list.add("http://www.baidu.com/img/bdlogo.png")
        list.add("http://www.baidu.com/img/bdlogo.png")
        list.add("http://www.baidu.com/img/bdlogo.png")
        val imgadapter = BigPicAdapter(this, list)

        rv.adapter = imgadapter

        imgadapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                startActivity(
                    Intent(this@ShowBigPicActivity, BigPicActivity::class.java)
                        .putExtra(BigPicActivity.EXTRA_IMG_INDEX, position)
                        .putExtra(BigPicActivity.EXTRA_IMG_LIST, list)
                )
            }
        })


    }
}