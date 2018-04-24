package com.mzk.compass.youqi.ui.mine.identify.company;

import android.os.Bundle;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */ 

public class CompanyManagerAct extends BaseAppActivity {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_company_manager, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("企业认证管理");
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

    @OnClick({R.id.llCreate, R.id.llManager, R.id.llIdentify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCreate:
                break;
            case R.id.llManager:
                break;
            case R.id.llIdentify:
                break;
        }
    }
}
