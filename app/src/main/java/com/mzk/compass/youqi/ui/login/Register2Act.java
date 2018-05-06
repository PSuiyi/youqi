package com.mzk.compass.youqi.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.TabHomeActivity;
import com.mzk.compass.youqi.ui.common.RemindAct;
import com.mzk.compass.youqi.view.EditTextPsd;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;
import com.znz.compass.znzlibray.views.ZnzToolBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/16 2018
 * User： PSuiyi
 * Description：
 */
public class Register2Act extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;
    @Bind(R.id.etPsd)
    EditTextPsd etPsd;
    @Bind(R.id.etCompassPsd)
    EditTextPsd etCompassPsd;
    @Bind(R.id.cbAgreement)
    CheckBox cbAgreement;

    private String phone;
    private String code;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_register2, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("phone")) {
            phone = getIntent().getStringExtra("phone");
        }
        if (getIntent().hasExtra("code")) {
            code = getIntent().getStringExtra("code");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("");
        setNavLeftGone();
        znzToolBar.setNavRightText("登录");
        znzToolBar.setOnNavRightClickListener(v -> {
            gotoActivityWithClearStack(LoginAct.class);
        });
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }


    @OnClick({R.id.tvRemind, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRemind:
                gotoActivity(RemindAct.class);
                break;
            case R.id.tvSubmit:
                if (StringUtil.isBlank(etPsd.getEditText())) {
                    mDataManager.showToast("请输入密码");
                    return;
                }
                if (StringUtil.isBlank(etPsd.getEditText())) {
                    mDataManager.showToast("请确认密码");
                    return;
                }
                if (!etPsd.getEditText().equals(etCompassPsd.getEditText())) {
                    mDataManager.showToast("密码输入不一致，请重新输入");
                    return;
                }
                if (!cbAgreement.isChecked()) {
                    mDataManager.showToast("请同意企业条款");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("mobile", phone);
                params.put("code", code);
                params.put("password", mDataManager.getValueFromView(etPsd));
                mModel.requestRegister(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        JSONObject jsonObject = JSON.parseObject(responseOriginal.getString("data"));
                        String token = jsonObject.getString("token");
                        mDataManager.saveTempData(ZnzConstants.ACCESS_TOKEN, token);
                        mDataManager.saveBooleanTempData(ZnzConstants.IS_LOGIN, true);
                        gotoActivityWithClearStack(TabHomeActivity.class);
                    }
                });
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
