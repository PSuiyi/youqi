package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.ui.common.RemindAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

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
public class ProductOrderComfirmAct extends BaseAppActivity {
    @Bind(R.id.tvPhone)
    LinearLayout tvPhone;
    @Bind(R.id.tvBuy)
    TextView tvBuy;
    @Bind(R.id.tvProductName)
    TextView tvProductName;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.tvCount)
    TextView tvCount;
    @Bind(R.id.tvTotalMoney)
    TextView tvTotalMoney;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etContent)
    EditText etContent;
    @Bind(R.id.cbSelect)
    CheckBox cbSelect;
    @Bind(R.id.tvRealMoney)
    TextView tvRealMoney;
    @Bind(R.id.tvTel)
    TextView tvTel;
    private ProductBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_product_order_comfirm, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("bean")) {
            bean = (ProductBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("提交订单");
    }

    @Override
    protected void initializeView() {
        if (bean != null) {
            mDataManager.setValueToView(tvProductName, bean.getName());
            mDataManager.setValueToView(tvPrice, bean.getRealPrice());
            mDataManager.setValueToView(tvCount, "x" + bean.getCount());
            mDataManager.setValueToView(tvTotalMoney, "￥" + bean.getTotalMoney());
            mDataManager.setValueToView(tvRealMoney, "￥" + bean.getTotalMoney());
            mDataManager.setValueToView(tvTel, bean.getTel());
        }
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

    @OnClick({R.id.tvPhone, R.id.tvBuy, R.id.cbSelect, R.id.tvAgree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvPhone:
                mDataManager.callPhone(activity, bean.getTel());
                break;
            case R.id.tvBuy:
                if (!cbSelect.isChecked()) {
                    mDataManager.showToast("请同意优企条款");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("num", bean.getCount());
                params.put("productId", bean.getId());
                if (!StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
                    params.put("remark", mDataManager.getValueFromView(etContent));
                } else {
                    params.put("remark", "");
                }

                if (!StringUtil.isBlank(mDataManager.getValueFromView(etPhone))) {
                    params.put("tel", mDataManager.getValueFromView(etPhone));
                } else {
                    params.put("tel", "");
                }

                mModel.requestOrderSubmit(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        String orderCode = responseOriginal.getString("data");
                        Bundle bundle = new Bundle();
                        bundle.putString("orderCode", orderCode);
                        gotoActivity(OrderPayAct.class, bundle);
                        finish();
                    }
                });
                break;
            case R.id.cbSelect:
                break;
            case R.id.tvAgree:
                gotoActivity(RemindAct.class);
                break;
        }
    }
}
