package com.mzk.compass.youqi.api;

import android.content.Context;

import com.znz.compass.znzlibray.base_znz.BaseModel;
import com.znz.compass.znzlibray.base_znz.IView;
import com.znz.compass.znzlibray.network.retorfit.ZnzRetrofitUtil;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * Date： 2017/12/11 2017
 * User： PSuiyi
 * Description：
 */

public class ApiModel extends BaseModel {
    private ApiService apiService;

    public ApiModel(Context context, IView mView) {
        super(context, mView);
        apiService = ZnzRetrofitUtil.getInstance().createService(ApiService.class);
    }


    /**
     * 获取验证码
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestCode(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCode(params), znzHttpListener);
    }


    /**
     * 注册
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestRegister(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestRegister(params), znzHttpListener);
    }

    /**
     * 登陆
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestLogin(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestLogin(params), znzHttpListener, LODING_PD);
    }

    /**
     * 忘记密码
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestForgetPsd(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestForgetPsd(params), znzHttpListener, LODING_PD);
    }

    public void requestBind(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestBind(params), znzHttpListener, LODING_PD);
    }


    /**
     * 第三方登录
     *
     * @param params
     * @return
     */
    public void requestAutherLogin(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestAutherLogin(params), znzHttpListener);
    }


    public Observable<ResponseBody> requestPeopleStateList(Map<String, String> params) {
        return apiService.requestPeopleStateList(params);
    }

    public void requestPeopleStateList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestPeopleStateList(params), znzHttpListener);
    }


    public void requestFiltList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestFiltList(params), znzHttpListener);
    }

    public void requestCityList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCityList(params), znzHttpListener, LODING_LODING);
    }


    public void requestPublishProject(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestPublishProject(params), znzHttpListener, LODING_PD);
    }

    public void requestRecomSelfProject(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestRecomSelfProject(params), znzHttpListener);
    }

    public void requestRecomSelf(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestRecomSelf(params), znzHttpListener, LODING_PD);
    }


    /**
     * 帖子评论回复列表
     *
     * @param params
     */
    public Observable<ResponseBody> requestCommentList(Map<String, String> params) {
        return apiService.requestCommentList(params);
    }

    public Observable<ResponseBody> requestPeopleList(Map<String, String> params) {
        return apiService.requestPeopleList(params);
    }

    public void requestPeopleDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestPeopleDetail(params), znzHttpListener, LODING_LODING);
    }

    public Observable<ResponseBody> requestProductList(Map<String, String> params) {
        return apiService.requestProductList(params);
    }

    public void requestProductDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestProductDetail(params), znzHttpListener, LODING_LODING);
    }

    public Observable<ResponseBody> requestOrganList(Map<String, String> params) {
        return apiService.requestOrganList(params);
    }

    public void requestOrganDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestOrganDetail(params), znzHttpListener, LODING_LODING);
    }

    public void requestProjectDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestProjectDetail(params), znzHttpListener);
    }

    public Observable<ResponseBody> requestPeopleViewList(Map<String, String> params) {
        return apiService.requestPeopleViewList(params);
    }

    public void requestPeopleViewList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestPeopleViewList(params), znzHttpListener);
    }


    /**
     * 文章列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestNewsList(Map<String, String> params) {
        return apiService.requestNewsList(params);
    }

    public void requestNewsType(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestNewsType(params), znzHttpListener);
    }

    /**
     * 文章详情
     *
     * @param params
     * @param params
     * @return
     */
    public void requestNewsDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestNewsDetail(params), znzHttpListener);
    }


    /**
     * 优企助首页
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestHelpHome(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestHelpHome(params), znzHttpListener);
    }

    public Observable<ResponseBody> requestHelpRecommend(Map<String, String> params) {
        return apiService.requestHelpRecommend(params);
    }

    /**
     * 首页
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestHome(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestHome(params), znzHttpListener);
    }

    public void requestHomeRecommend(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestHomeRecommend(params), znzHttpListener);
    }


    /**
     * 用户详情
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestUserDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUserDetail(params), znzHttpListener);
    }

    /**
     * 完善用户资料
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestUpdateUserInfo(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUpdateUserInfo(params), znzHttpListener);
    }


    /**
     * 项目列表我的
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestProjectMineList(Map<String, String> params) {
        return apiService.requestProjectMineList(params);
    }

    //全部项目
    public Observable<ResponseBody> requestProjectList(Map<String, String> params) {
        return apiService.requestProjectList(params);
    }

    /**
     * 订单列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestOrderList(Map<String, String> params) {
        return apiService.requestOrderList(params);
    }

    /**
     * 消息列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestMessageList(Map<String, String> params) {
        return apiService.requestMessageList(params);
    }

    /**
     * 互动消息列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestInteractMessageList(Map<String, String> params) {
        return apiService.requestInteractMessageList(params);
    }

    /**
     * 交易消息列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestOrderMessageList(Map<String, String> params) {
        return apiService.requestOrderMessageList(params);
    }

    /**
     * 系统消息列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestSysMessageList(Map<String, String> params) {
        return apiService.requestSysMessageList(params);
    }

    /**
     * 动态列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestStateList(Map<String, String> params) {
        return apiService.requestStateList(params);
    }

    /**
     * 动态收藏列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestCollect(Map<String, String> params) {
        return apiService.requestCollect(params);
    }

    /**
     * 投稿列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestArticleList(Map<String, String> params) {
        return apiService.requestArticleList(params);
    }

    /**
     * 投稿详情
     *
     * @param params
     * @return
     */
    public void requestArticleDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestArticleDetail(params), znzHttpListener);
    }

    /**
     * 删除互动消息
     *
     * @param params
     * @return
     */
    public void requestDeletInteractMsg(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestDeletInteractMsg(params), znzHttpListener);
    }

    /**
     * 删除交易消息
     *
     * @param params
     * @return
     */
    public void requestDeleteOrderMsg(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestDeleteOrderMsg(params), znzHttpListener);
    }

    /**
     * 互动消息详情
     *
     * @param params
     * @return
     */
    public void requestDeleteSysMsg(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestDeleteSysMsg(params), znzHttpListener);
    }

    /**
     * 消息回复
     *
     * @param params
     * @return
     */
    public void requestMsgReply(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestMsgReply(params), znzHttpListener);
    }

    /**
     * 删除系统消息
     *
     * @param params
     * @return
     */
    public void requestInteractMsgDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestInteractMsgDetail(params), znzHttpListener);
    }

    /**
     * 交易消息跳转
     *
     * @param params
     * @return
     */
    public void requestOrderMsgDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestOrderMsgDetail(params), znzHttpListener);
    }

    /**
     * 系统消息详情
     *
     * @param params
     * @return
     */
    public void requestSysMsgDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestSysMsgDetail(params), znzHttpListener, LODING_LODING);
    }

    /**
     * 专属客服列表
     *
     * @param params
     * @return
     */
    public void requestCustomerService(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCustomerService(params), znzHttpListener);
    }

    /**
     * 充值列表
     *
     * @param params
     * @return
     */
    public void requestPriceList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestPriceList(params), znzHttpListener);
    }

    /**
     * 生成支付订单
     *
     * @param params
     * @return
     */
    public void requestCreateOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCreateOrder(params), znzHttpListener);
    }

    /**
     * 获取验证码
     *
     * @param params
     * @return
     */
    public void requestGetCode(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestGetCode(params), znzHttpListener);
    }

    /**
     * 验证手机
     *
     * @param params
     * @return
     */
    public void requestCheckPhone(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCheckPhone(params), znzHttpListener);
    }

    /**
     * 修改手机号
     *
     * @param params
     * @return
     */
    public void requestChangePhone(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestChangePhone(params), znzHttpListener);
    }

    /**
     * 获取银行卡
     *
     * @param params
     * @return
     */
    public void requestBankList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestBankList(params), znzHttpListener);
    }

    /**
     * 绑定银行卡，修改银行卡
     *
     * @param params
     * @return
     */
    public void requestBindBank(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestBindBank(params), znzHttpListener);
    }

    /**
     * 获取消息数量
     *
     * @param params
     * @return
     */
    public void requestMessageCount(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestMessageCount(params), znzHttpListener);
    }

    /**
     * 版本更新
     *
     * @param params
     * @return
     */
    public void requestCheckUpdate(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCheckUpdate(params), znzHttpListener);
    }

    /**
     * 订单详情
     *
     * @param params
     * @return
     */
    public void requestOrderDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestOrderDetail(params), znzHttpListener, LODING_LODING);
    }

    /**
     * 获取企业创建信息
     *
     * @param params
     * @return
     */
    public void requestCompanyDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCompanyDetail(params), znzHttpListener);
    }

    /**
     * 成员列表
     *
     * @param params
     * @return
     */
    public void requestMemberList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestMemberList(params), znzHttpListener);
    }

    /**
     * 成员创建，认证
     *
     * @param params
     * @return
     */
    public void requestCompany(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCompany(params), znzHttpListener);
    }

    /**
     * 获取企业认证资料
     *
     * @param params
     * @return
     */
    public void requestCompanyIdentifyDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCompanyIdentifyDetail(params), znzHttpListener);
    }

    /**
     * 成员添加
     *
     * @param params
     * @return
     */
    public void requestMemberAdd(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestMemberAdd(params), znzHttpListener);
    }

    /**
     * 成员修改
     *
     * @param params
     * @return
     */
    public void requestMemberUpdate(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestMemberUpdate(params), znzHttpListener);
    }

    /**
     * 账户管理首页
     *
     * @param params
     * @return
     */
    public void requestAccountManger(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestAccountManger(params), znzHttpListener);
    }

    /**
     * 修改密码
     *
     * @param params
     * @return
     */
    public void requestUpdatePsd(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUpdatePsd(params), znzHttpListener);
    }

    /**
     * 发布按钮认证状态
     *
     * @param params
     * @return
     */
    public void requestCheckIdentify(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCheckIdentify(params), znzHttpListener);
    }

    /**
     * 判断是否认证投资人
     *
     * @param params
     * @return
     */
    public void requestIdentifyStatus(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestIdentifyStatus(params), znzHttpListener);
    }

    /**
     * 获取认证信息
     *
     * @param params
     * @return
     */
    public void requestIdentifyDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestIdentifyDetail(params), znzHttpListener);
    }

    /**
     * banner
     *
     * @param params
     * @return
     */
    public void requestBanner(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestBanner(params), znzHttpListener);
    }

    /**
     * 关于我们
     *
     * @param params
     * @return
     */
    public void requestAboutUs(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestAboutUs(params), znzHttpListener, LODING_LODING);
    }

    /**
     * 手机验证码
     *
     * @param params
     * @return
     */
    public void requestUpdate(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUpdate(params), znzHttpListener);
    }

    /**
     * 发布动态
     *
     * @param params
     * @return
     */
    public void requestAddState(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestAddState(params), znzHttpListener, LODING_PD);
    }

    public void requestSendMessage(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestSendMessage(params), znzHttpListener);
    }

    public void requestSendComment(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestSendComment(params), znzHttpListener, LODING_PD);
    }

    /**
     * 用户反馈
     *
     * @param params
     * @return
     */
    public void requestFeedBack(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestFeedBack(params), znzHttpListener);
    }

    /**
     * 修改订单状态
     *
     * @param params
     * @return
     */
    public void requestUpdateOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUpdateOrder(params), znzHttpListener);
    }

    /**
     * 订单确认
     *
     * @param params
     * @return
     */
    public void requestOrderConfirm(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestOrderConfirm(params), znzHttpListener);
    }

    /**
     * 订单提交
     *
     * @param params
     * @return
     */
    public void requestOrderSubmit(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestOrderSubmit(params), znzHttpListener);
    }

    public void requestPayParams(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestPayParams(params), znzHttpListener);
    }

    /**
     * 商品分类
     *
     * @param params
     * @return
     */
    public void requestCategory(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCategory(params), znzHttpListener);
    }

    /**
     * 订单支付界面
     *
     * @param params
     * @return
     */
    public void requestOrderPay(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestOrderPay(params), znzHttpListener);
    }

    /**
     * 消息详情
     *
     * @param params
     * @return
     */
    public void requestMessageDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestMessageDetail(params), znzHttpListener);
    }

    /**
     * 添加收藏
     *
     * @param params
     * @return
     */
    public void requestAddCollect(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestAddCollect(params), znzHttpListener);
    }

    /**
     * 取消收藏
     *
     * @param params
     * @return
     */
    public void requestCancalCollect(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestCancalCollect(params), znzHttpListener);
    }

    /**
     * 个人身份认证
     *
     * @param params
     * @return
     */
    public void requestIdentifyPersonal(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestIdentifyPersonal(params), znzHttpListener);
    }

    /**
     * 投资人认证
     *
     * @param params
     * @return
     */
    public void requestIdentify(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestIdentify(params), znzHttpListener);
    }

    /**
     * 删除动态
     *
     * @param params
     * @return
     */
    public void requestDeleteState(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestDeleteState(params), znzHttpListener);
    }

    /**
     * 项目操作
     *
     * @param params
     * @return
     */
    public void requestUpdateProjectState(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestUpdateProjectState(params), znzHttpListener);
    }

    /**
     * 查看融资
     *
     * @param params
     * @return
     */
    public void requestRongZiDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestRongZiDetail(params), znzHttpListener);
    }

    /**
     * 融资提交
     *
     * @param params
     * @return
     */
    public void requestRongZiSubmit(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        request(apiService.requestRongZiSubmit(params), znzHttpListener);
    }

    /**
     * 单张图片上传
     *
     * @param url
     * @param znzHttpListener
     */
    public void uploadImageSingle(Map<String, String> params, String url, ZnzHttpListener znzHttpListener) {
        File file = new File(url);
        Luban.get(context)
                .load(file)
                .putGear(Luban.THIRD_GEAR)
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> throwable.printStackTrace())
                .onErrorResumeNext(throwable -> Observable.empty())
                .subscribe(file1 -> {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file1);//表单类型
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        builder.addFormDataPart(entry.getKey(), entry.getValue());//传入服务器需要的key，和相应value值
                    }
                    builder.addFormDataPart("image", file.getName(), body); //添加图片数据，body创建的请求体
                    List<MultipartBody.Part> parts = builder.build().parts();
                    request(apiService.uploadImageSingle(parts), znzHttpListener, LODING_PD);
                });
    }

    /**
     * 多张图片上传
     *
     * @param url
     * @param znzHttpListener
     */
    public void uploadImageMulti(List<File> url, ZnzHttpListener znzHttpListener) {
        Map<String, String> params = new HashMap();
        List<MultipartBody.Part> partLlist = new ArrayList<>();
        for (File file : url) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName() + ".jpg", requestBody);
            partLlist.add(body);
        }
        request(apiService.uploadImageMulti(params, partLlist), znzHttpListener);
    }

}
