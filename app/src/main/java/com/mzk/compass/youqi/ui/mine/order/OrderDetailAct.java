package com.mzk.compass.youqi.ui.mine.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.OrderProcessAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.OrderBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.AppUtils;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class OrderDetailAct extends BaseAppActivity {
    @Bind(R.id.rvOrderProcess)
    RecyclerView rvOrderProcess;
    @Bind(R.id.llCanorPay)
    LinearLayout llCanorPay;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    HttpImageView ivLogo;
    TextView tvProjectName;
    TextView tvPrice;
    TextView tvCount;
    TextView tvTotalMoney;
    TextView tvPhone;
    TextView tvOrderCode;
    TextView tvTime;
    TextView tvNode;
    TextView tvState;
    TextView tvSubsricbe;
    private OrderProcessAdapter adapter;
    private List<BaseZnzBean> dataList = new ArrayList<>();
    private OrderBean bean;

    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_order_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("订单详情");
    }

    @Override
    protected void initializeView() {
        View header = LayoutInflater.from(context).inflate(R.layout.header_order_detail, null);
        ivLogo = bindViewById(header, R.id.ivLogo);
        tvProjectName = bindViewById(header, R.id.tvProjectName);
        tvPrice = bindViewById(header, R.id.tvPrice);
        tvCount = bindViewById(header, R.id.tvCount);
        tvTotalMoney = bindViewById(header, R.id.tvTotalMoney);
        tvPhone = bindViewById(header, R.id.tvPhone);
        tvOrderCode = bindViewById(header, R.id.tvOrderCode);
        tvTime = bindViewById(header, R.id.tvTime);
        tvNode = bindViewById(header, R.id.tvNode);
        tvState = bindViewById(header, R.id.tvState);
        tvSubsricbe = bindViewById(header, R.id.tvSubsricbe);

        adapter = new OrderProcessAdapter(dataList);
        adapter.addHeaderView(header);
        rvOrderProcess.setLayoutManager(new LinearLayoutManager(activity));
        rvOrderProcess.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("orderSerial", id);
        mModel.requestOrderDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSON.parseObject(responseOriginal.getString("data"), OrderBean.class);
                if (bean != null) {
                    ivLogo.loadHttpImage(bean.getProductMobileImage());
                    mDataManager.setValueToView(tvProjectName, bean.getProductName());
                    mDataManager.setValueToView(tvPrice, "￥" + bean.getProductPrice());
                    mDataManager.setValueToView(tvCount, "x" + bean.getNum());
                    mDataManager.setValueToView(tvTotalMoney, AppUtils.getInstance(context).getMoney(bean.getProductPrice(), bean.getNum()));
                    mDataManager.setValueToView(tvPhone, bean.getBuyerTel());
                    mDataManager.setValueToView(tvOrderCode, bean.getOrderSerial());
                    mDataManager.setValueToView(tvNode, bean.getNote());
                    mDataManager.setValueToView(tvTime, TimeUtils.millis2String(StringUtil.stringToLong(bean.getAddTime()) * 1000, "yyyy.MM.dd HH:mm:ss"));
                    switch (bean.getState()) {
                        case "1":
                            mDataManager.setValueToView(tvState, "待付款");
                            mDataManager.setValueToView(tvSubsricbe, "你已提交了订单，待付款");
                            mDataManager.setViewVisibility(llCanorPay, true);
                            mDataManager.setViewVisibility(tvSubmit, false);
                            break;
                        case "2":
                            mDataManager.setValueToView(tvState, "待服务");
                            mDataManager.setValueToView(tvSubsricbe, "你已提交了订单，待服务");
                            mDataManager.setViewVisibility(llCanorPay, false);
                            mDataManager.setViewVisibility(tvSubmit, true);
                            tvSubmit.setTextColor(mDataManager.getColor(R.color.text_color));
                            tvSubmit.setBackgroundColor(mDataManager.getColor(R.color.background));
                            tvSubmit.setClickable(false);
                            tvSubmit.setEnabled(false);
                            break;
                        case "3":
                            mDataManager.setValueToView(tvState, "待确认服务结果");
                            mDataManager.setValueToView(tvSubsricbe, "你已提交了订单，待确认服务");
                            mDataManager.setViewVisibility(llCanorPay, false);
                            mDataManager.setViewVisibility(tvSubmit, true);
                            tvSubmit.setTextColor(mDataManager.getColor(R.color.white));
                            tvSubmit.setBackgroundColor(mDataManager.getColor(R.color.red));
                            break;
                        case "4":
                            mDataManager.setValueToView(tvState, "已取消");
                            if (StringUtil.isBlank(bean.getAdminid())) {
                                mDataManager.setValueToView(tvSubsricbe, "你的订单已被系统取消，请联系客服退款。");
                            } else {
                                if (StringUtil.stringToDouble(bean.getAdminid()) > 0) {
                                    mDataManager.setValueToView(tvSubsricbe, "你的订单已取消");
                                } else {
                                    mDataManager.setValueToView(tvSubsricbe, "你的订单已被系统取消，请联系客服退款。");
                                }
                            }
                            mDataManager.setViewVisibility(llCanorPay, false);
                            mDataManager.setViewVisibility(tvSubmit, false);
                            break;
                        case "5":
                            mDataManager.setValueToView(tvState, "已完成");
                            mDataManager.setValueToView(tvSubsricbe, "你的订单已完成");
                            mDataManager.setViewVisibility(llCanorPay, false);
                            mDataManager.setViewVisibility(tvSubmit, false);
                            break;
                    }
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

    @OnClick({R.id.tvCancal, R.id.tvPay, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvCancal:
                new UIAlertDialog(activity)
                        .builder()
                        .setMsg("确定取消订单")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", v2 -> {
                            updateOrder("cancel");
                        })
                        .show();

                break;
            case R.id.tvPay:

                break;
            case R.id.tvSubmit:
                updateOrder("confirm");
                break;
        }
    }

    private void updateOrder(String optype) {
        Map<String, String> params = new HashMap<>();
        params.put("optype", optype);
        params.put("orderSerial", bean.getOrderSerial());
        mModel.requestUpdateOrder(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                switch (optype) {
                    case "cancel":
                        mDataManager.showToast("订单取消成功");
                        break;
                    case "confirm":
                        mDataManager.showToast("确认服务成功");
                        break;
                }
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_ORDER));
                finish();
            }
        });
    }
}
