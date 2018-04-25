package com.mzk.compass.youqi.ui.mine.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class VipCenterAct extends BaseAppActivity {
    @Bind(R.id.ivHeader)
    ImageView ivHeader;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_vip_center};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        tvTitle.setText("会员中心");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }


    @OnClick({R.id.llBack, R.id.tvRecharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llBack:
                finish();
                break;
            case R.id.tvRecharge:
                gotoActivity(RechargeAct.class);
                break;
        }
    }
}
