package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class IdentifyBean extends BaseZnzBean {

    /**
     * realName : 真实姓名
     * groupName : 很好
     * nameCard : approveinvestors/1ba7de5e4ff68cbb1d2b58fa8e14ee10.jpg
     * example : vsd
     * roleid : 4
     * tradeid : [{"id":"2","name":"互联网"},{"id":"3","name":"娱乐"}]
     * roundsid : [{"id":"3","name":"C轮"},{"id":"4","name":"D轮"}]
     */

    private String realName;
    private String groupName;
    private String nameCard;
    private String example;
    private String roleid;
    private List<IndustryBean> tradeid;
    private List<IndustryBean> roundsid;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public List<IndustryBean> getTradeid() {
        return tradeid;
    }

    public void setTradeid(List<IndustryBean> tradeid) {
        this.tradeid = tradeid;
    }

    public List<IndustryBean> getRoundsid() {
        return roundsid;
    }

    public void setRoundsid(List<IndustryBean> roundsid) {
        this.roundsid = roundsid;
    }
}
