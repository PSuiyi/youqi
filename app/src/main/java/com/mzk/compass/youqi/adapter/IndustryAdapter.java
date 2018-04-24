package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.IndustryBean;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class IndustryAdapter extends BaseQuickAdapter<IndustryBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    RadioButton rbIndustry;

    public IndustryAdapter(@Nullable List dataList) {
        super(R.layout.item_gv_industry, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndustryBean bean) {
        setOnItemClickListener(this);
        rbIndustry = helper.getView(R.id.rbIndustry);
        rbIndustry.setClickable(false);
        rbIndustry.setFocusable(false);
        mDataManager.setValueToView(rbIndustry, bean.getName());
        rbIndustry.setChecked(bean.isSelect());
        if (bean.isSelect()) {
            rbIndustry.setTextColor(mDataManager.getColor(R.color.text_blue_industry));
        } else {
            rbIndustry.setTextColor(mDataManager.getColor(R.color.text_gray));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        IndustryBean bean = mDataList.get(position);
        bean.setSelect(!bean.isSelect());
        notifyDataSetChanged();
    }
}
