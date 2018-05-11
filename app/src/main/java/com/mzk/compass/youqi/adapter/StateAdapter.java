package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.StateBean;
import com.mzk.compass.youqi.ui.mine.state.StateDetailAct;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class StateAdapter extends BaseQuickAdapter<StateBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;

    public StateAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_state, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, StateBean bean) {
        setOnItemClickListener(this);
        mDataManager.setValueToView(tvTitle, bean.getContent());
        mDataManager.setValueToView(tvTime, TimeUtils.getFormatTime(bean.getAddTime(), "yyyy-MM-dd HH:mm"));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(StateDetailAct.class);
    }
}
