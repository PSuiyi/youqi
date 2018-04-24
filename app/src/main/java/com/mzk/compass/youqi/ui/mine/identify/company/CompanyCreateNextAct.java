package com.mzk.compass.youqi.ui.mine.identify.company;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class CompanyCreateNextAct extends BaseAppActivity {


    @Bind(R.id.tvHangye)
    TextView tvHangye;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_compant_create_next, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("创建企业");
        znzToolBar.setNavRightText("提交");
        znzToolBar.setOnNavRightClickListener(view -> {

        });
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

    @OnClick({R.id.llHangye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llHangye:
                gotoActivity(IndustryAct.class);
                break;
        }
    }
}
