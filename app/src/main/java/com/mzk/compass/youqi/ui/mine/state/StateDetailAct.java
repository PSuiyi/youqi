package com.mzk.compass.youqi.ui.mine.state;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.StateBean;
import com.znz.compass.znzlibray.utils.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class StateDetailAct extends BaseAppActivity {
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvTime)
    TextView tvTime;

    private StateBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_state_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("bean")) {
            bean = (StateBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("我的动态");
    }

    @Override
    protected void initializeView() {
        mDataManager.setValueToView(tvContent, bean.getContent());
        mDataManager.setValueToView(tvTime, TimeUtils.getFormatTime(bean.getAddTime(), "yyyy-MM-dd HH:mm"));
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
