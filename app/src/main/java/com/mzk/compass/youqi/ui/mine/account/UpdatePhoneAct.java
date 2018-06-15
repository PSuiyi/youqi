package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/26.
 */

public class UpdatePhoneAct extends BaseAppActivity {
    @Bind(R.id.etPhone)
    EditTextWithDel etPhone;
    @Bind(R.id.etCode)
    EditTextWithDel etCode;
    @Bind(R.id.tvGetCode)
    TextView tvGetCode;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    private String validateKey;
    private CountDownTimer timer;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_update_phone, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("validateKey")) {
            validateKey = getIntent().getStringExtra("validateKey");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("修改手机号");
    }

    @Override
    protected void initializeView() {
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
    protected void loadDataFromServer() {

    }


    @OnClick({R.id.tvGetCode, R.id.tvSubmit})
    public void onViewClicked(View view) {
        Map<String, String> params = new HashMap<>();
        switch (view.getId()) {
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
                params.put("type", "5");
                params.put("validateKey", validateKey);
                mModel.requestCode(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        timer.start();
//                        mDataManager.setValueToView(etCode, "123456");
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
                break;
            case R.id.tvSubmit:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etCode))) {
                    mDataManager.showToast("请输入验证码");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入手机号");
                    return;
                }
                params.put("mobile", mDataManager.getValueFromView(etPhone));
                params.put("code", mDataManager.getValueFromView(etCode));
                mModel.requestChangePhone(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        Bundle bundle = new Bundle();
                        bundle.putString("from", "修改手机号");
                        mDataManager.saveTempData(ZnzConstants.ACCOUNT, mDataManager.getValueFromView(etPhone));
                        gotoActivity(ManagerSuccessAct.class, bundle);
                    }
                });
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_PHONE:
                finish();
                break;
        }
    }
}
