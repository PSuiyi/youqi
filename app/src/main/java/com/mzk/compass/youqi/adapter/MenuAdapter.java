package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MenuBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class MenuAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvTitle)
    TextView tvTitle;

    public MenuAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_menu, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean bean) {
        setOnItemClickListener(this);
        mDataManager.setValueToView(tvTitle, bean.getTitle());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
