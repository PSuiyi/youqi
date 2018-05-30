package com.mzk.compass.youqi.ui.home.project;

import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.PeopleViewAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.PeopleBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/5/30 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleViewAct extends BaseAppListActivity {

    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout_withnav, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("谁看过");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new PeopleViewAdapter(dataList);
        rvRefresh.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        params.put("projectId", id);
        return mModel.requestPeopleViewList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSON.parseArray(responseJson.getString("data"), PeopleBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
