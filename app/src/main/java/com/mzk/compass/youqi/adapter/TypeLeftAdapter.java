package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.CategoryBean;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class TypeLeftAdapter extends BaseQuickAdapter<CategoryBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivIcon)
    HttpImageView ivIcon;
    @Bind(R.id.tvTitle)
    TextView tvTitle;

    public TypeLeftAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_type_left, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean bean) {
        ivIcon.loadHttpImage(bean.getImage());
        mDataManager.setValueToView(tvTitle, bean.getName());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
