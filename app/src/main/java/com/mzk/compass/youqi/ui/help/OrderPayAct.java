package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.OrderBean;
import com.mzk.compass.youqi.bean.OrderConfirmBean;
import com.mzk.compass.youqi.bean.ProductBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/22 2018
 * User： PSuiyi
 * Description：
 */
public class OrderPayAct extends BaseAppActivity {
    @Bind(R.id.ivLogo)
    HttpImageView ivLogo;
    @Bind(R.id.tvProjectName)
    TextView tvProjectName;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.tvCount)
    TextView tvCount;
    @Bind(R.id.tvTotalMoney)
    TextView tvTotalMoney;
    private String orderCode;
    private OrderConfirmBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_order_pay, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("orderCode")) {
            orderCode = getIntent().getStringExtra("orderCode");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("支付订单");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("orderSerial", orderCode);
        mModel.requestOrderPay(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSON.parseObject(responseOriginal.getString("data"), OrderConfirmBean.class);
                if (bean != null) {
                    ivLogo.loadHttpImage(bean.getProductMobileImage());
                    mDataManager.setValueToView(tvProjectName, bean.getProductName());
                    mDataManager.setValueToView(tvPrice, "￥" + bean.getProductPrice());
                    mDataManager.setValueToView(tvCount, "x" + bean.getNum());
                    mDataManager.setValueToView(tvTotalMoney, "￥" + getTotalMoney());
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {

    }

    private String getTotalMoney() {
        if (StringUtil.isBlank(bean.getProductPrice())) {
            return "0";
        }
        if (StringUtil.isBlank(bean.getNum())) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(bean.getProductPrice());
        BigDecimal b2 = new BigDecimal(bean.getNum());
        double total = b1.multiply(b2).doubleValue();
        return total + "";
    }
}
