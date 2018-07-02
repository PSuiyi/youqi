package com.mzk.compass.youqi.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.recyclerview.BaseMultiItemQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

public class DetailAdapter extends BaseMultiItemQuickAdapter<MultiBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    public DetailAdapter(List dataList) {
        super(dataList);
        addItemType(Constants.MultiType.ProjectIntro, R.layout.item_lv_project_intro);
        addItemType(Constants.MultiType.ProjectTeam, R.layout.item_lv_project_team);
        addItemType(Constants.MultiType.ProjectProduct, R.layout.item_lv_project_product);
        addItemType(Constants.MultiType.ProjectMarket, R.layout.item_lv_project_market);
        addItemType(Constants.MultiType.ProjectSolution, R.layout.item_lv_project_solution);
        addItemType(Constants.MultiType.ProjectMoney, R.layout.item_lv_project_money);
        addItemType(Constants.MultiType.ProjectFinancing, R.layout.item_lv_project_financing);
        addItemType(Constants.MultiType.ProjectData, R.layout.item_lv_project_data);
        addItemType(Constants.MultiType.ProjectComment, R.layout.item_lv_comment);

        addItemType(Constants.MultiType.PeopleState, R.layout.item_lv_people_state);
        addItemType(Constants.MultiType.PeopleIntro, R.layout.item_lv_people_intro);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBean bean) {
        setOnItemClickListener(this);
        switch (bean.getItemType()) {
            case Constants.MultiType.ProjectIntro:
                helper.setText(R.id.tvIntro, bean.getProjectBean().getProjectProfile());
                break;
            case Constants.MultiType.ProjectTeam:
                PeopleTeamAdapter teamAdapter = new PeopleTeamAdapter(bean.getProjectBean().getTeam());
                RecyclerView rvTeam = helper.getView(R.id.rvRecycler);
                LinearLayoutManager teamLayoutManager = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rvTeam.setLayoutManager(teamLayoutManager);
                rvTeam.setAdapter(teamAdapter);
                break;
            case Constants.MultiType.ProjectProduct:
                WebViewWithProgress wvProduct = helper.getView(R.id.wvProduct);
                wvProduct.loadContent(bean.getProjectBean().getProductshape());
                break;
            case Constants.MultiType.ProjectMarket:
                WebViewWithProgress wvMarket = helper.getView(R.id.wvMarket);
                wvMarket.loadContent(bean.getProjectBean().getMarket());
                break;
            case Constants.MultiType.ProjectSolution:
                WebViewWithProgress wvSolution = helper.getView(R.id.wvSolution);
                wvSolution.loadContent(bean.getProjectBean().getSolusion());
                break;
            case Constants.MultiType.ProjectMoney:
                WebViewWithProgress wvMoney = helper.getView(R.id.wvMoney);
                wvMoney.loadContent(bean.getProjectBean().getProfitModel());
                break;
            case Constants.MultiType.ProjectFinancing:
                WebViewWithProgress wvFinan = helper.getView(R.id.wvFinan);
                wvFinan.loadContent(bean.getProjectBean().getFinancing());
                break;
            case Constants.MultiType.ProjectData:
                break;
            case Constants.MultiType.PeopleState:
                helper.addOnClickListener(R.id.llMore);
                StateAdapter stateAdapter = new StateAdapter(bean.getStateBeanList());
                RecyclerView rvState = helper.getView(R.id.rvRecycler);
                LinearLayoutManager proLayoutManager = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rvState.setLayoutManager(proLayoutManager);
                rvState.setAdapter(stateAdapter);
                break;
            case Constants.MultiType.PeopleIntro:
                WebViewWithProgress wvIntro = helper.getView(R.id.wvIntro);
                wvIntro.loadContent(bean.getPeopleBean().getExample());
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (bean.getItemType()) {
            case Constants.MultiType.Section:
                break;
            case Constants.MultiType.Project:
                break;
        }
    }

}
