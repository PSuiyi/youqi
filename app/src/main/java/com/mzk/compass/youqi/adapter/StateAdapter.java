package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.StateBean;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.SwipeMenuLayout;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class StateAdapter extends BaseQuickAdapter<StateBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.swipeMenuLayout)
    SwipeMenuLayout swipeMenuLayout;
    @Bind(R.id.cbSelect)
    CheckBox cbSelect;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;

    boolean isCanSwipe;

    public void setCanSwipe(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }

    public StateAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_state, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, StateBean bean) {
        if (bean.isChecked()) {
            mDataManager.setViewVisibility(cbSelect, true);
        } else {
            mDataManager.setViewVisibility(cbSelect, false);
        }
        mDataManager.setValueToView(tvTitle, bean.getContent());
        mDataManager.setValueToView(tvTime, TimeUtils.getFormatTime(bean.getAddTime(), "yyyy-MM-dd HH:mm"));
        helper.addOnClickListener(R.id.llDelete);
        helper.addOnClickListener(R.id.llContainer);
        helper.addOnClickListener(R.id.cbSelect);

        swipeMenuLayout.setSwipeEnable(isCanSwipe);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
