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
    private String addTime;
    private String title;
    private String type;


    private boolean edit;
    /**
     * uid : 1
     * orderSerial : 6217e041-7419-40e5-a5d5-e60b400d8d60
     * content : 订单阿斯蒂芬
     */

    private String uid;
    private String orderSerial;
    private String content;

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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
