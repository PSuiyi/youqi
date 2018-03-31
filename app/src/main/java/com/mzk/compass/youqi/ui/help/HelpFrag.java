package com.mzk.compass.youqi.ui.help;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;

/**
 * Date： 2018/3/29 2018
 * User： PSuiyi
 * Description：
 */
public class HelpFrag extends BaseAppListFragment {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout_withnav, 2};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(activity, 2);
    }

    @Override
    protected void initializeView() {
        adapter = new ProductAdapter(dataList);
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
