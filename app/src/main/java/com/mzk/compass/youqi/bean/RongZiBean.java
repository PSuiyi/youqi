package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/8.
 */

public class RongZiBean extends BaseZnzBean {

    /**
     * name : 项目7
     * logo : project/logo/2b73336fdcbb214ae965667f99e097c4
     * rongziroundsid : 0
     * rongzijine :
     * rongzistate : 1
     * title :
     * rname : null
     * tradeid : [{"id":"2","name":"互联网"},{"id":"3","name":"娱乐"}]
     */

    private String name;
    private String logo;
    private String rongziroundsid;
    private String rongzijine;
    private String rongzistate;
    private String title;
    private String rname;
    private List<TagYouBean> tradeid;

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

    public String getRongziroundsid() {
        return rongziroundsid;
    }

    public void setRongziroundsid(String rongziroundsid) {
        this.rongziroundsid = rongziroundsid;
    }

    public String getRongzijine() {
        return rongzijine;
    }

    public void setRongzijine(String rongzijine) {
        this.rongzijine = rongzijine;
    }

    public String getRongzistate() {
        return rongzistate;
    }

    public void setRongzistate(String rongzistate) {
        this.rongzistate = rongzistate;
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

    public List<TagYouBean> getTradeid() {
        return tradeid;
    }

    public void setTradeid(List<TagYouBean> tradeid) {
        this.tradeid = tradeid;
    }
}
