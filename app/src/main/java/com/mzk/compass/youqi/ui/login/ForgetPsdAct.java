package com.mzk.compass.youqi.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
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
 * Date： 2018/4/28 2018
 * User： PSuiyi
 * Description：
 */
public class ForgetPsdAct extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.etPhone)
    EditTextWithDel etPhone;
    @Bind(R.id.etCode)
    EditTextWithDel etCode;
    @Bind(R.id.tvGetCode)
    TextView tvGetCode;
    private CountDownTimer timer;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_forget_psd, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("忘记密码");
    }

    @Override
    protected void initializeView() {
//        if (!StringUtil.isBlank(mDataManager.readTempData(ZnzConstants.ACCOUNT))) {
//            mDataManager.setValueToView(etPhone, mDataManager.readTempData(ZnzConstants.ACCOUNT));
//        }
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
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mDataManager.getValueFromView(etPhone));
                params.put("type", "3");
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
                bundle.putString("mobile", mDataManager.getValueFromView(etPhone));
                bundle.putString("code", mDataManager.getValueFromView(etCode));
                gotoActivity(ForgetPsd2Act.class, bundle);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        EventManager.unregister(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_FORGET_PSD:
                finish();
                break;
        }
    }
}
