package com.mzk.compass.youqi.ui.news;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.NewsAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.NewsBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/3/31 2018
 * User： PSuiyi
 * Description：
 */
public class NewsListFrag extends BaseAppListFragment<NewsBean> {

    private String id;

    public static NewsListFrag newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("id", id);
        NewsListFrag fragment = new NewsListFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout};
    }

    @Override
    protected void initializeVariate() {
        if (getArguments() != null) {
            id = getArguments().getString("id");
        }
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
        adapter = new NewsAdapter(dataList);
        rvRefresh.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        params.put("cateId", id);
        return mModel.requestNewsList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSONArray.parseArray(responseJson.getString("data"), NewsBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
