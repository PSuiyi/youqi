package com.mzk.compass.youqi.bean;

import com.znz.compass.znzlibray.bean.BaseZnzBean;

/**
 * Date： 2018/5/16 2018
 * User： PSuiyi
 * Description：
 */
public class TagYouBean extends BaseZnzBean{
    private String name;
    private String id;
    private String backgroundcolor;
    private String fontcolor;

    public String getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    public String getFontcolor() {
        return fontcolor;
    }

    public void setFontcolor(String fontcolor) {
        this.fontcolor = fontcolor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
