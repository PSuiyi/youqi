package com.mzk.compass.youqi.ui.news;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.NewsAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.NewsBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/3/31 2018
 * User： PSuiyi
 * Description：
 */
public class NewsListFrag extends BaseAppListFragment<NewsBean> {

    private String id;
    private String from;
    private String keywords;

    public static NewsListFrag newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("id", id);
        NewsListFrag fragment = new NewsListFrag();
        fragment.setArguments(args);
        return fragment;
    }

    public static NewsListFrag newInstance(String from, String keywords) {
        Bundle args = new Bundle();
        args.putString("from", from);
        args.putString("keywords", keywords);
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
        if (getArguments() != null) {
            keywords = getArguments().getString("keywords");
        }
        if (getArguments() != null) {
            from = getArguments().getString("from");
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
        if (StringUtil.isBlank(from)) {
            params.put("cateId", id);
            return mModel.requestNewsList(params);
        } else {
            switch (from) {
                case "搜索":
                    params.put("cateId", "0");
                    params.put("searchKey", keywords);
                    return mModel.requestNewsList(params);
                case "收藏":
                    params.put("type", "5");
                    return mModel.requestCollect(params);
            }
        }
        return null;
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSONArray.parseArray(responseJson.getString("data"), NewsBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_SEARCH_NEWS:
                keywords = event.getValue();
                resetRefresh();
                break;
        }
    }
}
