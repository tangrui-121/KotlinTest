package com.person.kotlintest.showbigpic

import android.app.Activity
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.person.kotlintest.R
import kotlinx.android.synthetic.main.item_bigpic.view.*

class BigPicAdapter(val activity: Activity, list: List<*>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.item_bigpic, list as MutableList<String>?) {
    override fun convert(holder: BaseViewHolder, bean: String) {

        val itemView = holder.itemView
        Glide.with(activity).load(bean).into(itemView.img)
    }
}