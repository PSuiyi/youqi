package com.mzk.compass.youqi.ui.common;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.PeopleAdapter;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.adapter.ProjectAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2017/5/23 2017
 * User： PSuiyi
 * Description：
 */

public class SearchResultListFrag extends BaseAppListFragment<BaseZnzBean> {

    private String searchContent;
    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout, 1};
    }

    public static SearchResultListFrag newInstance(String searchContent, String from) {
        Bundle bundle = new Bundle();
        bundle.putString("searchContent", searchContent);
        bundle.putString("from", from);
        SearchResultListFrag listFragment = new SearchResultListFrag();
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    protected void initializeVariate() {
        if (getArguments() != null) {
            searchContent = getArguments().getString("searchContent");
            from = getArguments().getString("from");
        }
    }

    @Override
    protected void initializeNavigation() {
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(activity);
    }


    @Override
    protected void initializeView() {
        switch (from) {
            case "搜索项目":
                rvRefresh.setLayoutManager(new LinearLayoutManager(activity));
                adapter = new ProjectAdapter(dataList);
                break;
            case "找服务":
                rvRefresh.setLayoutManager(new GridLayoutManager(activity, 2));
                adapter = new ProductAdapter(dataList);
                break;
            case "搜索投资人":
                rvRefresh.setLayoutManager(new LinearLayoutManager(activity));
                adapter = new PeopleAdapter(dataList);
                break;
            case "搜索投资机构":
                rvRefresh.setLayoutManager(new LinearLayoutManager(activity));
                adapter = new ProjectAdapter(dataList);
                break;
            case "找商品":
                rvRefresh.setLayoutManager(new GridLayoutManager(activity, 2));
                adapter = new ProductAdapter(dataList);
                break;
        }

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

    public void setSearchData(String keyWords, String searchType) {
        searchContent = keyWords;
    }

}
