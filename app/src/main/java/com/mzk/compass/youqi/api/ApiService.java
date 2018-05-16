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

    @GET("product/get-help-recommend-product")
    Observable<ResponseBody> requestHelpRecommend(@QueryMap Map<String, String> params);

    @GET("site/index")
    Observable<ResponseBody> requestHome(@QueryMap Map<String, String> params);

    @GET("site/get-home-chosen-project")
    Observable<ResponseBody> requestHomeRecommend(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("ucenter/index")
    Observable<ResponseBody> requestUserDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("order/detail")
    Observable<ResponseBody> requestOrderDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("account/index")
    Observable<ResponseBody> requestAccountManger(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("account/modify-pass")
    Observable<ResponseBody> requestUpdatePsd(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("message/detail")
    Observable<ResponseBody> requestMessageDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("order/operate")
    Observable<ResponseBody> requestUpdateOrder(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("account/auto-send-code")
    Observable<ResponseBody> requestUpdate(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("state/add")
    Observable<ResponseBody> requestAddState(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("ucenter/add-feedback")
    Observable<ResponseBody> requestFeedBack(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("ucenter/update-info")
    Observable<ResponseBody> requestUpdateUserInfo(@FieldMap Map<String, String> params);

    @GET("project/list-my-project")
    Observable<ResponseBody> requestProjectMineList(@QueryMap Map<String, String> params);

    @GET("project/index")
    Observable<ResponseBody> requestProjectList(@QueryMap Map<String, String> params);

    @GET("order/list")
    Observable<ResponseBody> requestOrderList(@QueryMap Map<String, String> params);

    @GET("message/list")
    Observable<ResponseBody> requestMessageList(@QueryMap Map<String, String> params);

    @GET("state/index")
    Observable<ResponseBody> requestStateList(@QueryMap Map<String, String> params);

    @GET("contribution/index")
    Observable<ResponseBody> requestArticleList(@QueryMap Map<String, String> params);

    @GET("ucenter/get-my-custom-service")
    Observable<ResponseBody> requestCustomerService(@QueryMap Map<String, String> params);

    @Multipart
    @POST("")
    Observable<ResponseBody> uploadImageSingle(@QueryMap Map<String, String> params, @Part MultipartBody.Part file);

    @Multipart
    @POST("")
    Observable<ResponseBody> uploadImageMulti(@QueryMap Map<String, String> params, @Part() List<MultipartBody.Part> parts);

}
