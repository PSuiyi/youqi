package com.mzk.compass.youqi.ui.mine.state;

import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.StateAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;

/**
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class StateListAct extends BaseAppListActivity {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout_withnav, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("我的动态");
        znzToolBar.setNavRightText("编辑");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new StateAdapter(dataList);
        rvRefresh.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected void onRefreshSuccess(String response) {

    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
