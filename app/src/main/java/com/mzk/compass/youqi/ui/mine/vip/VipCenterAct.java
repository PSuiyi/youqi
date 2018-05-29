package com.mzk.compass.youqi.ui.mine.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.common.Constants;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class VipCenterAct extends BaseAppActivity {
    @Bind(R.id.ivHeader)
    ImageView ivHeader;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.ivVip)
    ImageView ivVip;
    @Bind(R.id.tvVipTime)
    TextView tvVipTime;
    @Bind(R.id.tvRecharge)
    TextView tvRecharge;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_vip_center};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        tvTitle.setText("会员中心");
    }

    @Override
    protected void initializeView() {
        if (StringUtil.isBlank(mDataManager.readTempData(Constants.User.ISVIP))) {
            mDataManager.setViewVisibility(ivVip, false);
            mDataManager.setValueToView(tvVipTime, "你还不是会员，快去购买吧");
            mDataManager.setValueToView(tvRecharge, "购买");
        } else {
            if (mDataManager.readTempData(Constants.User.ISVIP).equals("1")) {
                mDataManager.setViewVisibility(ivVip, true);
                mDataManager.setValueToView(tvVipTime, "VIP会员" + TimeUtils.getFormatTime(mDataManager.readTempData(Constants.User.VIPTIME), "yyyy-MM-dd") + "到期");
                mDataManager.setValueToView(tvRecharge, "续费");
            } else {
                mDataManager.setViewVisibility(ivVip, false);
                mDataManager.setValueToView(tvVipTime, "你还不是会员，快去购买吧");
                mDataManager.setValueToView(tvRecharge, "购买");
            }
        }
    }

    @Override
    protected void loadDataFromServer() {

    }


    @OnClick({R.id.llBack, R.id.tvRecharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llBack:
                finish();
                break;
            case R.id.tvRecharge:
                gotoActivity(RechargeAct.class);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
