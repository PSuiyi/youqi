package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.CityBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class CityAdapter extends BaseQuickAdapter<CityBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvCity)
    TextView tvCity;

    public CityAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_city, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityBean bean) {
//        setOnItemClickListener(this);
        mDataManager.setValueToView(tvCity, bean.getName());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
