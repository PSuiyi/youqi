package com.znz.compass.umeng.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.znz.compass.umeng.login.alipay.AuthResult;
import com.znz.compass.umeng.login.alipay.OrderInfoUtil2_0;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/10.
 */

public class AlipayAuthManager {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2017112700203093";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088621819395945";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = System.currentTimeMillis() + "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDXSyGJQaS7ncbpxXmBZ8/eWo0pYgQsekMmJgT/ZavkytQNRszGHMQnSFCtfuCvbEFUEFb1YbGI0jGfv5maloexYCul1gWQjVTL+gZYkQgAQTzgfuyYct2RPe0C7bLgQmMDEOtS9xptwsA1ERX0QJdHh/gbpOCuCsqx8pQi7m+L+reF6AHg0vMlkgVqlpUhFO6hRLuVwJlveK4AI5LMvWyVHaS4YTqDEJ8o1gtBhxLnOeCJXy0zeU40eZGt7nt83LES8fJWF2xYpz1a47CkGQb9IU0oTzySFjThsNHrSjSZ21JNZfBUPNOgTkvkP8V5wvGcOq6VEVFLiikBdt3cr22DAgMBAAECggEAC7Rm91pdRWH3jYAR8godz/3mTMiqZ2yiN/G7NdNmPAMW8j3WJjMsKuKQmE4ckwz0pEhbIe4Uw9aBoii9hh0G9Yq/9L0lAk3cvVYzgB8qazEgySe9XzBngVz3hMA9iFmznlgOIy2R/0OmRXxPUD8uulHXGFGncW7exDvm99s0r0xxpMx3HdYkbDvQz4+aNuNaVdE6WzdfO5FXEd1pN1JSyJZvvPW75A7f2jI1gYnqRAm8G3WGdIiK++w75jVHw4ysnMpT4xvJf94b4hJuSfBtDes2gSKuHyvVIgycfbF4PQq8Mbnc5eQe0q9QcjX1HS04blchSnnJvmdJHyFJ/f4asQKBgQDsAaU86k/AbykzCNMB3I3UsFlfvaTkX6S4EzW2yQoir9mw2bVnYLF9Z+Vu5R8LGIAQSnSnRjwGjYPO6uMrISnj0JWpB71etC650dR+IzVg0rql5dT3UikxiCYOsDhW7O5rq92xXqt/ugIbNiyF/nJ+dPfBeAIUyRmmeon9PM0EaQKBgQDpiEbMjqvYqHQZV5p+UwAm3J4JQdGGuSLgpK+PXsRPOAU0XLu21yl3dxL57V5YSEuV9jsSmpzNeXar9C0oNN5CVzN/vJeoEm3BGPOUxGBhYX2K/23ADSGoLmD6rT3Xvz+5+rGDvKlYlXT9xdQRoc01d1VYoPg6jkrlEyVqDXK1CwKBgQCZXjRgUrCDEHoIlTpyoj51MijSY+5tDo5/pNQqNZllkzt3hvMw/BA7u7HIkBLNZh2K+Ke4MJc/duVqKRB5bbLIPjuTvdjdW2BmFyrs0bqVcjwjJSlxk637Z0u8Dd5+9re1ttl5s1jlxx4pStpCNWl4LlD3JCLOGSWXH1je/Y0JyQKBgHVy1CiMmGCeV8VnCYv+slTZH6IIOBhJhAD20lSIlLJCultbSAzsqS4r9J89gfVkZp9E9vCkUbgFHmnco9lUvXMf6AaWEq+0lP5ITjjDirVvEnSVgdzUvdiXeq6X8kJPLP2b1ysp9wFWKUB3/e/gMBRzr7ijPDQ22/PZbTepC8eBAoGBAM9WR1dJWLK4YZnhe6UNbVsNMRRlTIQR5pVdISbocY2vozASbSa8audGXH7zFO+ilDUMIYbvpuyJAu0vOX7Rm2NJowJXGHYsLSiTxAYK53TvDOMoOfBFzHG2Faw8gTA8GI7Jm2kjvnA5U9hHNhAPb7pVxBx9dV/R180dVh8rY0yX";

    private static final int SDK_AUTH_FLAG = 2;

    private static AlipayAuthManager instance;
    private Context mContext;

    public AlipayAuthManager(Context context) {
        this.mContext = context;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    Intent mIntent = new Intent("0xff01");
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
//                        Toast.makeText(mContext,
//                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
//                                .show();
                        mIntent.putExtra("openid", authResult.getAlipayOpenId());
                        mContext.sendBroadcast(mIntent);

                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(mContext,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    public static AlipayAuthManager getInstance(Context context) {
        if (instance == null) {
            instance = new AlipayAuthManager(context.getApplicationContext());
        }
        return instance;
    }

    public void loginAlipay(final Activity activity) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(mContext).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(activity);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

}
