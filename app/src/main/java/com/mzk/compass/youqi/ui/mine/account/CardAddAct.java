package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.BankBean;
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
 * Created by Administrator on 2018/4/26.
 */

public class CardAddAct extends BaseAppActivity {
    @Bind(R.id.etBankCode)
    EditTextWithDel etBankCode;
    @Bind(R.id.etBank)
    EditTextWithDel etBank;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    private BankBean bean;
    private String validateKey;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_bind_card, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("validateKey")) {
            validateKey = getIntent().getStringExtra("validateKey");
        }
        if (getIntent().hasExtra("bean")) {
            bean = (BankBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("绑定银行卡");
    }

    @Override
    protected void initializeView() {
        if (bean != null) {
            etBankCode.setText(bean.getBankcard());
            etBank.setText(bean.getDetailbank());
        }
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        if (StringUtil.isBlank(mDataManager.getValueFromView(etBankCode))) {
            mDataManager.showToast("请输入银行卡号");
            return;
        }
        if (StringUtil.isBlank(mDataManager.getValueFromView(etBank))) {
            mDataManager.showToast("请输入银行");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("detailbank", mDataManager.getValueFromView(etBank));
        params.put("bankcard", mDataManager.getValueFromView(etBankCode));
        params.put("validateKey", validateKey);
        mModel.requestBindBank(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                Bundle bundle = new Bundle();
                bundle.putString("from", "绑定银行卡");
                gotoActivity(ManagerSuccessAct.class, bundle);
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
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
            case EventTags.REFRESH_BANK:
                finish();
                break;
        }
    }
}
