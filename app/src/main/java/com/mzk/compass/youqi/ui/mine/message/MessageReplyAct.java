package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.InteractMsgDetailBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MessageReplyAct extends BaseAppActivity {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.etContent)
    EditText etContent;
    private InteractMsgDetailBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_message_reply, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("bean")) {
            bean = (InteractMsgDetailBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("回复");
        znzToolBar.setNavRightText("发送", mDataManager.getColor(R.color.red));
        znzToolBar.setOnNavRightClickListener(view -> {
            if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
                mDataManager.showToast("请输入回复内容");
                return;
            }
            Map<String, String> params = new HashMap<>();
            params.put("id", bean.getMessageId());
            params.put("content", mDataManager.getValueFromView(etContent));
            mModel.requestMsgReply(params, new ZnzHttpListener() {
                @Override
                public void onSuccess(JSONObject responseOriginal) {
                    super.onSuccess(responseOriginal);
                    mDataManager.showToast("回复成功");
                    finish();
                }
            });
        });
    }

    @Override
    protected void initializeView() {
        mDataManager.setValueToView(tvTitle, "项目：" + bean.getProjectName());
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
