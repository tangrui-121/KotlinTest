package com.person.kotlintest

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.person.kotlintest.common_class.SingleClickListener
import com.person.kotlintest.weight.TempAppFragment
import com.person.kotlintest.weight.TempFragment
import java.io.Serializable

/**
 * 单击
 */
fun View.singleClick(f: (v: View?) -> Unit) {
    setOnClickListener(object : SingleClickListener() {
        override fun onSingleClick(v: View?) {
            f.invoke(v)
        }
    })
}

/**
 * 控制重复点击
 * @param delay 延迟响应时间（单位：毫秒）
 * @param interval 间隔响应时间（单位：毫秒）
 */
fun View.setDelayClickListener(delay: Long = 50, interval: Long = 1200, onClick: (v: View) -> Unit) {
    var t = 0L
    setOnClickListener()
    {
        if (System.currentTimeMillis() - t > interval) {
            postDelayed({ onClick(it) }, delay)
            t = System.currentTimeMillis()
        }
    }
}

/**
 * 暴力try
 */
fun safe(run: () -> Unit) {
    try {
        run()
    } catch (e: Exception) {
        Log.e("Runtime", "" + e)
    }
}

/**
 * 验证手机号
 */
fun String.isPhone(): Boolean {
    val regex = Regex("^(13|14|15|16|17|18|19)\\d{9}\$")
    return regex.matches(this)
}

/**
 * 全屏
 */
internal fun Activity.fullScreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //透明状态栏
        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.decorView.systemUiVisibility = option
        window.statusBarColor = Color.TRANSPARENT
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}

/**
 * log
 */
internal fun Any?.log() {
    if (BuildConfig.DEBUG) {
        Log.w("mylog", toString())
    }
}

/**
 * ac跳转相关
 */
internal fun Intent.putExtras(vararg params: Pair<String, Any>): Intent {
    if (params.isEmpty()) return this
    params.forEach { (key, value) ->
        when (value) {
            is Int -> putExtra(key, value)
            is Byte -> putExtra(key, value)
            is Char -> putExtra(key, value)
            is Long -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Short -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is Bundle -> putExtra(key, value)
            is String -> putExtra(key, value)
            is IntArray -> putExtra(key, value)
            is ByteArray -> putExtra(key, value)
            is CharArray -> putExtra(key, value)
            is LongArray -> putExtra(key, value)
            is FloatArray -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
            is ShortArray -> putExtra(key, value)
            is DoubleArray -> putExtra(key, value)
            is BooleanArray -> putExtra(key, value)
            is CharSequence -> putExtra(key, value)
            is Array<*> -> {
                when {
                    value.isArrayOf<String>() ->
                        putExtra(key, value as Array<String?>)
                    value.isArrayOf<Parcelable>() ->
                        putExtra(key, value as Array<Parcelable?>)
                    value.isArrayOf<CharSequence>() ->
                        putExtra(key, value as Array<CharSequence?>)
                    else -> putExtra(key, value)
                }
            }
            is Serializable -> putExtra(key, value)
        }
    }
    return this
}

private var tempRequestCode = 0
    set(value) {
        field = if (value >= Integer.MAX_VALUE) 1 else value
    }

internal fun Activity.startActivityForResult(
    intent: Intent,
    callback: ((result: Intent?) -> Unit)
) {
    if (this is FragmentActivity) {
        TempFragment().also { f ->
            f.init(++tempRequestCode, intent) { result ->
                callback(result)
                supportFragmentManager.beginTransaction().remove(f).commitAllowingStateLoss()
            }
            supportFragmentManager.beginTransaction().add(f, TempFragment::class.java.simpleName)
                .commitAllowingStateLoss()
        }
    } else {
        TempAppFragment().also { f ->
            f.init(++tempRequestCode, intent) { result ->
                callback(result)
                fragmentManager.beginTransaction().remove(f).commitAllowingStateLoss()
            }
            fragmentManager.beginTransaction().add(f, TempAppFragment::class.java.simpleName)
                .commitAllowingStateLoss()
        }
    }
}

/**
 * view显示隐藏相关
 */
internal fun View.gone() {
    visibility = View.GONE
}

internal fun View.invisible() {
    visibility = View.INVISIBLE
}

internal fun View.visible() {
    visibility = View.VISIBLE
}

internal fun View.visibleOrGone(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * toast
 */
fun String.toast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(App.mContext,this,Toast.LENGTH_SHORT).show()
}

/**
 * 输入框判空 提示
 *
 * ed_account.checkBlank("请输入账号") ?: return@singleClick
 */
fun TextView.checkBlank(message: String): String? {
    val text = this.text.toString()
    if (text.isBlank()) {
        message.toast()
        return null
    }
    return text
}