package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Created by Administrator on 2018/6/7.
 */

public class BankBean extends BaseZnzBean {

    /**
     * bankcard :
     * detailbank :
     */

    private String bankcard;
    private String detailbank;

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getDetailbank() {
        return detailbank;
    }

    public void setDetailbank(String detailbank) {
        this.detailbank = detailbank;
    }
}
