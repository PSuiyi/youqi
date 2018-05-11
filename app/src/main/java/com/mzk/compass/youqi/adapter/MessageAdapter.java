package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MessageBean;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;

    public MessageAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_message, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean bean) {
        mDataManager.setValueToView(tvTitle,bean.getMsgwarning());
        mDataManager.setValueToView(tvTime, TimeUtils.getFormatTime(bean.getTime(),"yyyy-MM-dd HH:mm"));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
