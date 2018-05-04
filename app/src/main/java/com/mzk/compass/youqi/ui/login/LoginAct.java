package com.mzk.compass.youqi.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.TabHomeActivity;
import com.mzk.compass.youqi.view.EditTextPsd;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

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
public class LoginAct extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.tvFoget)
    TextView tvFoget;
    @Bind(R.id.etPhone)
    EditTextWithDel etPhone;
    @Bind(R.id.etPsd)
    EditTextPsd etPsd;
    @Bind(R.id.llPsd)
    LinearLayout llPsd;
    @Bind(R.id.llCode)
    LinearLayout llCode;
    @Bind(R.id.tvLoginType)
    TextView tvLoginType;
    @Bind(R.id.etCode)
    EditTextWithDel etCode;
    @Bind(R.id.tvGetCode)
    TextView tvGetCode;

    private boolean isCode;
    private CountDownTimer timer;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_login, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setSwipeBackEnable(false);
        setTitleName("");
        setNavLeftGone();
        znzToolBar.setNavRightText("注册");
        znzToolBar.setOnNavRightClickListener(v -> {
            gotoActivity(RegisterAct.class);
        });


        timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                tvGetCode.setClickable(false);
                mDataManager.setValueToView(tvGetCode, l / 1000 + " s");
            }

            @Override
            public void onFinish() {
                tvGetCode.setClickable(true);
                mDataManager.setValueToView(tvGetCode, "重新发送");
            }
        };
    }

    @Override
    protected void initializeView() {

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

    @OnClick({R.id.tvSubmit, R.id.tvFoget, R.id.tvLoginType, R.id.tvGetCode})
    public void onViewClicked(View view) {
        Map<String, String> params = new HashMap<>();
        switch (view.getId()) {
            case R.id.tvSubmit:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入手机号");
                    return;
                }
                if (!StringUtil.isMobile(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入正确的手机号");
                    return;
                }

                params.put("mobile", mDataManager.getValueFromView(etPhone));
                if (isCode) {
                    if (StringUtil.isBlank(mDataManager.getValueFromView(etCode))) {
                        mDataManager.showToast("请输入验证码");
                        return;
                    }
                    params.put("login_type", "2");
                    params.put("code", mDataManager.getValueFromView(etCode));
                } else {
                    if (StringUtil.isBlank(mDataManager.getValueFromView(etPsd))) {
                        mDataManager.showToast("请输入密码");
                        return;
                    }
                    params.put("login_type", "1");
                    params.put("password", mDataManager.getValueFromView(etPsd));
                }

                mModel.requestLogin(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        gotoActivity(TabHomeActivity.class);
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
                break;
            case R.id.tvFoget:
                gotoActivity(ForgetPsdAct.class);
                break;
            case R.id.tvGetCode:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入手机号");
                    return;
                }
                if (!StringUtil.isMobile(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入正确的手机号");
                    return;
                }
                params.put("mobile", mDataManager.getValueFromView(etPhone));
                params.put("type", "1");
                mModel.requestCode(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        timer.start();
                        mDataManager.setValueToView(etCode, "123456");
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
                break;
            case R.id.tvLoginType:
                if (isCode) {
                    mDataManager.setViewVisibility(llCode, false);
                    mDataManager.setViewVisibility(llPsd, true);
                    tvLoginType.setText("密码登录");
                } else {
                    mDataManager.setViewVisibility(llCode, true);
                    mDataManager.setViewVisibility(llPsd, false);
                    tvLoginType.setText("验证码登录");
                }
                isCode = !isCode;
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

}
