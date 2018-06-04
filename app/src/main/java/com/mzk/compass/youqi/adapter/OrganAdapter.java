package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.OrganBean;
import com.mzk.compass.youqi.ui.home.organ.OrganDetailAct;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class OrganAdapter extends BaseQuickAdapter<OrganBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvCountFav)
    TextView tvCountFav;
    @Bind(R.id.tvCountView)
    TextView tvCountView;
    @Bind(R.id.ivShare)
    ImageView ivShare;
    @Bind(R.id.ivFav)
    ImageView ivFav;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;

    public OrganAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_organ, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrganBean bean) {
        setOnItemClickListener(this);
        ivImage.loadSquareImage(bean.getLogo());
        mDataManager.setValueToView(tvTitle, bean.getCname());
        mDataManager.setValueToView(tvContent, bean.getSummary());
        mDataManager.setValueToView(tvCountFav, bean.getCollectionNum());
        mDataManager.setValueToView(tvCountView, bean.getVisiteNum());
        if (bean.getIsCollected().equals("true")) {
            ivFav.setImageResource(R.mipmap.shoucanghuang);
        } else {
            ivFav.setImageResource(R.mipmap.shoucang);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", bean.getId());
        gotoActivity(OrganDetailAct.class, bundle);
    }
}
