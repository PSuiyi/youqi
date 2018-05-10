package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Created by Administrator on 2018/5/10.
 */

public class OrderBean extends BaseZnzBean {

    /**
     * id : 7
     * state : 2
     * num : 2
     * orderSerial : 20180425811540486309218
     * name : 测试商品24
     * realPrice : 99.00
     * mobilePhoto : products/5ae2bc129e89b.jpg
     */

    private String id;
    private String state;
    private String num;
    private String orderSerial;
    private String name;
    private String realPrice;
    private String mobilePhoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getMobilePhoto() {
        return mobilePhoto;
    }

    public void setMobilePhoto(String mobilePhoto) {
        this.mobilePhoto = mobilePhoto;
    }
}
