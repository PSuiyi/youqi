package com.mzk.compass.youqi.ui.mine.message;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/19.
 * 系统消息
 */

public class MessageSystemAct extends BaseAppActivity {
    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_message_system, 1};
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
}
