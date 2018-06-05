package com.mzk.compass.youqi.ui.mine.identify.company;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.mine.identify.personal.MemberMangerAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class CompanyManagerAct extends BaseAppActivity {
    @Bind(R.id.llCreate)
    LinearLayout llCreate;
    @Bind(R.id.llManager)
    LinearLayout llManager;
    @Bind(R.id.llIdentify)
    LinearLayout llIdentify;

    private boolean clickable = false;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_company_manager, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("企业认证管理");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("pageSize", "100");
        mModel.requestMemberList(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                clickable = true;
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
                clickable = false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.llCreate, R.id.llManager, R.id.llIdentify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCreate:
                gotoActivity(CompanyCreateNextAct.class);
                break;
            case R.id.llManager:
                if (!clickable) {
                    mDataManager.showToast("请先创建企业");
                    return;
                }
                gotoActivity(MemberMangerAct.class);
                break;
            case R.id.llIdentify:
                if (!clickable) {
                    mDataManager.showToast("请先创建企业");
                    return;
                }
                gotoActivity(CompanyIdentifyAct.class);
                break;
        }
    }
}
