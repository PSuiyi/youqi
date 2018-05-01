package com.mzk.compass.youqi.ui.home.project;

import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProjectAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class ProjectListFrag extends BaseAppListFragment {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new ProjectAdapter(dataList);
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
