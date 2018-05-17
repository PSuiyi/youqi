package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class BannerBean extends BaseZnzBean {

    /**
     * adPositionid : 36
     * title : 优企助banner1
     * link : 1
     * image : ads/5aefeb1017237.png
     * sort : 1
     * contentType : project
     * id : 1
     * logo : project/logo/5fdeb5a8bbd4d30ecae96257b5ed2e56
     * name : 项目1
     * roundsid : A轮
     * tradeid : [{"name":"互联网"},{"name":"娱乐"}]
     * turnoverid : 10~20万
     * commentsNum : 11
     * visiteNum : 380
     */

    private String adPositionid;
    private String title;
    private String link;
    private String image;
    private String sort;
    private String contentType;
    private String id;
    private String logo;
    private String name;
    private String roundsid;
    private String turnoverid;
    private String commentsNum;
    private String visiteNum;
    private List<TradeidBean> tradeid;

    public String getAdPositionid() {
        return adPositionid;
    }

    public void setAdPositionid(String adPositionid) {
        this.adPositionid = adPositionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoundsid() {
        return roundsid;
    }

    public void setRoundsid(String roundsid) {
        this.roundsid = roundsid;
    }

    public String getTurnoverid() {
        return turnoverid;
    }

    public void setTurnoverid(String turnoverid) {
        this.turnoverid = turnoverid;
    }

    public String getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(String commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getVisiteNum() {
        return visiteNum;
    }

    public void setVisiteNum(String visiteNum) {
        this.visiteNum = visiteNum;
    }

    public List<TradeidBean> getTradeid() {
        return tradeid;
    }

    public void setTradeid(List<TradeidBean> tradeid) {
        this.tradeid = tradeid;
    }

    public static class TradeidBean {
        /**
         * name : 互联网
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
