package com.mzk.compass.youqi.ui.mine.identify.personal;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.home.people.PeopleApproveAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/23.
 */

public class IdentifyPersonalAct extends BaseAppActivity {

    @Bind(R.id.tvIdentify)
    TextView tvIdentify;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_identify_personal, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("个人认证管理");
    }

    @Override
    protected void initializeView() {
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestIdentifyStatus(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (StringUtil.isBlank(responseOriginal.getString("data")) || responseOriginal.getString("data").equals("未认证")) {
                    tvIdentify.setText("未认证");
                } else {
                    tvIdentify.setText("");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.llPersonal)
    public void onViewClicked() {
        gotoActivity(PeopleApproveAct.class);
    }
}
