package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public class CompanyBean extends BaseZnzBean {

    /**
     * cname : asdfafd
     * shorName : asdf
     * provinceid : 1
     * cityid : 110000
     * areaid : 110100
     * address : asdf
     * summary : hena hao
     * website : http://asdf.com
     * fax : aadfasdf
     * email : a@qq.com
     * tradeid : [{"id":"1","name":"人工智能"},{"id":"2","name":"互联网"}]
     * basicAddressInfo : 北京市北京市东城区
     */

    private String cname;
    private String shorName;
    private String provinceid;
    private String cityid;
    private String areaid;
    private String address;
    private String summary;
    private String website;
    private String fax;
    private String email;
    private String basicAddressInfo;
    private List<IndustryBean> tradeid;
    /**
     * cname : null
     * shorName : null
     * provinceid : null
     * cityid : null
     * areaid : null
     * address : null
     * uid : null
     * companyid : 0
     * idCard : null
     * licensePhoto : null
     * basicAddressInfo : null
     */

    private String uid;
    private String companyid;
    private String idCard;
    private String licensePhoto;


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getShorName() {
        return shorName;
    }

    public void setShorName(String shorName) {
        this.shorName = shorName;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBasicAddressInfo() {
        return basicAddressInfo;
    }

    public void setBasicAddressInfo(String basicAddressInfo) {
        this.basicAddressInfo = basicAddressInfo;
    }

    public List<IndustryBean> getTradeid() {
        return tradeid;
    }

    public void setTradeid(List<IndustryBean> tradeid) {
        this.tradeid = tradeid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLicensePhoto() {
        return licensePhoto;
    }

    public void setLicensePhoto(String licensePhoto) {
        this.licensePhoto = licensePhoto;
    }
}
