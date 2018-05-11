package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.ArticleBean;
import com.mzk.compass.youqi.ui.mine.article.ArticleDetailAct;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class ArticleAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvState)
    TextView tvState;

    public ArticleAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_article, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean bean) {
        setOnItemClickListener(this);
        mDataManager.setValueToView(tvTitle, bean.getTitle());
        mDataManager.setValueToView(tvTime, TimeUtils.getFormatTime(bean.getAddTime(), "yyyy-MM-dd HH:mm"));
        if (StringUtil.isBlank(bean.getState())) {
            mDataManager.setViewVisibility(tvState, false);
        } else {
            mDataManager.setViewVisibility(tvState, true);
            switch (bean.getState()) {
                case "1":
                    mDataManager.setValueToView(tvState, "已采纳");
                    tvState.setTextColor(mDataManager.getColor(R.color.green));
                    break;
                case "2":
                    mDataManager.setValueToView(tvState, "审核中");
                    tvState.setTextColor(mDataManager.getColor(R.color.red));
                    break;
                case "3":
                    mDataManager.setValueToView(tvState, "已拒绝");
                    tvState.setTextColor(mDataManager.getColor(R.color.text_gray));
                    break;
                default:
                    mDataManager.setValueToView(tvState, "已拒绝");
                    tvState.setTextColor(mDataManager.getColor(R.color.text_gray));
                    break;
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(ArticleDetailAct.class);
    }
}
