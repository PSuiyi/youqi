package com.mzk.compass.youqi.api;

import android.content.Context;

import com.znz.compass.znzlibray.base_znz.BaseModel;
import com.znz.compass.znzlibray.base_znz.IView;
import com.znz.compass.znzlibray.common.ZnzConstants;
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

    public void checkPhone(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "00011");
        request(apiService.post(params), znzHttpListener);
    }

    public Observable<ResponseBody> requestVideoList(Map<String, String> params) {
        params.put("request_code", "30000");
        return apiService.post(params);
    }

    public void requestVideoDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30001");
        request(apiService.post(params), znzHttpListener, LODING_LODING);
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
     * 忘记密码
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestFindPassWord(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10021");
        request(apiService.post(params), znzHttpListener);
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
        request(apiService.requestLogin(params), znzHttpListener);
    }

    /**
     * 第三方登录
     *
     * @param params
     * @return
     */
    public void requestAutherLogin(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10023");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 收货地址列表
     */
    public Observable<ResponseBody> requestAddressList(Map<String, String> params) {
        params.put("request_code", "40000");
        return apiService.post(params);
    }

    /**
     * 新增收货地址
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestAddressAdd(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40001");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 删除收货地址
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestAddressDelete(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40005");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 修改收货地址
     */
    public void requestAddressModify(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40002");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 获取默认地址
     */
    public void requestDefaultAddress(ZnzHttpListener znzHttpListener) {
        Map<String, String> params = new HashMap<>();
        params.put("request_code", "40007");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 城市列表
     *
     * @param params
     * @return
     */
    public void requestCityList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40003");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 创建投诉
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestPublishPost(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10011");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 帖子列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestPostList(Map<String, String> params) {
        params.put("request_code", "10008");
        return apiService.post(params);
    }

    /**
     * 帖子评论回复列表
     *
     * @param params
     */
    public Observable<ResponseBody> requestPostCommentList(Map<String, String> params) {
        params.put("request_code", "10010");
        return apiService.post(params);
    }

    /**
     * 帖子详情
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestPostDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10009");
        request(apiService.post(params), znzHttpListener, LODING_LODING);
    }

    /**
     * 帖子回复
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestPostComment(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10013");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 帖子评论回复
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestPostCommentReply(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10012");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 便民服务列表页面
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestBianMinList(Map<String, String> params) {
        params.put("request_code", "20002");
        return apiService.post(params);
    }

    /**
     * 查询收藏列表接口 收藏文章
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestCollectArticleList(Map<String, String> params) {
        params.put("request_code", "20007");
        return apiService.post(params);
    }

    /**
     * 查询收藏列表接口 收藏商品
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestCollectGoodesList(Map<String, String> params) {
        params.put("request_code", "20009");
        return apiService.post(params);
    }

    /**
     * 文章列表
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestArticleList(Map<String, String> params) {
        params.put("request_code", "20005");
        return apiService.post(params);
    }

    /**
     * 文章详情
     *
     * @param params
     * @param params
     * @return
     */
    public void requestNewsDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "20006");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 商家列表
     *
     * @param params
     */
    public Observable<ResponseBody> requestBianMinServiceDetailList(Map<String, String> params) {
        params.put("request_code", "20003");
        return apiService.post(params);
    }

    /**
     * 商家详情
     *
     * @param params
     */
    public void requestShangJiaDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "20004");
        request(apiService.post(params), znzHttpListener);
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
     * 获取用户状态
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestUserStatus(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10029");
        request(apiService.post(params), znzHttpListener);
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
     * 修改手机号
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestChangePhoneNum(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30002");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 过户
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestTransferAct(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10027");
        request(apiService.post(params), znzHttpListener);
    }


    /**
     * 租客列表
     *
     * @return
     */
    public Observable<ResponseBody> requestMemberList(Map<String, String> params) {
        params.put("request_code", "10020");
        return apiService.post(params);
    }

    /**
     * 家庭信息
     *
     * @return
     */
    public Observable<ResponseBody> requestFamilyList(Map<String, String> params) {
        params.put("request_code", "10018");
        return apiService.post(params);
    }

    /**
     * 编辑家庭信息
     *
     * @return
     */
    public void requestEditFamialy(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10019");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 家庭成员列表
     *
     * @return
     */
    public Observable<ResponseBody> requestFamilyMember(Map<String, String> params) {
        params.put("request_code", "10022");
        return apiService.post(params);
    }

    public void requestFamilyMember(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10022");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 判断是否有户主
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestHasMaster(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10028");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 删除成员
     *
     * @return
     */
    public void requestDeleteMember(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10015");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 物业信息
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestWuYeDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40011");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 请求开锁记录列表
     *
     * @return
     */
    public Observable<ResponseBody> requestUnLockRecord(Map<String, String> params) {
        params.put("request_code", "10006");
        return apiService.post(params);
    }

    /**
     * 在线报修接口
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestOnlineRepairs(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "20001");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 用户的小区列表
     */
    public Observable<ResponseBody> requestCommunityList(Map<String, String> params) {
        params.put("request_code", "10003");
        return apiService.post(params);
    }

    /**
     * 用户的小区列表
     */
    public void requestCommunityList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10003");
        request(apiService.post(params), znzHttpListener);
    }


    /**
     * 查询账单
     *
     * @param params
     * @return
     */
    public Observable<ResponseBody> requestZhangdanList(Map<String, String> params) {
        params.put("request_code", "88000");
        return apiService.post(params);
    }

    /**
     * 查询饼图数据
     *
     * @return
     */
    public void requestBingTuList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "88005");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 查询账单分类
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestZhangdanType(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "88003");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 是否签到
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestIsSign(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10004");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 签到
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestSign(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10005");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 收藏和取消收藏接口
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestShouCang(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "20008");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 用户积分列表
     */
    public Observable<ResponseBody> requestScoreList(Map<String, String> params) {
        params.put("request_code", "40006");
        return apiService.post(params);
    }

    /**
     * 用户积分列表
     */
    public void requestScoreList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40006");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 赠送积分
     */
    public void requestSendScore(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10014");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 商品分类
     */
    public void requestGoodsClassify(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "50000");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 商品列表接口
     */
    public Observable<ResponseBody> requestProductList(Map<String, String> params) {
        params.put("request_code", "50001");
        return apiService.post(params);
    }

    /**
     * 商品详情接口
     */
    public void requestProductDetial(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "50002");
        request(apiService.post(params), znzHttpListener, LODING_LODING);
    }

    /**
     * 添加商品到购物车
     */
    public void requestAddProduct(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "60001");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 购物车列表
     */
    public Observable<ResponseBody> requestCartList(Map<String, String> params) {
        params.put("request_code", "60000");
        return apiService.post(params);
    }

    /**
     * 从购物车删除某一商品
     */
    public void requestRemoveProduct(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "60003");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 编辑购物车中商品数量
     */
    public void requestUpdateCart(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "60002");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 获取用户的购物车商品总数
     */
    public void requestProductCount(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "60004");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 余额支付接口
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestPayOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "99700");
        request(apiService.post(params), znzHttpListener);

    }

    /**
     * 保存订单接口
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestAddOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "80000");
        params.put("res", "1");
        request(apiService.post(params), znzHttpListener);

    }

    /**
     * 订单列表
     *
     * @param params
     */
    public Observable<ResponseBody> requestOrderList(Map<String, String> params) {
        params.put("request_code", "80003");
        return apiService.post(params);

    }

    /**
     * 订单列表
     *
     * @param params
     */
    public void requestOrderList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "80003");
        request(apiService.post(params), znzHttpListener);

    }

    /**
     * 订单详情
     *
     * @param params
     */
    public void requestOrderDetail(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "80005");
        request(apiService.post(params), znzHttpListener);

    }

    /**
     * 修改订单状态
     *
     * @param params
     */
    public void requestUpdateOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "80007");
        request(apiService.post(params), znzHttpListener);

    }

    /**
     * 签到列表
     */
    public Observable<ResponseBody> requestSignList(Map<String, String> params) {
        params.put("request_code", "10017");
        return apiService.post(params);
    }


    /**
     * Banner列表
     */
    public void requestBanner(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40008");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 活动列表
     */
    public Observable<ResponseBody> requestActivityList(Map<String, String> params) {
        params.put("request_code", "40009");
        return apiService.post(params);
    }

    /**
     * 活动详情接口
     */
    public void requestActivitytDetial(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40010");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 管家服务
     */
    public void requestGuanjiaService(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "40012");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 消息列表
     */
    public Observable<ResponseBody> requestMsgList(Map<String, String> params) {
        params.put("request_code", "30003");
        params.put("type", "1");
        return apiService.post(params);
    }

    /**
     * 用户优惠券列表
     */
    public Observable<ResponseBody> requestMineCouponList(Map<String, String> params) {
        params.put("request_code", "70001");
        return apiService.post(params);
    }

    /**
     * 优惠券列表
     */
    public Observable<ResponseBody> requestCouponList(Map<String, String> params) {
        params.put("request_code", "70000");
        return apiService.post(params);
    }

    /**
     * 用户领取优惠券接口
     */
    public void requestReceiveCoupon(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "70002");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 查询可用的优惠券列表
     */
    public Observable<ResponseBody> requestCouponAvailable(Map<String, String> params) {
        params.put("request_code", "70003");
        return apiService.post(params);
    }

    /**
     * 修改登录密码、支付密码
     * 设置支付密码
     */
    public void requestModifyPassword(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30001");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 修改被叫顺序接口
     */
    public void requestCalledOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10025");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 银行卡列表
     */
    public void requestCardList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30004");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 删除银行卡
     */
    public void requestDeleteCard(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30006");
        request(apiService.post(params), znzHttpListener);
    }


    /**
     * 支付参数
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestPayParams(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30004");
        request(apiService.post(params), znzHttpListener);
    }


    /**
     * 添加银行卡
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestCardAdd(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "30005");
        request(apiService.post(params), znzHttpListener);
    }

    /**
     * 充值卡列表
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestRechargeList(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "81000");
        request(apiService.post(params), znzHttpListener, LODING_LODING);
    }

    /**
     * 充值下单
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestRechargeOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "81003");
        request(apiService.post(params), znzHttpListener, LODING_PD);
    }

    /**
     * 获取充值记录
     *
     * @param params
     * @param
     */
    public Observable<ResponseBody> requestRechargeRecord(Map<String, String> params) {
        params.put("request_code", "81005");
        return apiService.post(params);
    }

    /**
     * 单张图片上传
     *
     * @param url
     * @param znzHttpListener
     */
    public void uploadImageSingle(String url, ZnzHttpListener znzHttpListener) {
        Map<String, String> params = new HashMap();
        params.put("request_code", "93333");
        if (ZnzConstants.APP_DEBUG) {
            params.put("bucket", "imgtest");
        } else {
            params.put("bucket", "imgtest");
        }
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
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file1);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile", file.getName(), requestBody);
                    request(apiService.uploadImageSingle(params, body), znzHttpListener, LODING_PD);
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
        params.put("request_code", "93333");
        if (ZnzConstants.APP_DEBUG) {
            params.put("bucket", "imgtest");
        } else {
            params.put("bucket", "imgtest");
        }
        List<MultipartBody.Part> partLlist = new ArrayList<>();
        for (File file : url) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile", file.getName() + ".jpg", requestBody);
            partLlist.add(body);
        }
        request(apiService.uploadImageMulti(params, partLlist), znzHttpListener);
    }

    /**
     * 缴费列表接口
     */
    public Observable<ResponseBody> requestPayList(Map<String, String> params) {
        params.put("request_code", "82000");
        return apiService.post(params);
    }

    /**
     * 缴费账户余额充值 下单
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestOnlinePayOrder(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "82003");
        request(apiService.post(params), znzHttpListener);

    }

    /**
     * 缴费
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestOnlinePay(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "82005");
        request(apiService.post(params), znzHttpListener);

    }

    /**
     * 用户等级
     *
     * @param params
     * @param znzHttpListener
     */
    public void requestUserLevel(Map<String, String> params, ZnzHttpListener znzHttpListener) {
        params.put("request_code", "10030");
        request(apiService.post(params), znzHttpListener);

    }


}
