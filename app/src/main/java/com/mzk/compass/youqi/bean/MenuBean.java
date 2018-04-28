package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Date： 2018/4/28 2018
 * User： PSuiyi
 * Description：
 */
public class MenuBean extends BaseZnzBean {
    private String title;

    public MenuBean() {
    }

    public MenuBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
