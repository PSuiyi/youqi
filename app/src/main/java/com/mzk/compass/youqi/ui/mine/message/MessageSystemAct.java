package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.MessageBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/19.
 * 系统消息
 */

public class MessageSystemAct extends BaseAppActivity {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvContent)
    TextView tvContent;
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
        mModel.requestSysMsgDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    MessageBean bean = JSON.parseObject(responseOriginal.getString("data"), MessageBean.class);
                    mDataManager.setValueToView(tvTime, TimeUtils.millis2String(StringUtil.stringToLong(bean.getAddTime()) * 1000, "yyyy.MM.dd HH:mm"));
                    mDataManager.setValueToView(tvContent, bean.getContent());
                    mDataManager.setValueToView(tvTitle, bean.getTitle());

                }
            }
        });
    }

}
