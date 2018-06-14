package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.IndustryBean;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class TagsAdapter extends BaseQuickAdapter<IndustryBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.ivDelete)
    ImageView ivDelete;

    public TagsAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_tags, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndustryBean bean) {
//        setOnItemClickListener(this);
        if (bean.isDelete()) {
            mDataManager.setViewVisibility(ivDelete, true);
        } else {
            mDataManager.setViewVisibility(ivDelete, false);
        }
        helper.addOnClickListener(R.id.ivDelete);
        mDataManager.setValueToView(tvName, bean.getName());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
