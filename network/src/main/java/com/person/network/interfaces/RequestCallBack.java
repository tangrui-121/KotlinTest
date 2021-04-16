package com.person.network.interfaces;

/**
 * 请求返回接口
 */
public interface RequestCallBack {
    void onSuccess(String result);

    void onFailure(int responseCode, String errorMsg, Throwable throwable);
}
