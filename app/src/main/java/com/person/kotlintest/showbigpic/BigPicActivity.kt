package com.person.kotlintest.showbigpic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.person.kotlintest.R
import kotlinx.android.synthetic.main.activity_big_pic.*

class BigPicActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IMG_LIST = "image_list"
        const val EXTRA_IMG_INDEX = "image_index"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_pic)

        val list = intent.getSerializableExtra(EXTRA_IMG_LIST) as? List<String> ?: return
        var index = intent.getIntExtra(EXTRA_IMG_INDEX, 0)
        val size = list.size
        if (index < 0) {
            index = 0
        } else if (index >= size) {
            index = size - 1
        }
        view_pager.adapter = Adapter(list)

        fun setIndex(position: Int) {
            tv_index.text = "${position + 1}/${size}"
        }
        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setIndex(position)
            }
        })
        if (index == 0) {
            setIndex(0)
        } else {
            view_pager.setCurrentItem(index, false)
        }
    }


    private inner class Adapter(val data: List<String>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return object : RecyclerView.ViewHolder(
                ImageView(parent.context).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                }
            ) {}
        }

        override fun getItemCount(): Int {
            return data?.size ?: 0
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val photoView = holder.itemView as ImageView
            val url = data!![position]
            Glide.with(this@BigPicActivity).load(url).into(photoView)
        }
    }

}