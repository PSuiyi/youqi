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
    private String address;
    private String runState;
    private String createTime;
    private String projectProfile;
    private String productshape;
    private String market;
    private String solusion;
    private String profitModel;
    private String financing;
    private String roadshowVideo;
    private String rongzijine;
    private String province;
    private String city;
    private String link;
    private String area;
    private String isCollected;
    private String showProjectResource;
    private List<TagYouBean> tradeid;
    private List<PeopleBean> team;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<PeopleBean> getTeam() {
        return team;
    }

    public void setTeam(List<PeopleBean> team) {
        this.team = team;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProjectProfile() {
        return projectProfile;
    }

    public void setProjectProfile(String projectProfile) {
        this.projectProfile = projectProfile;
    }

    public String getProductshape() {
        return productshape;
    }

    public void setProductshape(String productshape) {
        this.productshape = productshape;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSolusion() {
        return solusion;
    }

    public void setSolusion(String solusion) {
        this.solusion = solusion;
    }

    public String getProfitModel() {
        return profitModel;
    }

    public void setProfitModel(String profitModel) {
        this.profitModel = profitModel;
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public String getRoadshowVideo() {
        return roadshowVideo;
    }

    public void setRoadshowVideo(String roadshowVideo) {
        this.roadshowVideo = roadshowVideo;
    }

    public String getRongzijine() {
        return rongzijine;
    }

    public void setRongzijine(String rongzijine) {
        this.rongzijine = rongzijine;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(String isCollected) {
        this.isCollected = isCollected;
    }

    public String getShowProjectResource() {
        return showProjectResource;
    }

    public void setShowProjectResource(String showProjectResource) {
        this.showProjectResource = showProjectResource;
    }

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
