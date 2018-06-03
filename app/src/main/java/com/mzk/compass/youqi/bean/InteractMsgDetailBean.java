package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/3.
 */

public class InteractMsgDetailBean extends BaseZnzBean {

    /**
     * contentType : 1
     * contentId : 1
     * addTime : 1527211816
     * messageId : 39
     * content : 我很看好你的项目哦 哈哈
     * title : 约谈信息提示
     * username : Woshi niqu
     * avatar :
     * projectName : 项目1
     * logo : project/logo/5fdeb5a8bbd4d30ecae96257b5ed2e56
     * projectTitle : 你真的完美哟
     * tradeid : [{"id":"2","name":"互联网"},{"id":"3","name":"娱乐"}]
     * name : A轮
     * uid : 1
     * isMyProject : 1
     */

    private String contentType;
    private String contentId;
    private String addTime;
    private String messageId;
    private String content;
    private String title;
    private String username;
    private String avatar;
    private String projectName;
    private String logo;
    private String projectTitle;
    private String name;
    private String uid;
    private String isMyProject;
    private List<IndustryBean> tradeid;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsMyProject() {
        return isMyProject;
    }

    public void setIsMyProject(String isMyProject) {
        this.isMyProject = isMyProject;
    }

    public List<IndustryBean> getTradeid() {
        return tradeid;
    }

    public void setTradeid(List<IndustryBean> tradeid) {
        this.tradeid = tradeid;
    }
}
