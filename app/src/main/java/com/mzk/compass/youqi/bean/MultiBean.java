package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;


public class MultiBean extends BaseZnzBean implements MultiItemEntity {
    private int itemType;
    private String section;
    private List<OrganBean> organList = new ArrayList<>();
    private List<PeopleBean> peopleList = new ArrayList<>();

    public MultiBean() {
    }

    public MultiBean(int itemType) {
        this.itemType = itemType;
    }

    public MultiBean(int itemType, String section) {
        this.itemType = itemType;
        this.section = section;
    }

    public MultiBean(int itemType, List<OrganBean> organList) {
        this.itemType = itemType;
        this.organList = organList;
    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<OrganBean> getOrganList() {
        return organList;
    }

    public void setOrganList(List<OrganBean> organList) {
        this.organList = organList;
    }

    public List<PeopleBean> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<PeopleBean> peopleList) {
        this.peopleList = peopleList;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
