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

    @GET("order/detail")
    Observable<ResponseBody> requestOrderDetail(@QueryMap Map<String, String> params);

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
    @POST("project/add")
    Observable<ResponseBody> requestPublishProject(@FieldMap Map<String, String> params);

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
    @POST("comment/add")
    Observable<ResponseBody> requestSendComment(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("project/arrange-talk")
    Observable<ResponseBody> requestSendMessage(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("ucenter/add-feedback")
    Observable<ResponseBody> requestFeedBack(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("ucenter/update-info")
    Observable<ResponseBody> requestUpdateUserInfo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("site/release")
    Observable<ResponseBody> requestCheckIdentify(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("message/recommend-self")
    Observable<ResponseBody> requestRecomSelf(@FieldMap Map<String, String> params);

    @GET("project/list-my-project")
    Observable<ResponseBody> requestProjectMineList(@QueryMap Map<String, String> params);

    @GET("project/index")
    Observable<ResponseBody> requestProjectList(@QueryMap Map<String, String> params);

    @GET("order/list")
    Observable<ResponseBody> requestOrderList(@QueryMap Map<String, String> params);

    @GET("investor/get-my-project")
    Observable<ResponseBody> requestRecomSelfProject(@QueryMap Map<String, String> params);

    @GET("comment/index")
    Observable<ResponseBody> requestCommentList(@QueryMap Map<String, String> params);

    @GET("investor/detail")
    Observable<ResponseBody> requestPeopleDetail(@QueryMap Map<String, String> params);

    @GET("investor/list")
    Observable<ResponseBody> requestPeopleList(@QueryMap Map<String, String> params);

    @GET("product/list")
    Observable<ResponseBody> requestProductList(@QueryMap Map<String, String> params);

    @GET("product/detail")
    Observable<ResponseBody> requestProductDetail(@QueryMap Map<String, String> params);

    @GET("company/list")
    Observable<ResponseBody> requestOrganList(@QueryMap Map<String, String> params);

    @GET("company/detail")
    Observable<ResponseBody> requestOrganDetail(@QueryMap Map<String, String> params);

    @GET("project/detail")
    Observable<ResponseBody> requestProjectDetail(@QueryMap Map<String, String> params);

    @GET("project/get-investor-visits")
    Observable<ResponseBody> requestPeopleViewList(@QueryMap Map<String, String> params);

    @GET("news/detail")
    Observable<ResponseBody> requestNewsDetail(@QueryMap Map<String, String> params);

    @GET("message/list")
    Observable<ResponseBody> requestMessageList(@QueryMap Map<String, String> params);

    @GET("state/index")
    Observable<ResponseBody> requestStateList(@QueryMap Map<String, String> params);

    @GET("contribution/index")
    Observable<ResponseBody> requestArticleList(@QueryMap Map<String, String> params);

    @GET("contribution/detail")
    Observable<ResponseBody> requestArticleDetail(@QueryMap Map<String, String> params);

    @GET("ucenter/get-my-custom-service")
    Observable<ResponseBody> requestCustomerService(@QueryMap Map<String, String> params);

    @GET("site/get-banner")
    Observable<ResponseBody> requestBanner(@QueryMap Map<String, String> params);

    @GET("ucenter/aboutus")
    Observable<ResponseBody> requestAboutUs(@QueryMap Map<String, String> params);

    @GET("base/get-filter-data")
    Observable<ResponseBody> requestFiltList(@QueryMap Map<String, String> params);

    @GET("investor/get-investor-state")
    Observable<ResponseBody> requestPeopleStateList(@QueryMap Map<String, String> params);

    @GET("base/get-city")
    Observable<ResponseBody> requestCityList(@QueryMap Map<String, String> params);

    @GET("order/confirm")
    Observable<ResponseBody> requestOrderConfirm(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("order/submit")
    Observable<ResponseBody> requestOrderSubmit(@FieldMap Map<String, String> params);

    @GET("order/pay")
    Observable<ResponseBody> requestOrderPay(@QueryMap Map<String, String> params);

    @GET("product/get-product-type")
    Observable<ResponseBody> requestCategory(@QueryMap Map<String, String> params);

    @GET("favourite/index")
    Observable<ResponseBody> requestCollect(@QueryMap Map<String, String> params);

    @GET("investor/index")
    Observable<ResponseBody> requestIdentifyStatus(@QueryMap Map<String, String> params);

    @GET("investor/get-my-investor-info")
    Observable<ResponseBody> requestIdentifyDetail(@QueryMap Map<String, String> params);

    @GET("company/get-create-company-info")
    Observable<ResponseBody> requestCompanyDetail(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("favourite/add")
    Observable<ResponseBody> requestAddCollect(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("favourite/cancel")
    Observable<ResponseBody> requestCancalCollect(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("investor/submit-investor-info")
    Observable<ResponseBody> requestIdentify(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("account/approve-personal")
    Observable<ResponseBody> requestIdentifyPersonal(@FieldMap Map<String, String> params);

    @GET("invite/list")
    Observable<ResponseBody> requestMemberList(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("invite/add")
    Observable<ResponseBody> requestMemberAdd(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("invite/update")
    Observable<ResponseBody> requestMemberUpdate(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("company/save-company-info")
    Observable<ResponseBody> requestCompany(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("company/get-identify-company-info")
    Observable<ResponseBody> requestCompanyIdentifyDetail(@FieldMap Map<String, String> params);

    @Multipart
    @POST("upload/upload")
    Observable<ResponseBody> uploadImageSingle(@Part List<MultipartBody.Part> partLis);

    @Multipart
    @POST("upload/upload")
    Observable<ResponseBody> uploadImageMulti(@QueryMap Map<String, String> params, @Part() List<MultipartBody.Part> parts);

}
