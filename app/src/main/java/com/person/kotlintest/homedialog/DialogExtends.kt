package com.person.kotlintest.homedialog

import android.app.Activity
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * 针对dialog的扩展函数
 *
 * setOnSureClickUnDismissListener 默认的都会dismiss  这个不会
 */
fun Activity.errorDialog(
    title: CharSequence = "提示",
    message: String,
    cancel: (() -> Unit)? = null,
    retry: (() -> Unit)?
) {
    DialogBuilder.newBuilder(this)
        .title(title)
        .message(message)
        .cancelText("取消")
        .sureText("重试")
        .onCancelClickListener {
            cancel?.invoke()
        }
        .onSureClickListener {
            retry?.invoke()
        }
        .builder()
        .show()
}

fun Activity.noTitleDialog(message: String, sure: String = "确认", ok: (() -> Unit)?) {
    DialogBuilder.newBuilder(this)
        .titleVisble(View.GONE)
        .message(message)
        .showCancelButton(false)
        .sureText(sure)
        .onSureClickListener {
            ok?.invoke()
        }
        .builder()
        .show()
}

fun Activity.listDialog(
    title: CharSequence = "提示",
    adapter: BaseQuickAdapter<*, *>,
    ok: (() -> Unit)?
) {
    DialogBuilder.newBuilder(this)
        .title(title)
        .showCancelButton(false)
        .adapter(adapter)
        .sureText("确认")
        .onSureClickListener {
            ok?.invoke()
        }
        .builder()
        .show()
}