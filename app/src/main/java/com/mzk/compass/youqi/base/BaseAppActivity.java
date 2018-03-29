package com.mzk.compass.youqi.base;

import android.os.Bundle;

import com.mzk.compass.youqi.api.ApiModel;
import com.znz.compass.znzlibray.base.BaseActivity;

/**
 * Date： 2017/9/4 2017
 * User： PSuiyi
 * Description：
 */

public abstract class BaseAppActivity extends BaseActivity {

    protected ApiModel mModel;

    @Override
    protected void initializeAppBusiness() {
        mModel = new ApiModel(activity, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mModel != null) {
            mModel.MODestory();
        }
    }
}
