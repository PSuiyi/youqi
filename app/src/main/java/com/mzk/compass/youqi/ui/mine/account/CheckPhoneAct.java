package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/26.
 */

public class CheckPhoneAct extends BaseAppActivity {
    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_check_phone, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName(from);
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

    @OnClick(R.id.tvNext)
    public void onViewClicked() {
        switch (from) {
            case "修改手机号":
                gotoActivity(UpdatePhoneAct.class);
                break;
            case "绑定银行卡":
                gotoActivity(BindCardAct.class);
                break;
        }

    }
}
