package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ViewPageAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.home.project.ProjectListFrag;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/3/31 2018
 * User： PSuiyi
 * Description：
 */
public class MineProjectTabAct extends BaseAppActivity {
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

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_tab_layout, 1};
    }

    @Override
    protected void initializeVariate() {
        tabNames.add("全部");
        tabNames.add("待审核");
        tabNames.add("已发布");
        tabNames.add("已拒绝");
        tabNames.add("已下架");

        fragmentList.add(ProjectListFrag.newInstance("全部"));
        fragmentList.add(ProjectListFrag.newInstance("待审核"));
        fragmentList.add(ProjectListFrag.newInstance("已发布"));
        fragmentList.add(ProjectListFrag.newInstance("已拒绝"));
        fragmentList.add(ProjectListFrag.newInstance("已下架"));
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("我的项目");
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
