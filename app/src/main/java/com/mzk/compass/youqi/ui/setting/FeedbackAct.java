package com.mzk.compass.youqi.ui.setting;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/29.
 */

public class FeedbackAct extends BaseAppActivity {
    @Bind(R.id.etContent)
    EditText etContent;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_feedback, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("意见反馈");
    }

    @Override
    protected void initializeView() {
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() >= 300) {
                    mDataManager.showToast("300字以内");
                    etContent.setText(editable.toString().trim().substring(0, 300));
                }
            }
        });
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

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
            mDataManager.showToast("请输入内容");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("content", mDataManager.getValueFromView(etContent));
        mModel.requestFeedBack(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("反馈提交成功");
                finish();
            }
        });
    }
}
