package com.person.network.interfaces

/**
 * 组件间隔离接口
 *
 * @author hsh
 * @since 2020/11/2 9:57 AM
 */
interface HttpTarget {

    /**
     * 获取组件host
     */
    fun getHost(): String

    /**
     * 获取组件通用参数
     */
    fun getParams(): Map<String, String>
}