package com.mzk.compass.youqi.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseMultiItemQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MultiAdapter extends BaseMultiItemQuickAdapter<MultiBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    public MultiAdapter(List dataList) {
        super(dataList);
        addItemType(Constants.MultiType.Section, R.layout.item_lv_section);
        addItemType(Constants.MultiType.Project, R.layout.item_lv_project);
        addItemType(Constants.MultiType.People, R.layout.item_lv_recycle);
        addItemType(Constants.MultiType.Organ, R.layout.item_lv_home_organ);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBean bean) {
        setOnItemClickListener(this);
        switch (bean.getItemType()) {
            case Constants.MultiType.Section:
                helper.setText(R.id.tvTitle, bean.getSection());
                break;
            case Constants.MultiType.Project:
                break;
            case Constants.MultiType.People:
                PeopleGridHomeAdapter adapter = new PeopleGridHomeAdapter(bean.getPeopleList());
                RecyclerView rvPeople = helper.getView(R.id.rvRecycler);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rvPeople.setLayoutManager(layoutManager);
                rvPeople.setAdapter(adapter);
                break;
            case Constants.MultiType.Organ:
                OrganGridAdapter adapter2 = new OrganGridAdapter(bean.getOrganList());
                RecyclerView rvOrgan = helper.getView(R.id.rvRecycler);
                rvOrgan.setLayoutManager(new GridLayoutManager(mContext, 4));
                rvOrgan.setAdapter(adapter2);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (bean.getItemType()) {
            case Constants.MultiType.Section:
                break;
            case Constants.MultiType.Project:
                gotoActivity(ProjectDetailAct.class);
                break;
            case Constants.MultiType.People:
                break;
            case Constants.MultiType.Organ:
                break;
        }
    }

}
