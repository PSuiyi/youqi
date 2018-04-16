package com.mzk.compass.youqi.ui.login;

import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.common.RemindAct;
import com.znz.compass.znzlibray.views.ZnzToolBar;

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
    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;

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


    @OnClick({R.id.tvRemind, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRemind:
                gotoActivity(RemindAct.class);
                break;
            case R.id.tvSubmit:
                gotoActivityWithClearStack(LoginAct.class);
                break;
        }
    }
}
