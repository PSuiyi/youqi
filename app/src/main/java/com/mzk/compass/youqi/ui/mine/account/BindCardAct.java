package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/26.
 */

public class BindCardAct extends BaseAppActivity {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_bind_card, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("绑定银行卡");
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

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString("from", "绑定银行卡");
        gotoActivity(ManagerSuccessAct.class, bundle);
    }
}