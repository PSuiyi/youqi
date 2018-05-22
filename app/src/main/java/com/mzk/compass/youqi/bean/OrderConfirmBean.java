package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Created by Administrator on 2018/5/22.
 */

public class OrderConfirmBean extends BaseZnzBean {

    /**
     * orderSerial : 744798a3df0d348e51a0479b992809d1
     * productName : 产品名称-测试
     * productMobileImage : products/5ae2bc129e89b.jpg
     * productPrice : 23.00
     * num : 2
     */

    private String orderSerial;
    private String productName;
    private String productMobileImage;
    private String productPrice;
    private String num;

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductMobileImage() {
        return productMobileImage;
    }

    public void setProductMobileImage(String productMobileImage) {
        this.productMobileImage = productMobileImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
