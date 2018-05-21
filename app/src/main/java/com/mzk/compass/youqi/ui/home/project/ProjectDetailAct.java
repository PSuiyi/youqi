package com.mzk.compass.youqi.ui.home.project;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CommentAdapter;
import com.mzk.compass.youqi.adapter.DetailAdapter;
import com.mzk.compass.youqi.adapter.MenuAdapter;
import com.mzk.compass.youqi.adapter.PeopleGridAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.home.people.PeopleListAct;
import com.mzk.compass.youqi.utils.PopupWindowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class ProjectDetailAct extends BaseAppListActivity {
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
    private RecyclerView rvPeople;
    private LinearLayout llMore;
    private RecyclerView rvMenu;
    private RecyclerView rvProject;

    private DetailAdapter detailAdapter;
    private String id;
    private List<PeopleBean> userList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_project_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("项目详情");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new CommentAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        View header = View.inflate(activity, R.layout.header_detail_project, null);
        adapter.addHeaderView(header);
        llMore = bindViewById(header, R.id.llMore);
        rvMenu = bindViewById(header, R.id.rvMenu);
        rvPeople = bindViewById(header, R.id.rvPeople);
        rvProject = bindViewById(header, R.id.rvProject);

        PeopleGridAdapter peopleGridAdapter = new PeopleGridAdapter(userList);
        rvPeople.setLayoutManager(new GridLayoutManager(activity, 6));
        rvPeople.setAdapter(peopleGridAdapter);

        llMore.setOnClickListener(v -> {
            gotoActivity(PeopleListAct.class);
        });


        //菜单栏
        List<MenuBean> menuBeanList = new ArrayList<>();
        menuBeanList.add(new MenuBean("项目简介"));
        menuBeanList.add(new MenuBean("团队介绍"));
        menuBeanList.add(new MenuBean("产品形态"));
        menuBeanList.add(new MenuBean("市场分析"));
        menuBeanList.add(new MenuBean("解决方案"));
        menuBeanList.add(new MenuBean("盈利模式"));
        menuBeanList.add(new MenuBean("项目资料"));
        menuBeanList.add(new MenuBean("项目评论"));
        MenuAdapter adapter = new MenuAdapter(menuBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapter);


        //项目详情
        rvProject.setLayoutManager(new LinearLayoutManager(activity));
        List<MultiBean> multiBeanList = new ArrayList<>();
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectIntro));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectTeam));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectProduct));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectMarket));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectSolution));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectMoney));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectFinancing));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectData));
        detailAdapter = new DetailAdapter(multiBeanList);
        rvProject.setAdapter(detailAdapter);
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
                PopupWindowManager.getInstance(activity).showChatProject(view, new PopupWindowManager.OnPopupWindowClickListener() {
                    @Override
                    public void onPopupWindowClick(String type, String[] values) {

                    }
                });
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
