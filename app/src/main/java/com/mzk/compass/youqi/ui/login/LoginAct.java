package com.mzk.compass.youqi.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.TabHomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/16 2018
 * User： PSuiyi
 * Description：
 */
public class LoginAct extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.tvFoget)
    TextView tvFoget;
    @Bind(R.id.tvLoginCode)
    TextView tvLoginCode;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_login, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setSwipeBackEnable(false);
        setTitleName("");
        setNavLeftGone();
        znzToolBar.setNavRightText("注册");
        znzToolBar.setOnNavRightClickListener(v -> {
            gotoActivity(RegisterAct.class);
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

    @OnClick({R.id.tvSubmit, R.id.tvFoget, R.id.tvLoginCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSubmit:
                gotoActivity(TabHomeActivity.class);
                break;
            case R.id.tvFoget:
                break;
            case R.id.tvLoginCode:
                gotoActivity(LoginCodeAct.class);
                break;
        }
    }
}
