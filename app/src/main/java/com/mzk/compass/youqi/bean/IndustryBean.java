package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Created by Administrator on 2018/4/24.
 */

public class IndustryBean extends BaseZnzBean {
    private String name;

    public IndustryBean() {
    }

    public IndustryBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
