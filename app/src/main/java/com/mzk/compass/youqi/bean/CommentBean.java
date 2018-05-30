package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Date： 2018/5/29 2018
 * User： PSuiyi
 * Description：
 */
public class CommentBean extends BaseZnzBean {

    /**
     * id : 51
     * pid : 0
     * content : tewrwqeqwe
     * addTime : 1524652670
     * username : zhaozhao
     * avatar :
     * reply : [{"content":"广东佛山大碗茶","username":"zhaozhao","pid":"51"}]
     */

    private String id;
    private String pid;
    private String content;
    private String addTime;
    private String username;
    private String avatar;
    private List<CommentReplyBean> reply;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
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

    public List<CommentReplyBean> getReply() {
        return reply;
    }

    public void setReply(List<CommentReplyBean> reply) {
        this.reply = reply;
    }


}
