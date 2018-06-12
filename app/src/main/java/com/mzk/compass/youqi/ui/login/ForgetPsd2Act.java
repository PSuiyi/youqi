package com.mzk.compass.youqi.ui.login;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import org.greenrobot.eventbus.EventBus;

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
public class ForgetPsd2Act extends BaseAppActivity {
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.etPst)
    EditTextWithDel etPst;
    @Bind(R.id.etPsdConfirm)
    EditTextWithDel etPsdConfirm;

    private String mobile;
    private String code;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_forget_psd2, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("mobile")) {
            mobile = getIntent().getStringExtra("mobile");
        }
        if (getIntent().hasExtra("code")) {
            code = getIntent().getStringExtra("code");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("忘记密码");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        if (StringUtil.isBlank(mDataManager.getValueFromView(etPst))) {
            mDataManager.showToast("请输入新密码");
            return;
        }
        if (StringUtil.isBlank(mDataManager.getValueFromView(etPsdConfirm))) {
            mDataManager.showToast("请确认新密码");
            return;
        }
        if (!mDataManager.getValueFromView(etPst).equals(mDataManager.getValueFromView(etPsdConfirm))) {
            mDataManager.showToast("两次密码输入不一致，请重新输入");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("password", mDataManager.getValueFromView(etPsdConfirm));
        mModel.requestForgetPsd(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_FORGET_PSD));
                finish();
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }
}
