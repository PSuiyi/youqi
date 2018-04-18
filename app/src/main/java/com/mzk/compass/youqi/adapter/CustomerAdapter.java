package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

public class CustomerAdapter extends BaseQuickAdapter<BaseZnzBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    public CustomerAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_customer, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseZnzBean bean) {
        setOnItemClickListener(this);
        helper.addOnClickListener(R.id.tvPhone);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
