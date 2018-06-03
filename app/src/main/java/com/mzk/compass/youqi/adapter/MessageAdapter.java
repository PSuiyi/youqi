package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MessageBean;
import com.mzk.compass.youqi.ui.mine.message.MessageInteractAct;
import com.mzk.compass.youqi.ui.mine.message.MessageSystemAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTradeAct;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.SwipeMenuLayout;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.llDelete)
    LinearLayout llDelete;
    @Bind(R.id.swipeMenuLayout)
    SwipeMenuLayout swipeMenuLayout;
    @Bind(R.id.cbSelect)
    CheckBox cbSelect;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;

    public MessageAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_message, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean bean) {
        cbSelect.setChecked(bean.isSelect());
        if (bean.isEdit()) {
            mDataManager.setViewVisibility(cbSelect, true);
        } else {
            mDataManager.setViewVisibility(cbSelect, false);
        }
        switch (bean.getType()) {
            case "互动消息":
                if (StringUtil.isBlank(bean.getMsgwarning())) {
                    mDataManager.setValueToView(tvTitle, bean.getTitle());
                } else {
                    mDataManager.setValueToView(tvTitle, bean.getMsgwarning());
                }
                break;
            case "交易信息":
                mDataManager.setValueToView(tvTitle, bean.getContent());
                break;
            case "系统信息":
                mDataManager.setValueToView(tvTitle, bean.getTitle());
                break;
            default:
                mDataManager.setValueToView(tvTitle, bean.getTitle());
                break;
        }

        mDataManager.setValueToView(tvTime, TimeUtils.millis2String(StringUtil.stringToLong(bean.getAddTime()) * 1000, "yyyy.MM.dd HH:mm"));
        helper.addOnClickListener(R.id.llDelete);
        helper.addOnClickListener(R.id.llContainer);
        helper.addOnClickListener(R.id.cbSelect);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
