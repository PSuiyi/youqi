package com.mzk.compass.youqi.ui.mine.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ViewPageAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class OrderTabAct extends BaseAppActivity {
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
        tabNames.add("待付款");
        tabNames.add("待服务");
        tabNames.add("待确认服务结果");
        tabNames.add("已取消 ");

        fragmentList.add(new OrderListFrag());
        fragmentList.add(new OrderListFrag());
        fragmentList.add(new OrderListFrag());
        fragmentList.add(new OrderListFrag());
        fragmentList.add(new OrderListFrag());
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("我的订单");
    }

    @Override
    protected void initializeView() {
        commonViewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), tabNames, fragmentList));
        commonTabLayout.setupWithViewPager(commonViewPager);
        commonTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
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
}
