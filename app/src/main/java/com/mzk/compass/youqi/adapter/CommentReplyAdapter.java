package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.CommentReplyBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class CommentReplyAdapter extends BaseQuickAdapter<CommentReplyBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {


    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;

    public CommentReplyAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_comment_reply, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentReplyBean bean) {
        mDataManager.setValueToView(tvUserName, bean.getUsername());
        mDataManager.setValueToView(tvContent, bean.getContent());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
