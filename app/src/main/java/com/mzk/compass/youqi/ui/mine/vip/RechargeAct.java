package com.mzk.compass.youqi.ui.mine.vip;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.RechargeAdapter;
import com.mzk.compass.youqi.base.BaseAppPayActivity;
import com.mzk.compass.youqi.bean.PriceBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzpay.bean.WeixinBean;
import com.znz.compass.znzpay.common.PayKeys;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/25.
 */

public class RechargeAct extends BaseAppPayActivity {
    @Bind(R.id.rvPrice)
    RecyclerView rvPrice;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    private RechargeAdapter adapter;
    private List<PriceBean> dataList = new ArrayList<>();

    private PopupWindowManager popupWindowManager;
    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_recharge, 1};
    }

    @Override
    protected void initializeVariate() {
        popupWindowManager = PopupWindowManager.getInstance(context);
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("购买VIP");
    }

    @Override
    protected void initializeView() {
        adapter = new RechargeAdapter(dataList);
        rvPrice.setLayoutManager(new LinearLayoutManager(activity));
        rvPrice.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            for (PriceBean bean : dataList) {
                bean.setSelect(false);
            }
            PriceBean bean = dataList.get(position);
            bean.setSelect(true);
            adapter.notifyDataSetChanged();
            id = bean.getId();
            tvTotalPrice.setText("￥" + bean.getPrice());
        });
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestPriceList(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    dataList.clear();
                    dataList.addAll(JSON.parseArray(responseOriginal.getString("data"), PriceBean.class));
                    if (!dataList.isEmpty()) {
                        dataList.get(0).setSelect(true);
                        id = dataList.get(0).getId();
                        tvTotalPrice.setText("￥" + dataList.get(0).getPrice());
                    }
                    adapter.notifyDataSetChanged();
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

    @Override
    protected void onPayResult(int result) {
        switch (result) {
            case PayKeys.ALI_PAY_SUCESSS:
            case PayKeys.WX_PAY_SUCESSS:
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_PAY_VIP));
                finish();
                break;
            case PayKeys.ALI_PAY_FAIL:
            case PayKeys.WX_PAY_FAIL:
                mDataManager.showToast("支付失败");
                break;
        }

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        popupWindowManager.showPayWays(tvSubmit, new PopupWindowManager.OnPopupWindowClickListener() {
            @Override
            public void onPopupWindowClick(String type, String[] values) {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("type", type);
                mModel.requestCreateOrder(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        if (type.equals("1")) {
                            handleAliPay(responseOriginal.getString("data"));
                        } else {
                            WeixinBean bean = JSONObject.parseObject(responseOriginal.getString("data"), WeixinBean.class);
                            handleWeixinPay(bean);
                        }
                    }
                });
            }
        });
    }
}
