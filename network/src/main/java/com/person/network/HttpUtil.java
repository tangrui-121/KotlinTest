package com.person.network;

import androidx.annotation.Nullable;

import com.car300.retrofit.interfaces.HttpTarget;
import com.person.network.converter.StringConverterFactory;
import com.person.network.interfaces.Failure;
import com.person.network.interfaces.ParamsInterceptor;
import com.person.network.interfaces.Success;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by xhe on 2016/9/23.
 * 网络请求类
 */
public class HttpUtil {

    private static ApiInterface mApiInterface;
    private static String BASE_URL = "";
    private static String NETWORK_ERROR_MSG = "您的网络好像不太给力";
    private static ParamsInterceptor paramsInterceptor;
    private static final int CONNECT_TIMEOUT = 5;//单位：s

    private HttpUtil() {
        OkHttpClient okHttpClient = client;
        if (okHttpClient == null) {
            okHttpClient = defaultClient();
        }
        if (interceptors != null) {
            OkHttpClient.Builder builder = okHttpClient.newBuilder();
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
            okHttpClient = builder.build();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(StringConverterFactory.create())//转换数据的适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava作为回调适配器
                .build();

        mApiInterface = retrofit.create(ApiInterface.class);
    }

    private volatile static HttpUtil httpUtil;

    public static HttpUtil getInstance() {
        if (httpUtil == null) {
            synchronized (HttpUtil.class) {
                if (httpUtil == null) {
                    httpUtil = new HttpUtil();
                }
            }
        }
        return httpUtil;
    }

    private static OkHttpClient client;

    public static void setClient(OkHttpClient okHttpClient) {
        client = okHttpClient;
    }

    private static List<Interceptor> interceptors;

    public static void addInterceptors(List<Interceptor> interceptors) {
        HttpUtil.interceptors = interceptors;
    }

    private OkHttpClient defaultClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)//错误重连
                .build();
//        if (BuildConfig.DEBUG) {//printf logs while  debug
//            //mock
//            //log
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            okHttpClient = okHttpClient.newBuilder()
//                    .addInterceptor(logging).build();
//        }
        return okHttpClient;
    }

    /**
     * 必须先设置baseurl，再实例化HttpRequestUtil
     *
     * @param baseUrl
     */
    public static void setBaseUrl(String baseUrl) {
        if (BASE_URL != null && !BASE_URL.isEmpty()) {
            throw new IllegalArgumentException("BASE_URL 仅允许在宿主中设置一次");
        }
        BASE_URL = baseUrl;
    }

    /**
     * 通用参数拦截器
     *
     * @param interceptor
     */
    public static void setParamsInterceptor(ParamsInterceptor interceptor) {
        paramsInterceptor = interceptor;
    }

    /**
     * url如果是一个完整的URL，会自动忽略BASEURL
     *
     * @param url
     * @return
     */
    private static String checkUrl(@Nullable HttpTarget target, String url) {
        if (!url.contains("http")) {
            String host;
            if (target != null) {
                host = target.getHost();
            } else {
                host = BASE_URL;
            }
            url = host + url;
        }
        return url;
    }

    /**
     * 每个项目中可以设置不同的参数
     *
     * @param params
     * @return
     */
    private static Map<String, String> checkParams(@Nullable HttpTarget target, Map<String, String> params) {
        Map<String, String> allParams = null;
        if (paramsInterceptor != null) {
            allParams = paramsInterceptor.checkParams(new HashMap<>(params));
        }
        if (allParams != null) {
            allParams.putAll(params);
        } else {
            allParams = params;
        }
        if (target != null) {
            Map<String, String> targetParams = target.getParams();
            if (targetParams != null) {
                allParams.putAll(targetParams);
            }
        }
        //retrofit的params的值不能为null，此处做下校验，防止出错
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getValue() == null) {
                allParams.put(entry.getKey(), "");
            }
        }
        return allParams;
    }

    private static Map<String, String> checkHeaders(Map<String, String> params) {
        //retrofit的params的值不能为null，此处做下校验，防止出错
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() == null) {
                params.put(entry.getKey(), "");
            }
        }
        return params;
    }

    public static String message(String mes) {
        if (!Util.isValid(mes)) {
            mes = NETWORK_ERROR_MSG;
        }

        if (mes.equals("timeout") || mes.equals("SSL handshake timed out")) {
            return "网络请求超时";
        } else {
            return mes;
        }
    }

    public static class Builder {
        String mUrl;
        Map<String, String> mParamsMap;
        Map<String, String> mHeadersMap;
        RequestBody requestBody;
        Success mSuccessCallback;
        Failure mFailCallback;
        HttpTarget target;

        public Builder() {
            init(null);
        }

        public Builder(String url) {
            this.init(url);
        }

        private void init(String url) {
            this.mUrl = url;
            this.mParamsMap = new HashMap<>();
            this.mHeadersMap = new HashMap<>();
            this.mSuccessCallback = new Success() {
                @Override
                public void onSuccess(String result) {

                }
            };
            this.mFailCallback = new Failure() {
                @Override
                public void onFailure(int responseCode, String errorMsg, Throwable throwable) {

                }
            };
        }

        public Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder target(HttpTarget target) {
            this.target = target;
            return this;
        }

        public Builder params(String key, String value) {
            this.mParamsMap.put(key, value);
            return this;
        }

        public Builder paramsMap(Map<String, String> paramsMap) {
            if (paramsMap != null) {
                this.mParamsMap.putAll(paramsMap);
            }
            return this;
        }

        public Builder headers(String key, String value) {
            this.mHeadersMap.put(key, value);
            return this;
        }

        public Builder headersMap(Map<String, String> headersMap) {
            if (headersMap != null) {
                this.mHeadersMap.putAll(headersMap);
            }
            return this;
        }

        public Builder requestBody(RequestBody body) {
            if (body != null) {
                this.requestBody = body;
            }
            return this;
        }

        public Builder success(Success successCallback) {
            if (successCallback != null) {
                this.mSuccessCallback = successCallback;
            }
            return this;
        }

        public Builder failure(Failure failCallback) {
            if (failCallback != null) {
                this.mFailCallback = failCallback;
            }
            return this;
        }

        public Observable<String> obGet() {
            this.mUrl = checkUrl(target, this.mUrl);
            this.mParamsMap = checkParams(target, this.mParamsMap);
            this.mHeadersMap = checkHeaders(this.mHeadersMap);
            return mApiInterface.getObservable(this.mUrl, this.mParamsMap, this.mHeadersMap);
        }

        public Observable<String> obPost() {
            this.mUrl = checkUrl(target, this.mUrl);
            this.mParamsMap = checkParams(target, this.mParamsMap);
            this.mHeadersMap = checkHeaders(this.mHeadersMap);
            return mApiInterface.postObservable(this.mUrl, this.mParamsMap, this.mHeadersMap);
        }

        public Call<String> callGet() {
            this.mUrl = checkUrl(target, this.mUrl);
            this.mParamsMap = checkParams(target, this.mParamsMap);
            this.mHeadersMap = checkHeaders(this.mHeadersMap);
            return mApiInterface.get(this.mUrl, this.mParamsMap, this.mHeadersMap);
        }

        public Call<String> callPost() {
            this.mUrl = checkUrl(target, this.mUrl);
            this.mParamsMap = checkParams(target, this.mParamsMap);
            this.mHeadersMap = checkHeaders(this.mHeadersMap);
            return mApiInterface.post(this.mUrl, this.mParamsMap, this.mHeadersMap);
        }

        public Call<String> callPut() {
            this.mUrl = checkUrl(target, this.mUrl);
            this.mHeadersMap = checkParams(target, this.mHeadersMap);
            return mApiInterface.put(this.mUrl, this.requestBody, this.mHeadersMap);
        }

        /**
         * get请求
         */
        public void get() {
            this.mUrl = checkUrl(target, this.mUrl);
            this.mParamsMap = checkParams(target, this.mParamsMap);
            this.mHeadersMap = checkHeaders(this.mHeadersMap);
            mApiInterface
                    .get(this.mUrl, this.mParamsMap, this.mHeadersMap)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.code() == 200) {
                                String result = response.body();
                                mSuccessCallback.onSuccess(result);
                            } else {
                                mFailCallback.onFailure(response.code(), message(response.message()), new IOException(response.code() + response.message()));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            mFailCallback.onFailure(-1, NETWORK_ERROR_MSG, t);
                        }
                    });
        }

        /**
         * post请求
         */
        public void post() {
            this.mUrl = checkUrl(target, this.mUrl);
            this.mParamsMap = checkParams(target, this.mParamsMap);
            this.mHeadersMap = checkHeaders(this.mHeadersMap);
            mApiInterface
                    .post(this.mUrl, this.mParamsMap, this.mHeadersMap)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.code() == 200) {
                                String result = response.body();
                                mSuccessCallback.onSuccess(result);
                            } else {
                                mFailCallback.onFailure(response.code(), message(response.message()), new IOException(response.code() + response.message()));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            mFailCallback.onFailure(-1, NETWORK_ERROR_MSG, t);
                        }
                    });
        }

    }

}
