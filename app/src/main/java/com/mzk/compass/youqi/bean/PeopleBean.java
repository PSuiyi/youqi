package com.mzk.compass.youqi.bean;

import com.mzk.compass.youqi.adapter.TagYouBean;
import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Date： 2018/5/11 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleBean extends BaseZnzBean {

    /**
     * adPositionid : 31
     * title : 赵传喜
     * link : 57
     * image : ads/5af24d2602015.png
     * sort : 1
     * contentType : approveinvestors
     * id : 57
     * realName : 赵传喜
     * uid : 2
     * groupName :
     * avatar :
     * introduce : 集设计、研发、生产为一体，拥有多年国际衍生品开发经验，与美国华纳影业、日本CAPCOM 游戏社、韩国SNK 游戏厂等合作，并获制集设计、研发、生产为一体，拥有多年国际衍生品开发经验，与美国华纳影业、日本CAPCOM 游戏社、韩国SNK 游戏厂等合作，并获制
     */

    private String adPositionid;
    private String title;
    private String link;
    private String image;
    private String username;
    private String sort;
    private String contentType;
    private String id;
    private String realName;
    private String uid;
    private String name;
    private String groupName;
    private String avatar;
    private String introduce;
    private String commentsNum;
    private String projectid;
    private String userPhoto;
    private String userName;
    private String position;
    private String profile;
    private String collectionNum;
    private List<TagYouBean> tradeid;

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TagYouBean> getTradeid() {
        return tradeid;
    }

    public void setTradeid(List<TagYouBean> tradeid) {
        this.tradeid = tradeid;
    }

    public String getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(String commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(String collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
