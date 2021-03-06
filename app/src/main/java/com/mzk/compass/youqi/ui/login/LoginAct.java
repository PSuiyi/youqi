package com.mzk.compass.youqi.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.TabHomeActivity;
import com.mzk.compass.youqi.view.EditTextPsd;
import com.socks.library.KLog;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.znz.compass.umeng.login.LoginAuthManager;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private LoginAuthManager loginManager;
    private String login_type;
    private String open_id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_login, 1};
    }

    @Override
    protected void initializeVariate() {
        loginManager = LoginAuthManager.getInstance(context);
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
        if (!StringUtil.isBlank(mDataManager.readTempData(ZnzConstants.ACCOUNT))) {
            mDataManager.setValueToView(etPhone, mDataManager.readTempData(ZnzConstants.ACCOUNT));
        }
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

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Set<Map.Entry<String, String>> set = map.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                KLog.e(key + "---" + value);
            }
            switch (share_media) {
                case QQ:
                    login_type = "3";
                    open_id = map.get("openid");
                    break;
                case WEIXIN:
                    login_type = "1";
                    open_id = map.get("unionid");
                    break;
                case SINA:
                    login_type = "2";
                    open_id = map.get("uid");
                    break;
            }
            requestAuthLogin();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };


    private void requestAuthLogin() {
        Map<String, String> params = new HashMap<>();
        params.put("open_id", open_id);
        params.put("type", login_type);
        mModel.requestAutherLogin(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                JSONObject jsonObject = JSON.parseObject(responseOriginal.getString("data"));
                String token = jsonObject.getString("token");
                mDataManager.saveTempData(ZnzConstants.ACCESS_TOKEN, token);
                mDataManager.saveTempData(ZnzConstants.ACCOUNT, mDataManager.getValueFromView(etPhone));
                mDataManager.saveBooleanTempData(ZnzConstants.IS_LOGIN, true);
                if (jsonObject.getString("goTo").equals("1")) {
                    gotoActivity(BindAct.class);
                } else {
                    gotoActivityWithClearStack(TabHomeActivity.class);
                }
                finish();
            }
        });
    }

    @OnClick({R.id.tvSubmit, R.id.tvQQ, R.id.tvWechat, R.id.tvWeibo, R.id.tvFoget, R.id.tvLoginType, R.id.tvGetCode})
    public void onViewClicked(View view) {
        Map<String, String> params = new HashMap<>();
        switch (view.getId()) {
            case R.id.tvQQ:
                loginManager.loginQQ(activity, umAuthListener);
                break;
            case R.id.tvWechat:
                loginManager.loginWeChat(activity, umAuthListener);
                break;
            case R.id.tvWeibo:
                loginManager.loginWeibo(activity, umAuthListener);
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

                params.put("mobile", mDataManager.getValueFromView(etPhone));
                if (isCode) {
                    if (StringUtil.isBlank(mDataManager.getValueFromView(etCode))) {
                        mDataManager.showToast("请输入验证码");
                        return;
                    }
                    params.put("login_type", "2");
                    params.put("code", mDataManager.getValueFromView(etCode));
                } else {
                    if (StringUtil.isBlank(etPsd.getEditText())) {
                        mDataManager.showToast("请输入密码");
                        return;
                    }
                    params.put("login_type", "1");
                    params.put("password", etPsd.getEditText());
                }
                mModel.requestLogin(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        JSONObject jsonObject = JSON.parseObject(responseOriginal.getString("data"));
                        String token = jsonObject.getString("token");
                        mDataManager.saveTempData(ZnzConstants.ACCESS_TOKEN, token);
                        mDataManager.saveTempData(ZnzConstants.ACCOUNT, mDataManager.getValueFromView(etPhone));
                        mDataManager.saveBooleanTempData(ZnzConstants.IS_LOGIN, true);
                        gotoActivityWithClearStack(TabHomeActivity.class);
                        finish();
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
//                        mDataManager.setValueToView(etCode, "123456");
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
                    tvLoginType.setText("验证码登录");
                } else {
                    mDataManager.setViewVisibility(llCode, true);
                    mDataManager.setViewVisibility(llPsd, false);
                    tvLoginType.setText("密码登录");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
