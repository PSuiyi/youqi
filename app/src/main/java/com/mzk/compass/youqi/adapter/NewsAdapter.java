package com.mzk.compass.youqi.adapter;

import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.NewsBean;
import com.mzk.compass.youqi.ui.news.NewsDetailAct;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.ArrayList;

import butterknife.Bind;


public class NewsAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvTag)
    TextView tvTag;
    @Bind(R.id.tvTime)
    TextView tvTime;

    public NewsAdapter(ArrayList dataList) {
        super(R.layout.item_lv_news, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean bean) {
        setOnItemClickListener(this);
        ivImage.loadRectImage(bean.getImage());
        mDataManager.setValueToView(tvTitle, bean.getTitle());
        mDataManager.setValueToView(tvContent, bean.getSummary());
        mDataManager.setValueToView(tvTag, bean.getName());
        mDataManager.setValueToView(tvTime, TimeUtils.getFriendlyTimeSpanByNow(StringUtil.stringToLong(bean.getAddTime())));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(NewsDetailAct.class);
    }
}
