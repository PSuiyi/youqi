package com.mzk.compass.youqi.ui.mine.state;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class StateDetailAct extends BaseAppActivity {
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvTime)
    TextView tvTime;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_state_detail, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("我的动态");
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
}
