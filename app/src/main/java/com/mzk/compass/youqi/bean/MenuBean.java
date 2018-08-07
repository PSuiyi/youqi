package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Date： 2018/4/28 2018
 * User： PSuiyi
 * Description：
 */
public class MenuBean extends BaseZnzBean {
    private String title;
    private String id;
    private String name;
    private String image;
    private List<MenuBean> son;

    public MenuBean() {
    }

    public List<MenuBean> getSon() {
        return son;
    }

    public void setSon(List<MenuBean> son) {
        this.son = son;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
