package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.entity.MultiItemEntity;


public class MultiBean extends BaseZnzBean implements MultiItemEntity {
    private int itemType;
    private String section;

    public MultiBean() {
    }

    public MultiBean(int itemType) {
        this.itemType = itemType;
    }

    public MultiBean(int itemType, String section) {
        this.itemType = itemType;
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }


    @Override
    public int getItemType() {
        return itemType;
    }
}
