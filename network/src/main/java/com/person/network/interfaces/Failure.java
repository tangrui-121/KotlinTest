package com.person.network.interfaces;

public interface Failure {
    /**
     * 该失败包括：
     * 1.非200【网络异常，请求失败。。。】
     * 2.200下服务器返回status=false
     *
     * @param responseCode
     * @param errorMsg
     * @param throwable
     */
    void onFailure(int responseCode, String errorMsg, Throwable throwable);
}
