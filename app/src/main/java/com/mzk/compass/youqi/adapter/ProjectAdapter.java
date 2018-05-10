package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
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
    @Bind(R.id.tvRname)
    TextView tvRname;
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

    public ProjectAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_project, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectBean bean) {
        setOnItemClickListener(this);
        ivImage.loadHttpImage(bean.getLogo());
        mDataManager.setValueToView(tvName, bean.getName());
        mDataManager.setValueToView(tvTag, bean.getRname());
        mDataManager.setValueToView(tvContent, bean.getTitle());
        mDataManager.setValueToView(tvCollection, bean.getCollectionNum());
        mDataManager.setValueToView(tvComment, bean.getCommentsNum());
        mDataManager.setValueToView(tvVisite, bean.getVisiteNum());
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
        gotoActivity(ProjectDetailAct.class);
    }
}
