package com.person.kotlintest.委托

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.person.kotlintest.R

class OrderDetailFragment : Fragment(R.layout.fragment_order_detail) {

    private var orderId: Int? = null
    private var orderType: Int? = null

    companion object {
        const val EXTRA_ORDER_ID = "orderId"
        const val EXTRA_ORDER_TYPE = "orderType";

        @JvmStatic
        fun newInstance(orderId: Int, orderType: Int) = OrderDetailFragment().apply {
            Bundle().apply {
                putInt(EXTRA_ORDER_ID, orderId)
                if (null != orderType) {
                    putInt(EXTRA_ORDER_TYPE, orderType)
                }
            }.also {
                arguments = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            orderId = it.getInt(EXTRA_ORDER_ID, 10000)
            orderType = it.getInt(EXTRA_ORDER_TYPE, 2)
        }
    }
}