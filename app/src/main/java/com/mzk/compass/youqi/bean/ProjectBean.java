package com.mzk.compass.youqi.bean;

import com.mzk.compass.youqi.adapter.TagYouBean;
import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ProjectBean extends BaseZnzBean {


    /**
     * id : 101
     * name : 项目一
     * logo : project/a.jpg
     * title : 
     * rname : null
     * state : 1
     * tradeid : ["互联网","娱乐"]
     * collectionNum : 0
     * visiteNum : 0
     * commentsNum : 0
     * rongzistate : 1
     */

    private String id;
    private String name;
    private String logo;
    private String title;
    private String rname;
    private String state;
    private String collectionNum;
    private String visiteNum;
    private String commentsNum;
    private String rongzistate;
    private String companyName;
    private String companyLogo;
    private List<TagYouBean> tradeid;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(String collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getVisiteNum() {
        return visiteNum;
    }

    public void setVisiteNum(String visiteNum) {
        this.visiteNum = visiteNum;
    }

    public String getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(String commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getRongzistate() {
        return rongzistate;
    }

    public void setRongzistate(String rongzistate) {
        this.rongzistate = rongzistate;
    }

    public List<TagYouBean> getTradeid() {
        return tradeid;
    }

    public void setTradeid(List<TagYouBean> tradeid) {
        this.tradeid = tradeid;
    }
}
