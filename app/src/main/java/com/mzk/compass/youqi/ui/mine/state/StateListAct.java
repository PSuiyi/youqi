package com.mzk.compass.youqi.ui.mine.state;

import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.StateAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.StateBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class StateListAct extends BaseAppListActivity<StateBean> {
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
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        return mModel.requestStateList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        JSONObject json = JSON.parseObject(response);
        dataList.addAll(JSON.parseArray(json.getString("data"), StateBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
