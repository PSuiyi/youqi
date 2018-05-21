package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/26.
 */

public class BindCardAct extends BaseAppActivity {
    @Bind(R.id.etBankCode)
    EditTextWithDel etBankCode;
    @Bind(R.id.etBank)
    EditTextWithDel etBank;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_bind_card, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("绑定银行卡");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString("from", "绑定银行卡");
        gotoActivity(ManagerSuccessAct.class, bundle);
    }
}
