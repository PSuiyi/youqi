package com.mzk.compass.youqi.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CommentAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class NewsDetailAct extends BaseAppListActivity {

    private View header;
    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_news_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("详情");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new CommentAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        header = View.inflate(activity, R.layout.header_news_detail, null);
        adapter.addHeaderView(header);
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
