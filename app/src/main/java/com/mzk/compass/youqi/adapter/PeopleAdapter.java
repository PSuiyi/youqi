package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.ui.home.people.PeopleDetailAct;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class PeopleAdapter extends BaseQuickAdapter<PeopleBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.ivShare)
    ImageView ivShare;
    @Bind(R.id.ivFav)
    ImageView ivFav;
    @Bind(R.id.tvCountFav)
    TextView tvCountFav;
    @Bind(R.id.tvCountComment)
    TextView tvCountComment;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;
    @Bind(R.id.rvTrade)
    RecyclerView rvTrade;
    @Bind(R.id.tvTag1)
    TextView tvTag1;
    @Bind(R.id.tvTag2)
    TextView tvTag2;

    public PeopleAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_investor, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeopleBean bean) {
        setOnItemClickListener(this);
        ivImage.loadHeaderImage(bean.getAvatar());
        mDataManager.setValueToView(tvName, bean.getUsername());
        mDataManager.setValueToView(tvCountComment, bean.getCommentsNum());
        mDataManager.setValueToView(tvCountFav, bean.getCollectionNum());
        mDataManager.setValueToView(tvTag1, bean.getRealName());
        mDataManager.setValueToView(tvTag2, bean.getGroupName());

        if (bean.getTradeid() != null & bean.getTradeid().size() > 0) {
            mDataManager.setViewVisibility(rvTrade, true);
            TradeAdapter adapter = new TradeAdapter(bean.getTradeid());
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvTrade.setLayoutManager(layoutManager);
            rvTrade.setAdapter(adapter);
        } else {
            mDataManager.setViewVisibility(rvTrade, false);
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", bean.getId());
        gotoActivity(PeopleDetailAct.class, bundle);
    }
}
