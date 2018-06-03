package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.PriceBean;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class RechargeAdapter extends BaseQuickAdapter<PriceBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvMonth)
    TextView tvMonth;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    LinearLayout llPrice;

    public RechargeAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_recharge, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, PriceBean bean) {
        llPrice = helper.getView(R.id.llPrice);
        if (bean.isSelect()) {
            llPrice.setBackgroundResource(R.drawable.bg_red_light_radius_2);
        } else {
            llPrice.setBackgroundResource(R.drawable.bg_border_gray);
        }
        tvMonth.setText(bean.getUnit() + "个月");
        tvPrice.setText("¥" + bean.getPrice());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
