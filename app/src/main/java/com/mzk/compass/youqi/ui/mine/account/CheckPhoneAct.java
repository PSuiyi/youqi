package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.BankBean;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/26.
 */

public class CheckPhoneAct extends BaseAppActivity {
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.etCode)
    EditTextWithDel etCode;
    @Bind(R.id.tvGetCode)
    TextView tvGetCode;

    private String from;

    private CountDownTimer timer;
    private BankBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_check_phone, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        if (from.equals("绑定银行卡")) {
            if (getIntent().hasExtra("bean")) {
                bean = (BankBean) getIntent().getSerializableExtra("bean");
            }
        }
    }

    @Override
    protected void initializeNavigation() {
        if (from.equals("查看银行卡")) {
            setTitleName("绑定银行卡");
        } else {
            setTitleName(from);
        }

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
        mDataManager.setValueToView(tvPhone, StringUtil.getSignPhone(mDataManager.readTempData(ZnzConstants.ACCOUNT)));
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick({R.id.tvNext, R.id.tvGetCode})
    public void onViewClicked(View v) {
        Map<String, String> params = new HashMap<>();
        switch (from) {
            case "修改手机号":
                params.put("type", "1");
                break;
            case "查看银行卡":
            case "绑定银行卡":
                params.put("type", "2");
                break;
        }
        switch (v.getId()) {
            case R.id.tvNext:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etCode))) {
                    mDataManager.showToast("请输入验证码");
                    return;
                }
                params.put("code", mDataManager.getValueFromView(etCode));
                mModel.requestCheckPhone(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        Bundle bundle = new Bundle();
                        bundle.putString("validateKey", responseOriginal.getString("data"));
                        switch (from) {
                            case "修改手机号":
                                gotoActivity(UpdatePhoneAct.class,bundle);
                                break;
                            case "查看银行卡":
                                gotoActivity(CardListAct.class, bundle);
                                break;
                            case "绑定银行卡":
                                bundle.putSerializable("bean", bean);
                                gotoActivity(CardAddAct.class, bundle);
                                break;
                        }
                        finish();
                    }
                });
                break;
            case R.id.tvGetCode:
                mModel.requestGetCode(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
//                        etCode.setText("123456");
                        timer.start();
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
}
