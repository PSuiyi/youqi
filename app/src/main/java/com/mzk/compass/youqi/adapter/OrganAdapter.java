package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.ui.home.organ.OrganDetailAct;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

public class OrganAdapter extends BaseQuickAdapter<BaseZnzBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    public OrganAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_organ, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseZnzBean bean) {
        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(OrganDetailAct.class);
    }
}
