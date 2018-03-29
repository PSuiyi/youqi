package com.mzk.compass.youqi.ui.setting;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/26.
 */

public class AboutUsAct extends BaseAppActivity {


    @Bind(R.id.tvVer)
    TextView tvVer;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_aboutus, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("关于我们");
    }

    @Override
    protected void initializeView() {
    }

    @Override
    protected void loadDataFromServer() {
        mDataManager.setValueToView(tvVer, "美客美租" + StringUtil.getVersionName(context) + "v");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
