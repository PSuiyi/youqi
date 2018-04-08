package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class CommentAdapter extends BaseQuickAdapter<BaseZnzBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.rvReply)
    RecyclerView rvReply;
    private OnReplyClickListener onReplyClickListener;

    public CommentAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_comment, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseZnzBean bean) {
        List<BaseZnzBean> replys = new ArrayList<>();
        replys.add(new BaseZnzBean());
        replys.add(new BaseZnzBean());
        replys.add(new BaseZnzBean());
        CommentReplyAdapter adapter = new CommentReplyAdapter(replys);
        rvReply.setLayoutManager(new LinearLayoutManager(mContext));
        rvReply.setAdapter(adapter);
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
