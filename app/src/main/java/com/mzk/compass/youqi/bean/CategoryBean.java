package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class CategoryBean extends BaseZnzBean {

    /**
     * id : 2
     * name : 代理记账
     * pid : 0
     * image :
     * son : [{"id":"11","name":"申报代帐","pid":"2","image":"","son":[]}]
     */

    private String id;
    private String name;
    private String pid;
    private String image;
    private List<CategoryBean> son;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CategoryBean> getSon() {
        return son;
    }

    public void setSon(List<CategoryBean> son) {
        this.son = son;
    }
}
