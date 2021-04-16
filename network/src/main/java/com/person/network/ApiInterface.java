package com.person.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET()
    Observable<String> getObservable(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST()
    Observable<String> postObservable(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @GET()
    Call<String> get(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST()
    Call<String> post(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @PUT
    Call<String> put(@Url String url, @Body RequestBody body, @HeaderMap Map<String, String> headers);

}
