package com.znz.compass.znzpay.bean;


import java.io.Serializable;

/**
 * Date： 2018/1/5 2018
 * User： PSuiyi
 * Description：
 */

public class WeixinBean implements Serializable {

    /**
     * appId : wx6f2f97e86d78153e
     * partnerId : 1416166602
     * prepayId : wx20180104121726af88c029130417474392
     * package : Sign=WXPay
     * nonceStr : l2tMJRImxxri47XS
     * timeStamp : 1515039446
     * sign : BE77DC24CD9B245467095B8F396D4993
     */

    private String msg;
    private String timestamp;
    private String out_trade_no;
    private String packageStr;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String status;

    public String getAppid() {
        return appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
