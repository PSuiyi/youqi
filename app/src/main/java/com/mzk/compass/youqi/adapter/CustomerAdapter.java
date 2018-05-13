package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.CustomerServiceBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class CustomerAdapter extends BaseQuickAdapter<CustomerServiceBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvTel)
    TextView tvTel;

    public CustomerAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_customer, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerServiceBean bean) {
        setOnItemClickListener(this);
        helper.addOnClickListener(R.id.tvPhone);
        mDataManager.setValueToView(tvName, bean.getUname());
        mDataManager.setValueToView(tvTel, bean.getTel());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
