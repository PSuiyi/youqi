package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.CommentBean;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class CommentAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.rvReply)
    RecyclerView rvReply;
    @Bind(R.id.ivUserHeader)
    HttpImageView ivUserHeader;
    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvReply)
    TextView tvReply;
    @Bind(R.id.ivBg)
    ImageView ivBg;
    @Bind(R.id.llBg)
    LinearLayout llBg;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;
    @Bind(R.id.llNoData)
    LinearLayout llNoData;
    private OnReplyClickListener onReplyClickListener;

    public CommentAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_comment, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean bean) {
        if (bean.getReply() != null && !bean.getReply().isEmpty()) {
            ivBg.setVisibility(View.VISIBLE);
            llBg.setVisibility(View.VISIBLE);
            rvReply.setVisibility(View.VISIBLE);
            CommentReplyAdapter adapter = new CommentReplyAdapter(bean.getReply());
            rvReply.setLayoutManager(new LinearLayoutManager(mContext));
            rvReply.setAdapter(adapter);
        } else {
            ivBg.setVisibility(View.GONE);
            llBg.setVisibility(View.GONE);
            rvReply.setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.tvReply);
        mDataManager.setValueToView(tvUserName, bean.getUsername());
        mDataManager.setValueToView(tvContent, bean.getContent());
        mDataManager.setValueToView(tvTime, TimeUtils.getFriendlyTimeSpanByNow(bean.getAddTime()));
        ivUserHeader.loadHeaderImage(bean.getAvatar());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public void setOnReplyClickListener(OnReplyClickListener onReplyClickListener) {
        this.onReplyClickListener = onReplyClickListener;
    }

    public interface OnReplyClickListener {
        void onReplyClick(int position);
    }
}
