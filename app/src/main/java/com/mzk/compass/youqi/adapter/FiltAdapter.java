package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.FiltBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class FiltAdapter extends BaseQuickAdapter<FiltBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.ivSelect)
    ImageView ivSelect;

    public FiltAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_filt, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, FiltBean bean) {
        mDataManager.setValueToView(tvName, bean.getName());
        mDataManager.setViewVisibility(ivSelect, bean.isChecked());
        if (bean.isChecked()) {
            tvName.setTextColor(mDataManager.getColor(R.color.red));
        } else {
            tvName.setTextColor(mDataManager.getColor(R.color.text_color));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
