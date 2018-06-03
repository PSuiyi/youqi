package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.ui.home.people.PeopleDetailAct;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class PeopleViewAdapter extends BaseQuickAdapter<PeopleBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivUserHeader)
    HttpImageView ivUserHeader;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvOrganName)
    TextView tvOrganName;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;

    public PeopleViewAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_people_view, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeopleBean bean) {
        setOnItemClickListener(this);
        ivUserHeader.loadHeaderImage(bean.getAvatar());
        mDataManager.setValueToView(tvName, bean.getUsername());
        mDataManager.setValueToView(tvTime, TimeUtils.getFriendlyTimeSpanByNow(bean.getAddTime()));
        mDataManager.setValueToView(tvOrganName, bean.getGroupName());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", bean.getId());
        gotoActivity(PeopleDetailAct.class, bundle);
    }
}
