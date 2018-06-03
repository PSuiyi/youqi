package com.mzk.compass.youqi.ui.home.people;

import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.StateAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.StateBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/6/3 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleStateAct extends BaseAppListActivity {

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
        setTitleName("投资人动态");
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
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        params.put("investorId", id);
        return mModel.requestPeopleStateList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSON.parseArray(responseJson.getString("data"), StateBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
