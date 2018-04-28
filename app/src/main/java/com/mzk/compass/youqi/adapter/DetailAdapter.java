package com.mzk.compass.youqi.adapter;

import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
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
        addItemType(Constants.MultiType.Organ, R.layout.item_lv_home_organ);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBean bean) {
        setOnItemClickListener(this);
        switch (bean.getItemType()) {
            case Constants.MultiType.ProjectIntro:
                break;
            case Constants.MultiType.ProjectTeam:
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
