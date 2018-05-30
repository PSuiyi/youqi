package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Created by Administrator on 2018/5/10.
 */

public class OrderBean extends BaseZnzBean {

    /**
     * orderSerial : 20180425811540486309218
     * state : 2
     * num : 2
     * productName :
     * productPrice : 0.00
     * productMobileImage :
     */

    private String orderSerial;
    private String state;
    private String num;
    private String productName;
    private String productPrice;
    private String productMobileImage;
    /**
     * note :
     * addTime : 1524640486
     * buyerTel :
     * adminid : 0
     */

    private String note;
    private String addTime;
    private String buyerTel;
    private String adminid;


    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductMobileImage() {
        return productMobileImage;
    }

    public void setProductMobileImage(String productMobileImage) {
        this.productMobileImage = productMobileImage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }
}
