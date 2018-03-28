package com.znz.compass.znzlibray.bean;

import java.io.Serializable;

/**
 * 实体基类
 *
 * @author znz
 */
public class BaseZnzBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean isChecked;
    private boolean isSelect;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
