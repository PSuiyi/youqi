package com.mzk.compass.youqi.ui.login;

import android.content.Intent;
import android.view.WindowManager;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.TabHomeActivity;
import com.znz.compass.znzlibray.common.ZnzConstants;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Project_Name： builder
 * Date： 2017/1/9 2017
 * User： PSuiyi
 * Description：
 */

public class SplashAct extends BaseAppActivity {

    @Override
    protected int[] getLayoutResource() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return new int[]{R.layout.act_splash};
    }

    @Override
    protected void initializeVariate() {
        //解决home键重启问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
//                    if (!mDataManager.readBooleanTempData(ZnzConstants.IS_APP_OPEND)) {
//                        gotoActivity(WelcomeAct.class);
//                        mDataManager.saveBooleanTempData(ZnzConstants.IS_APP_OPEND, true);
//                    } else {
//                        gotoActivity(TabHomeActivity.class);
                    if (mDataManager.readBooleanTempData(ZnzConstants.IS_LOGIN)) {
                        gotoActivity(TabHomeActivity.class);
                    } else {
                        gotoActivity(LoginAct.class);
                    }
//                    }
//                    gotoActivity(LoginAct.class);
//                    finish();
                })
                .subscribe();
    }

    @Override
    protected void initializeNavigation() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
    }
}
