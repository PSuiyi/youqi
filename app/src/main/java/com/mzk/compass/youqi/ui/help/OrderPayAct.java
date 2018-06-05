package com.mzk.compass.youqi.ui.help;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppPayActivity;
import com.mzk.compass.youqi.bean.OrderConfirmBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Date： 2018/4/22 2018
 * User： PSuiyi
 * Description：
 */
public class OrderPayAct extends BaseAppPayActivity {
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
    @Bind(R.id.rbWx)
    RadioButton rbWx;
    @Bind(R.id.rbAli)
    RadioButton rbAli;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    private String orderCode;
    private OrderConfirmBean bean;
    private int orderType;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_order_pay, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("orderCode")) {
            orderCode = getIntent().getStringExtra("orderCode");
            currentOrderCode = orderCode;
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("支付订单");
    }

    @Override
    protected void initializeView() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbWx:
                        orderType = 1;
                        break;
                    case R.id.rbAli:
                        orderType = 0;
                        break;
                }
            }
        });
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
    protected void onPayResult(int result) {

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        if (orderType == 0) {
            handleAliPay();
        } else {
            handleWeixinPay();
        }
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
