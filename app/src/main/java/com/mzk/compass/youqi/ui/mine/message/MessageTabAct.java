package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ViewPageAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class MessageTabAct extends BaseAppActivity {
    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;
    @Bind(R.id.znzRemind)
    ZnzRemind znzRemind;
    @Bind(R.id.llNetworkStatus)
    LinearLayout llNetworkStatus;
    @Bind(R.id.commonTabLayout)
    TabLayout commonTabLayout;
    @Bind(R.id.commonViewPager)
    ViewPager commonViewPager;

    private List<String> tabNames = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private boolean isEdit = false;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_tab_layout, 1};
    }

    @Override
    protected void initializeVariate() {
        tabNames.add("互动消息");
        tabNames.add("交易信息");
        tabNames.add("系统信息");

        fragmentList.add(MessageListFrag.newInstance("互动消息"));
        fragmentList.add(MessageListFrag.newInstance("交易信息"));
        fragmentList.add(MessageListFrag.newInstance("系统信息"));
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("消息");
        znzToolBar.setNavRightText("编辑");
        znzToolBar.setOnNavRightClickListener(view -> {
            if (isEdit) {
                isEdit = false;
                znzToolBar.setNavRightText("编辑");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_MESSAGE_EDIT, "完成"));
            } else {
                isEdit = true;
                znzToolBar.setNavRightText("完成");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_MESSAGE_EDIT, "编辑"));
            }
        });
    }

    @Override
    protected void initializeView() {
        commonTabLayout.setTabMode(TabLayout.MODE_FIXED);
        commonViewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), tabNames, fragmentList));
        commonTabLayout.setupWithViewPager(commonViewPager);
        commonViewPager.setOffscreenPageLimit(fragmentList.size());

        commonTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
