package com.mzk.compass.youqi.adapter;

import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
import com.znz.compass.znzlibray.views.recyclerview.BaseMultiItemQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

public class MultiAdapter extends BaseMultiItemQuickAdapter<MultiBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    public MultiAdapter(List dataList) {
        super(dataList);
        addItemType(Constants.MultiType.Section, R.layout.item_lv_section);
        addItemType(Constants.MultiType.Project, R.layout.item_lv_project);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBean bean) {
        setOnItemClickListener(this);
        switch (bean.getItemType()) {

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

}
