package com.mzk.compass.youqi.ui.home.people;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CommentAdapter;
import com.mzk.compass.youqi.adapter.DetailAdapter;
import com.mzk.compass.youqi.adapter.MenuAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleDetailAct extends BaseAppListActivity {
    @Bind(R.id.tvOption1)
    TextView tvOption1;
    @Bind(R.id.tvOption2)
    TextView tvOption2;
    @Bind(R.id.tvOption3)
    TextView tvOption3;
    @Bind(R.id.tvOption4)
    TextView tvOption4;
    @Bind(R.id.tvOption5)
    TextView tvOption5;
    private RecyclerView rvDetail;
    private RecyclerView rvMenu;
    private TextView tvRecommend;

    private DetailAdapter detailAdapter;
    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_people_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("投资人主页");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new CommentAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        View header = View.inflate(activity, R.layout.header_detail_people, null);
        adapter.addHeaderView(header);
        rvMenu = bindViewById(header, R.id.rvMenu);
        rvDetail = bindViewById(header, R.id.rvDetail);

        tvRecommend = bindViewById(header, R.id.tvRecommend);
        tvRecommend.setOnClickListener(v -> {
            gotoActivity(RecommendSelfAct.class);
        });

        //菜单栏
        List<MenuBean> menuBeanList = new ArrayList<>();
        menuBeanList.add(new MenuBean("投资人动态"));
        menuBeanList.add(new MenuBean("简介"));
        menuBeanList.add(new MenuBean("评论"));
        MenuAdapter adapter = new MenuAdapter(menuBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapter);


        //项目详情
        rvDetail.setLayoutManager(new LinearLayoutManager(activity));
        List<MultiBean> multiBeanList = new ArrayList<>();
        multiBeanList.add(new MultiBean(Constants.MultiType.PeopleState));
        multiBeanList.add(new MultiBean(Constants.MultiType.PeopleIntro));
        detailAdapter = new DetailAdapter(multiBeanList);
        rvDetail.setAdapter(detailAdapter);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("investorId", id);
        mModel.requestPeopleDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    @Override
    protected void onRefreshSuccess(String response) {

    }

    @Override
    protected void onRefreshFail(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvOption1, R.id.tvOption2, R.id.tvOption3, R.id.tvOption4, R.id.tvOption5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvOption1:
                break;
            case R.id.tvOption2:
                gotoActivity(RecommendSelfAct.class);
                break;
            case R.id.tvOption3:
                break;
            case R.id.tvOption4:
                break;
            case R.id.tvOption5:
                rvRefresh.smoothScrollToPosition(0);
                break;
        }
    }
}
