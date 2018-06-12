package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.BankBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/26.
 */

public class BankCardListAct extends BaseAppActivity {

    @Bind(R.id.tvBank)
    TextView tvBank;
    @Bind(R.id.tvNumber)
    TextView tvNumber;
    private String validateKey;
    private BankBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_bank_card, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("validateKey")) {
            validateKey = getIntent().getStringExtra("validateKey");
        }
    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setNavRightText("更换");
        setTitleName("绑定银行卡");
        znzToolBar.setOnNavRightClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("from", "绑定银行卡");
            if (bean != null) {
                bundle.putSerializable("bean", bean);
            }
            gotoActivity(CheckPhoneAct.class, bundle);
        });
    }


    @Override
    protected void initializeView() {
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("validateKey", validateKey);
        mModel.requestBankList(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (StringUtil.isBlank(responseOriginal.getString("data"))) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "绑定银行卡");
                    gotoActivity(CheckPhoneAct.class, bundle);
                } else {
                    bean = JSON.parseObject(responseOriginal.getString("data"), BankBean.class);
                    tvBank.setText(bean.getDetailbank());
                    tvNumber.setText(bean.getDetailbank());
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_BANK:
                loadDataFromServer();
                break;
        }
    }
}
