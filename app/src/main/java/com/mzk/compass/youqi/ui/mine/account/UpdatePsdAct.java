package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;

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
 * Created by Administrator on 2018/4/26.
 */

public class UpdatePsdAct extends BaseAppActivity {
    @Bind(R.id.etOldPsd)
    EditTextWithDel etOldPsd;
    @Bind(R.id.etNewPsd)
    EditTextWithDel etNewPsd;
    @Bind(R.id.etConfirmPsd)
    EditTextWithDel etConfirmPsd;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_update_psd, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("修改密码");
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

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        if (StringUtil.isBlank(mDataManager.getValueFromView(etOldPsd))) {
            mDataManager.showToast("请输入旧密码");
            return;
        }
        if (StringUtil.isBlank(mDataManager.getValueFromView(etNewPsd))) {
            mDataManager.showToast("请输入新密码");
            return;
        }
        if (StringUtil.isBlank(mDataManager.getValueFromView(etConfirmPsd))) {
            mDataManager.showToast("请确认新密码密码");
            return;
        }

        if (!mDataManager.getValueFromView(etNewPsd).equals(mDataManager.getValueFromView(etConfirmPsd))) {
            mDataManager.showToast("两次密码输入不一致，请重新输入");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("pass", mDataManager.getValueFromView(etOldPsd));
        params.put("newPass", mDataManager.getValueFromView(etNewPsd));
        params.put("conPass", mDataManager.getValueFromView(etConfirmPsd));
        mModel.requestUpdatePsd(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("密码修改成功");
                finish();
            }
        });
    }
}
