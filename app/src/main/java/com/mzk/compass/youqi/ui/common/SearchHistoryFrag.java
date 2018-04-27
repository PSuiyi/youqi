package com.mzk.compass.youqi.ui.common;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.HistoryListAdapter;
import com.mzk.compass.youqi.base.BaseAppFragment;
import com.mzk.compass.youqi.bean.SearchHistoryBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.db.DbManagerSearch;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.bean.TagBean;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.ZnzTagView;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2017/5/23 2017
 * User： PSuiyi
 * Description：
 */

public class SearchHistoryFrag extends BaseAppFragment implements BaseQuickAdapter.OnItemClickListener {
    @Bind(R.id.rvHistory)
    RecyclerView rvHistory;
    @Bind(R.id.ivClear)
    ImageView ivClear;
    @Bind(R.id.llSearchHistory)
    LinearLayout llSearchHistory;

    List<TagBean> tagBeanList = new ArrayList<>();

    private HistoryListAdapter adapter;
    private List<SearchHistoryBean> dataList = new ArrayList<>();

    private DbManagerSearch dbManager;
    private SearchHistoryBean searchHistoryBean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_search_history};
    }

    @Override
    protected void initializeVariate() {

        searchHistoryBean = new SearchHistoryBean();
        dbManager = DbManagerSearch.getInstance(activity);
        dataList.clear();
        for (SearchHistoryBean bean : dbManager.queryListFromDB()) {
            if (bean.getType().equals(mDataManager.readTempData(Constants.SearchType.SEARCHTYPE))) {
                //判断是从哪进来的显示对应的历史记录
                dataList.add(bean);
            }
        }
    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected void initializeView() {
        adapter = new HistoryListAdapter(dataList);
        rvHistory.setLayoutManager(new LinearLayoutManager(activity));
        rvHistory.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        if (dataList.isEmpty()) {
            llSearchHistory.setVisibility(View.GONE);
        } else {
            llSearchHistory.setVisibility(View.VISIBLE);
        }

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ivDelete:
                        dbManager.deleteSingleToDB(dataList.get(position).getName());
                        dataList.clear();
                        for (SearchHistoryBean bean : dbManager.queryListFromDB()) {
                            if (bean.getType().equals(mDataManager.readTempData(Constants.SearchType.SEARCHTYPE))) {
                                //判断是从哪进来的显示对应的历史记录
                                dataList.add(bean);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        if (dataList.isEmpty()) {
                            llSearchHistory.setVisibility(View.GONE);
                        } else {
                            llSearchHistory.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        });

    }

    @Override
    protected void loadDataFromServer() {
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_SEARCH_VALUE, dataList.get(position).getName()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        EventManager.register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_SEARCH_HISTORY:
                dataList.clear();
                for (SearchHistoryBean bean : dbManager.queryListFromDB()) {
                    if (bean.getType().equals(mDataManager.readTempData(Constants.SearchType.SEARCHTYPE))) {
                        //判断是从哪进来的显示对应的历史记录
                        dataList.add(bean);
                    }
                }
                adapter.notifyDataSetChanged();
                if (dataList.isEmpty()) {
                    llSearchHistory.setVisibility(View.GONE);
                } else {
                    llSearchHistory.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @OnClick(R.id.ivClear)
    public void onViewClicked() {
        new UIAlertDialog(activity)
                .builder()
                .setMsg("是否确定清空搜索记录")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", v2 -> {
                    dbManager.clear();
                    dataList.clear();
                    adapter.notifyDataSetChanged();
                })
                .show();

    }
}
