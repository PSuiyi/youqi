package com.mzk.compass.youqi.ui.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.znz.compass.znzlibray.base.BaseListActivity;

/**
 * Date： 2018/3/31 2018
 * User： PSuiyi
 * Description：
 */
public class ProductListAct extends BaseListActivity {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_lv_service, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected void initializeAppBusiness() {

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

    @Override
    protected void customeRefreshRequest(int actionPullToRefresh) {

    }
}
