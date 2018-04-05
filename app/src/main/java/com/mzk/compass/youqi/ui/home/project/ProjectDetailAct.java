package com.mzk.compass.youqi.ui.home.project;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class ProjectDetailAct extends BaseAppActivity {
    @Bind(R.id.rvPeople)
    RecyclerView rvPeople;
    @Bind(R.id.llMore)
    LinearLayout llMore;
    @Bind(R.id.rvMenu)
    RecyclerView rvMenu;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_project_detail, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("项目详情");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.llMore)
    public void onViewClicked() {
    }
}
