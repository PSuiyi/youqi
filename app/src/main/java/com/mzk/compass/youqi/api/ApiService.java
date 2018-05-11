package com.mzk.compass.youqi.api;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("baj_api/s/api")
    Observable<ResponseBody> post(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("login/get-code")
    Observable<ResponseBody> requestCode(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("login/login")
    Observable<ResponseBody> requestLogin(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("login/register")
    Observable<ResponseBody> requestRegister(@FieldMap Map<String, String> params);

    @GET("news/list")
    Observable<ResponseBody> requestNewsList(@QueryMap Map<String, String> params);

    @GET("news/get-new-cate")
    Observable<ResponseBody> requestNewsType(@QueryMap Map<String, String> params);

    @GET("product/index")
    Observable<ResponseBody> requestHelpHome(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("ucenter/index")
    Observable<ResponseBody> requestUserDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("ucenter/update-info")
    Observable<ResponseBody> requestUpdateUserInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("project/list-my-project")
    Observable<ResponseBody> requestProjectList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("order/list")
    Observable<ResponseBody> requestOrderList(@FieldMap Map<String, String> params);

    @Multipart
    @POST("baj_api/s/api")
    Observable<ResponseBody> uploadImageSingle(@QueryMap Map<String, String> params, @Part MultipartBody.Part file);

    @Multipart
    @POST("baj_api/s/api")
    Observable<ResponseBody> uploadImageMulti(@QueryMap Map<String, String> params, @Part() List<MultipartBody.Part> parts);

}
