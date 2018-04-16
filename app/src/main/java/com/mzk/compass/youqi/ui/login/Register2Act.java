package com.mzk.compass.youqi.ui.login;

import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Date： 2018/4/16 2018
 * User： PSuiyi
 * Description：
 */
public class Register2Act extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_register2, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("");
        setNavLeftGone();
        znzToolBar.setNavRightText("登录");
        znzToolBar.setOnNavRightClickListener(v -> {
            gotoActivityWithClearStack(LoginAct.class);
        });
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        gotoActivityWithClearStack(LoginAct.class);
    }
}
