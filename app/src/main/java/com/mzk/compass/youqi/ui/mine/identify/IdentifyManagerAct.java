package com.mzk.compass.youqi.ui.mine.identify;

import android.os.Bundle;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.mine.identify.company.CompanyManagerAct;
import com.mzk.compass.youqi.ui.mine.identify.personal.IdentifyPersonalAct;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class IdentifyManagerAct extends BaseAppActivity {

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_identify_manager, 1};
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

    @OnClick({R.id.llCompany, R.id.llPersonal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCompany:
                gotoActivity(CompanyManagerAct.class);
                break;
            case R.id.llPersonal:
                gotoActivity(IdentifyPersonalAct.class);
                break;
        }
    }
}
