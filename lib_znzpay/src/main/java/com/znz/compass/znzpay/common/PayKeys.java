package com.znz.compass.znzpay.common;

/**
 * Date： 2017/4/20 2017
 * User： PSuiyi
 * Description：
 */

public class PayKeys {

    public static String BASE_URL = "";
    //    public static String BASE_URL = "";//杨露露
    public static String ALI_CALLBACK = BASE_URL + "";
    public static String WX_CALLBACK = BASE_URL + "";

    //-------------------微信相关----------------------

    public static final String WX_APPID = "wx42e6b250601dad60";
    public static final String WX_APPSECRET = "";
    //商户号
    public static final String WX_MCH_ID = "";
    //  API密钥，在商户平台设置
    public static final String WX_API_KEY = "";

    //-------------------支付宝相关----------------------

    // APPID
    public static final String APPID = "";
    // 商户PID
    public static final String PARTNER = "";
    // 商户收款账号
    public static final String SELLER = "";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "";

    // 支付宝公钥
    public static final String RSA_PUBLIC = "";

    public static final int WX_PAY_SUCESSS = 1;
    public static final int WX_PAY_FAIL = 2;
    public static final int ALI_PAY_SUCESSS = 3;
    public static final int ALI_PAY_FAIL = 4;
    public static final int OWN_PAY_SUCESSS = 5;
    public static final int OWN_PAY_FAIL = 6;
    public static final int PAY_SUCESSS = 7;
    public static final int PAY_FAIL = 8;

}
