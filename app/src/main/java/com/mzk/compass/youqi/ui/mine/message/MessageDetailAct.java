package com.mzk.compass.youqi.ui.mine.message;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MessageDetailAct extends BaseAppActivity {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_message_detail, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("消息详情");
        znzToolBar.setNavRightText("回复");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }
}
