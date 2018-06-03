package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.TagYouBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class TradeAdapter extends BaseQuickAdapter<TagYouBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvCategory)
    TextView tvCategory;

    public TradeAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_trade, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagYouBean bean) {
        mDataManager.setValueToView(tvCategory, bean.getName());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
