package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MessageInteractAct extends BaseAppActivity {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTag)
    TextView tvTag;
    @Bind(R.id.tvDiscribe)
    TextView tvDiscribe;
    @Bind(R.id.ivHeader)
    HttpImageView ivHeader;
    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvContent)
    TextView tvContent;
    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_message_interact, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("消息详情");
        znzToolBar.setNavRightText("回复");
        znzToolBar.setOnNavRightClickListener(view -> {
            gotoActivity(MessageReplyAct.class);
        });
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        mModel.requestMessageDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
