package com.mzk.compass.youqi.ui.mine.identify.company;

import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.IdentifyProcessAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;

/**
 * Created by Administrator on 2018/4/24.
 */

public class IdentifyProcessAct extends BaseAppListActivity {
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_identify_process, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("进度查询");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new IdentifyProcessAdapter(dataList);
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
