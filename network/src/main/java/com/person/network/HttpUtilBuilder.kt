package com.person.network

import com.person.network.HttpUtil
import com.person.network.interfaces.ParamsInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * 构建HttpUtil实例
 *
 * @author hsh
 * @since 2020/11/2 10:11 AM
 */
class HttpUtilBuilder @JvmOverloads constructor(
    baseUrl: String,
    paramsInterceptor: ParamsInterceptor? = null,
    interceptors: List<Interceptor>? = null,
    client: OkHttpClient? = null,
    message: String? = null
) {

    init {
        HttpUtil.setBaseUrl(baseUrl)
        HttpUtil.setParamsInterceptor(paramsInterceptor)
        HttpUtil.setClient(client)
        HttpUtil.addInterceptors(interceptors)
        HttpUtil.message(message)
    }

    @JvmOverloads
    fun create() {
        HttpUtil.getInstance()
    }
}