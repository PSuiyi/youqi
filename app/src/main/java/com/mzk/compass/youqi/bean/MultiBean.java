package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;


public class MultiBean extends BaseZnzBean implements MultiItemEntity {
    private int itemType;
    private String value;
    private List<OrganBean> organList = new ArrayList<>();
    private List<PeopleBean> peopleList = new ArrayList<>();
    private List<ProjectBean> projectBeanList = new ArrayList<>();
    private List<StateBean> stateBeanList = new ArrayList<>();
    private PeopleBean peopleBean;

    public MultiBean() {
    }

    public MultiBean(int itemType) {
        this.itemType = itemType;
    }

    public MultiBean(int itemType, String section) {
        this.itemType = itemType;
        this.value = section;
    }


    public MultiBean(int itemType, List<OrganBean> organList) {
        this.itemType = itemType;
        this.organList = organList;
    }

    public MultiBean(int itemType, PeopleBean peopleBean) {
        this.itemType = itemType;
        this.peopleBean = peopleBean;
    }

    public List<StateBean> getStateBeanList() {
        return stateBeanList;
    }

    public void setStateBeanList(List<StateBean> stateBeanList) {
        this.stateBeanList = stateBeanList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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


    public List<ProjectBean> getProjectBeanList() {
        return projectBeanList;
    }

    public void setProjectBeanList(List<ProjectBean> projectBeanList) {
        this.projectBeanList = projectBeanList;
    }

    public PeopleBean getPeopleBean() {
        return peopleBean;
    }

    public void setPeopleBean(PeopleBean peopleBean) {
        this.peopleBean = peopleBean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
