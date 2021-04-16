package com.person.network

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by hsh on 2019-10-21 15:53
 * 协程扩展
 */

/**
 * Desc:协程任务辅助方法
 * Sample:
 * launch({
 *      val result = HttpUtil.Builder("pro/v1/home/home").getCall().await()?:return@launch
 *      Log.d("Coroutine",result)
 *      val model = BaseModel(result)
 *      if (!model.status) return@launch
 *      val info = model.data?.fromJson<HomeInfo>()
 * }){
 *      Log.d("Coroutine",it.message)
 * }
 */
fun Fragment.launch(block: suspend (CoroutineScope) -> Unit,
                    error: ((e: Exception) -> Unit)? = null): Job {
    return this.lifecycleScope.launchWhenCreated {
        try {
            block(this)
        } catch (e: Exception) {
            Log.e("==>jobError", e.message?:"")
            if (error != null) {
                error(e)
            }
        }
    }
}

fun FragmentActivity.launch(block: suspend (CoroutineScope) -> Unit,
                            error: ((e: Exception) -> Unit)? = null): Job {
    return this.lifecycleScope.launchWhenCreated {
        try {
            block(this)
        } catch (e: Exception) {
            Log.e("==>jobError", e.message?:"")
            if (error != null) {
                error(e)
            }
        }
    }
}

fun ViewModel.launch(block: suspend (CoroutineScope) -> Unit,
                     error: ((e: Exception) -> Unit)? = null): Job {
    return this.viewModelScope.launch {
        try {
            block(this)
        } catch (e: Exception) {
            Log.e("==>jobError", e.message?:"")
            if (error != null) {
                error(e)
            }
        }
    }
}