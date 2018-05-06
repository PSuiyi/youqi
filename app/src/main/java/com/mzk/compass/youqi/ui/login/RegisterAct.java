package com.mzk.compass.youqi.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
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
public class RegisterAct extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.etPhone)
    EditTextWithDel etPhone;
    @Bind(R.id.etCode)
    EditTextWithDel etCode;
    @Bind(R.id.tvCode)
    TextView tvCode;
    private CountDownTimer timer;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_register, 1};
    }

    @Override
    protected void initializeVariate() {

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
        timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                tvCode.setClickable(false);
                mDataManager.setValueToView(tvCode, l / 1000 + " s");
            }

            @Override
            public void onFinish() {
                tvCode.setClickable(true);
                mDataManager.setValueToView(tvCode, "重新发送");
            }
        };
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick({R.id.tvSubmit, R.id.tvCode})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tvSubmit:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入手机号");
                    return;
                }
                if (!StringUtil.isMobile(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入正确的手机号");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etCode))) {
                    mDataManager.showToast("请输入验证码");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("phone", mDataManager.getValueFromView(etPhone));
                bundle.putString("code", mDataManager.getValueFromView(etCode));
                gotoActivity(Register2Act.class, bundle);
                break;
            case R.id.tvCode:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入手机号");
                    return;
                }
                if (!StringUtil.isMobile(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入正确的手机号");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mDataManager.getValueFromView(etPhone));
                params.put("type", "2");
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
