package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class ProjectAdapter extends BaseQuickAdapter<ProjectBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvTag)
    TextView tvTag;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.tvCollection)
    TextView tvCollection;
    @Bind(R.id.tvComment)
    TextView tvComment;
    @Bind(R.id.tvVisite)
    TextView tvVisite;
    @Bind(R.id.rvTrade)
    RecyclerView rvTrade;
    @Bind(R.id.ivLogo)
    HttpImageView ivLogo;
    @Bind(R.id.tvCompanyName)
    TextView tvCompanyName;
    @Bind(R.id.ivShare)
    ImageView ivShare;
    @Bind(R.id.ivFav)
    ImageView ivFav;

    public ProjectAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_project, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectBean bean) {
        setOnItemClickListener(this);
        ivImage.loadSquareImage(bean.getLogo());
        ivLogo.loadSquareImage(bean.getCompanyLogo());
        mDataManager.setValueToView(tvName, bean.getName());
        if (!StringUtil.isBlank(bean.getRname())) {
            mDataManager.setValueToView(tvTag, bean.getRname());
            mDataManager.setViewVisibility(tvTag, true);
        } else {
            if (!StringUtil.isBlank(bean.getRoundsid())) {
                mDataManager.setValueToView(tvTag, bean.getRoundsid());
                mDataManager.setViewVisibility(tvTag, true);
            } else {
                mDataManager.setViewVisibility(tvTag, false);
            }
        }
        mDataManager.setValueToView(tvContent, bean.getTitle());
        mDataManager.setValueToView(tvCollection, bean.getCollectionNum(), "0");
        mDataManager.setValueToView(tvComment, bean.getCommentsNum(), "0");
        mDataManager.setValueToView(tvVisite, bean.getVisiteNum());
        mDataManager.setValueToView(tvCompanyName, bean.getCompanyName());
        if (!StringUtil.isBlank(bean.getTname())) {
            mDataManager.setValueToView(tvPrice, bean.getTname());
        } else {
            mDataManager.setValueToView(tvPrice, bean.getTurnoverid());
        }

        if (!StringUtil.isBlank(bean.getIsCollected())) {
            if (bean.getIsCollected().equals("true")) {
                ivFav.setImageResource(R.mipmap.shoucanghuang);
            } else {
                ivFav.setImageResource(R.mipmap.shoucang);
            }
        } else {
            ivFav.setImageResource(R.mipmap.shoucang);
        }

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


        helper.addOnClickListener(R.id.ivShare);
        helper.addOnClickListener(R.id.ivFav);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        if (!StringUtil.isBlank(bean.getId())) {
            bundle.putString("id", bean.getId());
        } else {
            bundle.putString("id", bean.getLink());
        }
        gotoActivity(ProjectDetailAct.class, bundle);
    }
}
