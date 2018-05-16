package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Date： 2018/5/11 2018
 * User： PSuiyi
 * Description：
 */
public class ProductBean extends BaseZnzBean {

    /**
     * adPositionid : 38
     * title : 测试商品24
     * link : 1
     * image : ads/5af25a22da7a7.png
     * sort : 3
     * contentType : products
     * marketPrice : 100.00
     * realPrice : 99.00
     * showNum : 12
     */

    private String adPositionid;
    private String title;
    private String link;
    private String image;
    private String sort;
    private String contentType;
    private String marketPrice;
    private String realPrice;
    private String showNum;

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

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getShowNum() {
        return showNum;
    }

    public void setShowNum(String showNum) {
        this.showNum = showNum;
    }
}
