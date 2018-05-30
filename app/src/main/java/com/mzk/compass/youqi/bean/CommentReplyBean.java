package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

public class CommentReplyBean extends BaseZnzBean {
    /**
     * content : 广东佛山大碗茶
     * username : zhaozhao
     * pid : 51
     */

    private String content;
    private String username;
    private String pid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}