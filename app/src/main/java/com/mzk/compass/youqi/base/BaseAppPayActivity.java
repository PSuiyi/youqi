package com.mzk.compass.youqi.base;

import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.event.EventPay;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzpay.alipay.AliPayUtil;
import com.znz.compass.znzpay.bean.WeixinBean;
import com.znz.compass.znzpay.common.PayKeys;
import com.znz.compass.znzpay.wxpay.WXPayUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Date： 2017/9/4 2017
 * User： PSuiyi
 * Description：
 */

public abstract class BaseAppPayActivity extends BaseAppActivity {

    protected String currentOrderCode;

    @Override
    protected void initializeAppBusiness() {
        super.initializeAppBusiness();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventPay event) {
        if (event.getFlag() == EventTags.PAY_WX_SUCCESS) {
            onPayResult(PayKeys.WX_PAY_SUCESSS);
        }
        if (event.getFlag() == EventTags.PAY_WX_FAIL) {
            onPayResult(PayKeys.WX_PAY_FAIL);
        }
    }

    /**
     * 微信支付
     */
    public void handleWeixinPay() {
        Map<String, String> params = new HashMap<>();
        params.put("orderSerial", currentOrderCode);
        params.put("type", "1");
        mModel.requestPayParams(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                WeixinBean bean = JSONObject.parseObject(responseOriginal.getString("object"), WeixinBean.class);
                Map<String, String> params = new HashMap<>();
                params.put("appid", bean.getAppid());
                params.put("msg", bean.getMsg());
                params.put("out_trade_no", bean.getOut_trade_no());
                params.put("status", bean.getStatus());
                params.put("partnerid", bean.getPartnerid());
                params.put("prepayid", bean.getPrepayid());
                params.put("packageStr", bean.getPackageStr());
                params.put("nonceStr", bean.getNonceStr());
                params.put("timeStamp", bean.getTimestamp());
                params.put("paySign", bean.getPaySign());
                WXPayUtil.getInstance(activity).startWXPay(params);
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    /**
     * 支付宝支付
     */
    public void handleAliPay() {
        Map<String, String> params = new HashMap<>();
        params.put("orderSerial", currentOrderCode);
        params.put("type", "1");
        mModel.requestPayParams(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                AliPayUtil.getInstance(activity).startAliPay(responseOriginal.getString("data"), result -> {
                    switch (result) {
                        case "支付成功":
                            onPayResult(PayKeys.ALI_PAY_SUCESSS);
                            break;
                        case "支付取消":
                            mDataManager.showToast("支付取消");
                            break;
                        case "支付失败":
                            onPayResult(PayKeys.ALI_PAY_FAIL);
                            break;
                    }
                });
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }


    public void handleAliPay(String key) {
        AliPayUtil.getInstance(activity).startAliPay(key, result -> {
            switch (result) {
                case "支付成功":
                    onPayResult(PayKeys.ALI_PAY_SUCESSS);
                    break;
                case "支付取消":
                    mDataManager.showToast("支付取消");
                    break;
                case "支付失败":
                    onPayResult(PayKeys.ALI_PAY_FAIL);
                    break;
            }
        });
    }

    /**
     * 余额支付
     */
    public void handleOwnPay() {
//        Map<String, String> params2 = new HashMap<>();
//        mModel.requestUserDetail(params2, new ZnzHttpListener() {
//            @Override
//            public void onSuccess(JSONObject responseOriginal) {
//                super.onSuccess(responseOriginal);
//                UserBean bean = JSON.parseObject(responseOriginal.getString("object"), UserBean.class);
//                if (!StringUtil.isBlank(bean.getIs_pay_password()) && bean.getIs_pay_password().equals("1")) {
//                    PopupWindowManager.getInstance(activity).showInputPsdUpDialog(znzToolBar, (type, values) -> {
//                        Map<String, String> params = new HashMap<>();
//                        params.put("order_code", currentOrderCode);
//                        params.put("pay_password", values[0]);
//                        mModel.requestPayOrder(params, new ZnzHttpListener() {
//                            @Override
//                            public void onSuccess(JSONObject responseOriginal) {
//                                super.onSuccess(responseOriginal);
//                                JSONObject json = JSON.parseObject(responseOriginal.getString("object"));
//                                if (json.getString("code").equals("10")) {
//                                    onPayResult(PayKeys.PAY_SUCESSS);
//                                } else {
//                                    mDataManager.showToast(json.getString("msg"));
//                                }
//                            }
//
//                            @Override
//                            public void onFail(String error) {
//                                super.onFail(error);
//                                mDataManager.showToast(error);
//                                onPayResult(PayKeys.PAY_FAIL);
//                            }
//                        });
//                    });
//                } else {
//                    new UIAlertDialog(activity)
//                            .builder()
//                            .setMsg("您还没有设置支付密码，请设置支付密码")
//                            .setNegativeButton("取消", null)
//                            .setPositiveButton("确定", v2 -> {
//                                gotoActivity(PayPsdSettingAct.class);
//                            })
//                            .show();
//                }
//            }
//        });

    }


    protected abstract void onPayResult(int result);
}
