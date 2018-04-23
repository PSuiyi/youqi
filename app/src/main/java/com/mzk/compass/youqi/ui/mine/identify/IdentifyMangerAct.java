package com.mzk.compass.youqi.ui.mine.identify;

import android.os.Bundle;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/23.
 */

public class IdentifyMangerAct extends BaseAppActivity {

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_identify_manger, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("认证管理");
    }

    @Override
    protected void initializeView() {
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.llPersonal)
    public void onViewClicked() {
        gotoActivity(MemberMangerAct.class);
    }
}
