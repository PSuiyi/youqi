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

/**
 * Created by Administrator on 2018/4/26.
 */

public class CardListAct extends BaseAppActivity {

    @Bind(R.id.tvBank)
    TextView tvBank;
    @Bind(R.id.tvNumber)
    TextView tvNumber;
    private BankBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_bank_card, 1};
    }

    @Override
    protected void initializeVariate() {
    }

    @Override
    protected void initializeNavigation() {
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
        mModel.requestBankList(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSON.parseObject(responseOriginal.getString("data"), BankBean.class);
                if (bean == null || StringUtil.isBlank(bean.getBankcard())) {
                    znzToolBar.setNavRightText("绑定", mDataManager.getColor(R.color.red));
                    showNoData();
                } else {
                    znzToolBar.setNavRightText("更换", mDataManager.getColor(R.color.red));
                    tvBank.setText(bean.getDetailbank());
                    tvNumber.setText(bean.getBankcard());
                    hideNoData();
                }
            }
        });
    }

    /**
     * 方法描述 隐藏银行卡号中间的字符串（使用*号），显示前四后四
     *
     * @param cardNo
     * @return
     * @author yaomy
     * @date 2018年4月3日 上午10:37:00
     */
    public String hideCardNo(String cardNo) {
        if (StringUtil.isBlank(cardNo)) {
            return cardNo;
        }

        int length = cardNo.length();
        int beforeLength = 4;
        int afterLength = 4;
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i < beforeLength || i >= (length - afterLength)) {
                sb.append(cardNo.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
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
