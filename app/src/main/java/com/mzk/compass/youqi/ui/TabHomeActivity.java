package com.mzk.compass.youqi.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.help.HelpFrag;
import com.mzk.compass.youqi.ui.home.HomeFrag;
import com.mzk.compass.youqi.ui.mine.MineFrag;
import com.mzk.compass.youqi.ui.news.NewsFrag;
import com.znz.compass.znzlibray.utils.FragmentUtil;

import butterknife.Bind;
import butterknife.OnClick;


public class TabHomeActivity extends BaseAppActivity {
    @Bind(R.id.main_container)
    LinearLayout mainContainer;
    @Bind(R.id.radioButton1)
    RadioButton radioButton1;
    @Bind(R.id.radioButton2)
    RadioButton radioButton2;
    @Bind(R.id.radioButton3)
    RadioButton radioButton3;
    @Bind(R.id.radioButton4)
    RadioButton radioButton4;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.llAdd)
    LinearLayout llAdd;
    private HomeFrag homeFragment;
    private HelpFrag meetingFragment;
    private NewsFrag groupFragment;
    private MineFrag mineFragment;
    private FragmentUtil fragmentUtil;
    private FragmentManager fragmentManager;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.activity_tab};
    }

    @Override
    protected void initializeVariate() {
    }

    @Override
    protected void initializeNavigation() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void initializeView() {
        fragmentUtil = new FragmentUtil();
        fragmentManager = getSupportFragmentManager();

        if (homeFragment == null) {
            homeFragment = new HomeFrag();
        }
        fragmentManager.beginTransaction().add(R.id.main_container, homeFragment).commit();
        fragmentUtil.mContent = homeFragment;
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick({R.id.radioButton1, R.id.radioButton2, R.id.llAdd, R.id.radioButton3, R.id.radioButton4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButton1:
                if (homeFragment == null) {
                    homeFragment = new HomeFrag();
                }
                fragmentUtil.switchContent(homeFragment, R.id.main_container, fragmentManager);
                break;
            case R.id.radioButton2:
                if (meetingFragment == null) {
                    meetingFragment = new HelpFrag();
                }
                fragmentUtil.switchContent(meetingFragment, R.id.main_container, fragmentManager);
                break;
            case R.id.llAdd:

                break;
            case R.id.radioButton3:
                if (groupFragment == null) {
                    groupFragment = new NewsFrag();
                }
                fragmentUtil.switchContent(groupFragment, R.id.main_container, fragmentManager);
                break;
            case R.id.radioButton4:
                if (mineFragment == null) {
                    mineFragment = new MineFrag();
                }
                fragmentUtil.switchContent(mineFragment, R.id.main_container, fragmentManager);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
