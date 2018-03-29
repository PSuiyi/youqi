package com.mzk.compass.youqi.base;

import com.mzk.compass.youqi.api.ApiModel;
import com.znz.compass.znzlibray.base.BaseFragment;

/**
 * Date： 2017/9/4 2017
 * User： PSuiyi
 * Description：
 */

public abstract class BaseAppFragment extends BaseFragment {
    protected ApiModel mModel;

    @Override
    protected void initializeAppBusiness() {
        mModel = new ApiModel(activity, this);
    }

}
