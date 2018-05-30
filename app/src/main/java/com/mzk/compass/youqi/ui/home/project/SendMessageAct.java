package com.mzk.compass.youqi.ui.home.project;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

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
 * Date： 2018/4/28 2018
 * User： PSuiyi
 * Description：
 */
public class SendMessageAct extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.etContent)
    EditText etContent;
    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_send_message, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("约谈信息");
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
                if (editable.toString().trim().length() >= 200) {
                    mDataManager.showToast("200字以内");
                    etContent.setText(editable.toString().trim().substring(0, 200));
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
        params.put("projectId", id);
        mModel.requestSendMessage(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("提交成功");
                finish();
            }
        });
    }
}
