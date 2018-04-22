package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/22 2018
 * User： PSuiyi
 * Description：
 */
public class ProductOrderComfirmAct extends BaseAppActivity {
    @Bind(R.id.tvPhone)
    LinearLayout tvPhone;
    @Bind(R.id.tvBuy)
    TextView tvBuy;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_product_order_comfirm, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("提交订单");
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

    @OnClick({R.id.tvPhone, R.id.tvBuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvPhone:
                mDataManager.callPhone(activity,"400-8888-8888");
                break;
            case R.id.tvBuy:
                gotoActivity(OrderPayAct.class);
                break;
        }
    }
}
