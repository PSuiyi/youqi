package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/26.
 */

public class ManagerSuccessAct extends BaseAppActivity {
    @Bind(R.id.tvStatus)
    TextView tvStatus;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_manager_success, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
    }

    @Override
    protected void initializeNavigation() {
        switch (from) {
            case "修改手机号":
                setTitleName("修改成功");
                break;
            case "绑定银行卡":
                setTitleName("绑定成功");
                break;
        }

    }

    @Override
    protected void initializeView() {
        switch (from) {
            case "修改手机号":
                tvStatus.setText("修改成功");
                break;
            case "绑定银行卡":
                tvStatus.setText("绑定成功");
                break;
        }
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
        switch (from) {
            case "修改手机号":
                EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_PHONE));
                break;
            case "绑定银行卡":
                EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_BANK));
                break;
        }
        finish();
    }
}
