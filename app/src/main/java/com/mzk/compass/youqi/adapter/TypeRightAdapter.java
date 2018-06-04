package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.CategoryBean;
import com.mzk.compass.youqi.ui.home.product.ProductListAct;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class TypeRightAdapter extends BaseQuickAdapter<CategoryBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvTitle)
    TextView tvTitle;

    public TypeRightAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_type_right, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean bean) {
        setOnItemClickListener(this);
        mDataManager.setValueToView(tvTitle, bean.getName());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("cateId", bean.getId());
        gotoActivity(ProductListAct.class, bundle);
    }
}
