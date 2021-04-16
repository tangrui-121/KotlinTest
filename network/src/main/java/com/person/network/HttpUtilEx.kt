package com.person.network

import com.person.network.HttpUtil
import retrofit2.Call

/**
 * Time:2020/6/15
 * Author:Idtk
 * Desc: Http 请求Call转换辅助类
 */

/**
 * Http Get请求Call转换辅助方法
 */
fun HttpUtil.Builder.getCall():Call<String?> {
    return this.callGet() as Call<String?>
}

/**
 * Http Post请求Call转换辅助方法
 */
fun HttpUtil.Builder.postCall():Call<String?> {
    return this.callPost() as Call<String?>
}