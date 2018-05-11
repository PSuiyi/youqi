package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Date： 2018/5/7 2018
 * User： PSuiyi
 * Description：
 */
public class NewsBean extends BaseZnzBean {

    /**
     * id : 18
     * image :
     * summary : 测试摘要测试摘要测试摘要18181818
     * name : 政策解读
     * addTime : 1522696324
     * title : 测试标题18181818
     * isCollected :
     */

    private String id;
    private String image;
    private String summary;
    private String name;
    private String addTime;
    private String title;
    private String isCollected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(String isCollected) {
        this.isCollected = isCollected;
    }
}
