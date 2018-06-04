package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.OrderBean;
import com.mzk.compass.youqi.ui.mine.order.OrderDetailAct;
import com.mzk.compass.youqi.utils.AppUtils;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvOrderCode)
    TextView tvOrderCode;
    @Bind(R.id.tvState)
    TextView tvState;
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
    @Bind(R.id.tvCancal)
    TextView tvCancal;
    @Bind(R.id.tvPay)
    TextView tvPay;
    private AppUtils appUtils;

    public OrderAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_order, dataList);
        appUtils = AppUtils.getInstance(mContext);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean bean) {
        setOnItemClickListener(this);
        mDataManager.setValueToView(tvOrderCode, bean.getOrderSerial());
        mDataManager.setValueToView(tvProjectName, bean.getProductName());
        mDataManager.setValueToView(tvPrice, "￥" + bean.getProductPrice());
        mDataManager.setValueToView(tvCount, "x" + bean.getNum());
        ivLogo.loadHttpImage(bean.getProductMobileImage());
        helper.addOnClickListener(R.id.tvCancal);
        helper.addOnClickListener(R.id.tvPay);
        mDataManager.setValueToView(tvTotalMoney, appUtils.getMoney(bean.getProductPrice(), bean.getNum()));
        if (StringUtil.isBlank(bean.getState())) {
            mDataManager.setViewVisibility(tvState, false);
            mDataManager.setViewVisibility(tvCancal, false);
            mDataManager.setViewVisibility(tvPay, false);
        } else {
            mDataManager.setViewVisibility(tvState, true);
            switch (bean.getState()) {
                case "1":
                    mDataManager.setValueToView(tvState, "待付款");
                    mDataManager.setViewVisibility(tvCancal, true);
                    mDataManager.setViewVisibility(tvPay, true);
                    break;
                case "2":
                    mDataManager.setValueToView(tvState, "待服务");
                    mDataManager.setViewVisibility(tvCancal, false);
                    mDataManager.setViewVisibility(tvPay, false);
                    break;
                case "3":
                    mDataManager.setValueToView(tvState, "待确认服务结果");
                    mDataManager.setViewVisibility(tvCancal, false);
                    mDataManager.setViewVisibility(tvPay, false);
                    break;
                case "4":
                    mDataManager.setValueToView(tvState, "已取消");
                    mDataManager.setViewVisibility(tvCancal, false);
                    mDataManager.setViewVisibility(tvPay, false);
                    break;
                case "5":
                    mDataManager.setValueToView(tvState, "已完成");
                    mDataManager.setViewVisibility(tvCancal, false);
                    mDataManager.setViewVisibility(tvPay, false);
                    break;
                default:
                    mDataManager.setValueToView(tvState, "已取消");
                    mDataManager.setViewVisibility(tvCancal, false);
                    mDataManager.setViewVisibility(tvPay, false);
                    break;
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", bean.getOrderSerial());
        gotoActivity(OrderDetailAct.class, bundle);
    }
}
