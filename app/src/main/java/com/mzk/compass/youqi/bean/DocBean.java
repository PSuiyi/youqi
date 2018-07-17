package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Date： 2018/7/17 2018
 * User： PSuiyi
 * Description：
 */
public class DocBean extends BaseZnzBean {


    /**
     * filetype : 3
     * projectid : 2
     * docid : doc-idqmwcmrpxx62e3
     * state : 1
     */

    private String filetype;
    private String projectid;
    private String docid;
    private String state;

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
