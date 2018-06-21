package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.InteractMsgDetailBean;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/19.
 * 交易消息
 */

public class MessageTradeAct extends BaseAppActivity {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.ivHeader)
    HttpImageView ivHeader;
    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvContent)
    TextView tvContent;
    private InteractMsgDetailBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_message_trade, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("bean")) {
            bean = (InteractMsgDetailBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("消息详情");
        znzToolBar.setNavRightText("回复",mDataManager.getColor(R.color.red));
        znzToolBar.setOnNavRightClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("bean", bean);
            gotoActivity(MessageReplyAct.class, bundle);
        });
    }

    @Override
    protected void initializeView() {
        mDataManager.setValueToView(tvTitle, bean.getProjectName());
        mDataManager.setValueToView(tvUserName, bean.getUsername());
        mDataManager.setValueToView(tvContent, bean.getContent());
        mDataManager.setValueToView(tvTime, TimeUtils.millis2String(StringUtil.stringToLong(bean.getAddTime()) * 1000, "yyyy.MM.dd HH:mm"));
        ivHeader.loadHeaderImage(bean.getAvatar());
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
