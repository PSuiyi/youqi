package com.mzk.compass.youqi.ui.mine.article;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ArticleAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.ArticleBean;
import com.mzk.compass.youqi.bean.MessageBean;
import com.mzk.compass.youqi.ui.mine.message.MessageListFrag;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class ArticleListFrag extends BaseAppListFragment<ArticleBean> {
    private String from;

    public static ArticleListFrag newInstance(String from) {
        Bundle args = new Bundle();
        args.putString("from", from);
        ArticleListFrag fragment = new ArticleListFrag();
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
        adapter = new ArticleAdapter(dataList);
        rvRefresh.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        switch (from) {
            case "全部":
                params.put("state", "0");
                break;
            case "已采纳":
                params.put("state", "1");
                break;
            case "审核中":
                params.put("state", "2");
                break;
            case "拒绝":
                params.put("state", "3");
                break;
        }
        return mModel.requestArticleList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        JSONObject json = JSON.parseObject(response);
        dataList.addAll(JSON.parseArray(json.getString("data"), ArticleBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
