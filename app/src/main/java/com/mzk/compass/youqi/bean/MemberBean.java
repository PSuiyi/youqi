package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Created by Administrator on 2018/5/30.
 */

public class MemberBean extends BaseZnzBean {

    /**
     * username : Woshi niqu
     * avatar :
     * tel : 18115127617
     * invitename : 普京
     * id : 1
     * state : 3
     */

    private String username;
    private String avatar;
    private String tel;
    private String invitename;
    private String id;
    private String state;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getInvitename() {
        return invitename;
    }

    public void setInvitename(String invitename) {
        this.invitename = invitename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
