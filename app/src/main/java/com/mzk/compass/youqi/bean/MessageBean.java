package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Created by Administrator on 2018/5/11.
 */

public class MessageBean extends BaseZnzBean {

    /**
     * id : 2
     * msgwarning : 有创业者：张大桥  项目：项目1  拿项目拜访您了，注意查看！
     * state : 2
     * time : 1525684351
     */

    private String id;
    private String msgwarning;
    private String state;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgwarning() {
        return msgwarning;
    }

    public void setMsgwarning(String msgwarning) {
        this.msgwarning = msgwarning;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
