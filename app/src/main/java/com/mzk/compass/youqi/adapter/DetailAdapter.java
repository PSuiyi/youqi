package com.mzk.compass.youqi.adapter;

import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.mine.state.StateListAct;
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

        addItemType(Constants.MultiType.PeopleState, R.layout.item_lv_people_state);
        addItemType(Constants.MultiType.PeopleIntro, R.layout.item_lv_people_intro);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBean bean) {
        setOnItemClickListener(this);
        switch (bean.getItemType()) {
            case Constants.MultiType.ProjectIntro:
                break;
            case Constants.MultiType.ProjectTeam:
                break;
            case Constants.MultiType.ProjectProduct:
                break;
            case Constants.MultiType.ProjectMarket:
                break;
            case Constants.MultiType.ProjectSolution:
                break;
            case Constants.MultiType.ProjectMoney:
                break;
            case Constants.MultiType.ProjectFinancing:
                break;
            case Constants.MultiType.ProjectData:
                break;
            case Constants.MultiType.PeopleState:
                helper.getView(R.id.llMore).setOnClickListener(v -> {
                    gotoActivity(StateListAct.class);
                });
                break;
            case Constants.MultiType.PeopleIntro:
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
