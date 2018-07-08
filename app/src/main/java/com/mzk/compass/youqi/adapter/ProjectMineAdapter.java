package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class ProjectMineAdapter extends BaseQuickAdapter<ProjectBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

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
    @Bind(R.id.tvRongzi)
    TextView tvRongzi;
    @Bind(R.id.tvPublish)
    TextView tvPublish;
    @Bind(R.id.tvShenhe)
    TextView tvShenhe;

    public ProjectMineAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_project_mine, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectBean bean) {
        setOnItemClickListener(this);
        ivImage.loadSquareImage(bean.getLogo());
        ivLogo.loadSquareImage(bean.getCompanyLogo());
        mDataManager.setValueToView(tvName, bean.getName());
        if (!StringUtil.isBlank(bean.getRname())) {
            mDataManager.setValueToView(tvTag, bean.getRname());
        } else {
            mDataManager.setValueToView(tvTag, bean.getRoundsid());
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
        helper.addOnClickListener(R.id.tvShenhe);
        if (!StringUtil.isBlank(bean.getState())) {
            tvPublish.setVisibility(View.VISIBLE);
            tvShenhe.setVisibility(View.VISIBLE);
            switch (bean.getState()) {
                case "1":
                    tvPublish.setText("待审核");
                    tvPublish.setTextColor(mDataManager.getColor(R.color.red));
                    tvPublish.setBackgroundResource(R.drawable.bg_line_red_2);
                    tvShenhe.setVisibility(View.GONE);
                    break;
                case "2":
                    tvPublish.setText("已发布");
                    tvPublish.setTextColor(mDataManager.getColor(R.color.text_gray));
                    tvPublish.setBackgroundResource(R.drawable.bg_line_gray);
                    tvShenhe.setText("下架");
                    tvShenhe.setTextColor(mDataManager.getColor(R.color.red));
                    tvShenhe.setBackgroundResource(R.drawable.bg_line_red_2);
                    break;
                case "3":
                    tvPublish.setText("已下架");
                    tvPublish.setTextColor(mDataManager.getColor(R.color.text_gray));
                    tvPublish.setBackgroundResource(R.drawable.bg_line_gray);
                    tvShenhe.setText("重新上架");
                    tvShenhe.setTextColor(mDataManager.getColor(R.color.red));
                    tvShenhe.setBackgroundResource(R.drawable.bg_line_red_2);
                    break;
                case "4":
                    tvPublish.setText("已拒绝");
                    tvPublish.setTextColor(mDataManager.getColor(R.color.text_gray));
                    tvPublish.setBackgroundResource(R.drawable.bg_line_gray);
                    tvShenhe.setText("删除");
                    tvShenhe.setTextColor(mDataManager.getColor(R.color.red));
                    tvShenhe.setBackgroundResource(R.drawable.bg_line_red_2);
                    break;
                default:
                    tvPublish.setVisibility(View.GONE);
                    tvShenhe.setVisibility(View.GONE);
                    break;
            }
        } else {
            tvPublish.setVisibility(View.GONE);
            tvShenhe.setVisibility(View.GONE);
        }
        if (!StringUtil.isBlank(bean.getRongzistate())) {//融资状态 1未融资 2 已融资
            switch (bean.getRongzistate()) {
                case "1":
                    tvRongzi.setVisibility(View.VISIBLE);
                    tvRongzi.setText("未融资");
                    tvRongzi.setTextColor(mDataManager.getColor(R.color.text_gray));
                    tvRongzi.setBackgroundResource(R.drawable.bg_line_gray);
                    break;
                case "2":
                    tvRongzi.setVisibility(View.VISIBLE);
                    tvRongzi.setText("已融资");
                    tvRongzi.setTextColor(mDataManager.getColor(R.color.red));
                    tvRongzi.setBackgroundResource(R.drawable.bg_line_red_2);
                    break;
                default:
                    tvRongzi.setVisibility(View.GONE);
                    break;
            }
        } else {
            tvRongzi.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.tvRongzi);
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
