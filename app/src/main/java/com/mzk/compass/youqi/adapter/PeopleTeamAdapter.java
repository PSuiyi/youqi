package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

public class PeopleTeamAdapter extends BaseQuickAdapter<PeopleBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {


    @Bind(R.id.ivUserHeader)
    HttpImageView ivUserHeader;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvContent)
    TextView tvContent;

    public PeopleTeamAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_people_team, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeopleBean bean) {
        setOnItemClickListener(this);
        ivUserHeader.loadHeaderImage(bean.getUserPhoto());
        mDataManager.setValueToView(tvName, bean.getUserName());
        mDataManager.setValueToView(tvContent, bean.getProfile());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", bean.getId());
        gotoActivity(PeopleDetailAct.class, bundle);
    }
}
