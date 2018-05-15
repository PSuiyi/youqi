package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_check_phone, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName(from);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvNext, R.id.tvGetCode})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tvNext:
                switch (from) {
                    case "修改手机号":
                        gotoActivity(UpdatePhoneAct.class);
                        break;
                    case "绑定银行卡":
                        gotoActivity(BindCardAct.class);
                        break;
                }
                break;
            case R.id.tvGetCode:
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
}
