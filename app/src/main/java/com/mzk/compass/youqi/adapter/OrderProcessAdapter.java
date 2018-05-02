package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class OrderProcessAdapter extends BaseQuickAdapter<BaseZnzBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.view1)
    View view1;
    @Bind(R.id.view2)
    View view2;
    @Bind(R.id.tvProcess)
    TextView tvProcess;

    public OrderProcessAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_order_process, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseZnzBean bean) {
        setOnItemClickListener(this);
        if (helper.getPosition() == 1) {
            view1.setVisibility(View.INVISIBLE);
        } else {
            view1.setVisibility(View.VISIBLE);
        }
        if (helper.getPosition() == mDataList.size()) {
            view2.setVisibility(View.INVISIBLE);
        } else {
            view2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
