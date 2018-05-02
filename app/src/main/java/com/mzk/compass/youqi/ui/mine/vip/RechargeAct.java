package com.mzk.compass.youqi.ui.mine.vip;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.RechargeAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/25.
 */

public class RechargeAct extends BaseAppActivity {
    @Bind(R.id.rvPrice)
    RecyclerView rvPrice;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    private RechargeAdapter adapter;
    private List<BaseZnzBean> dataList = new ArrayList<>();

    private PopupWindowManager popupWindowManager;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_recharge, 1};
    }

    @Override
    protected void initializeVariate() {
        popupWindowManager = PopupWindowManager.getInstance(context);
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
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
            for (BaseZnzBean bean : dataList) {
                bean.setSelect(false);
            }
            dataList.get(position).setSelect(true);
            adapter.notifyDataSetChanged();
        });
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

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        popupWindowManager.showPayWays(tvSubmit, null);
    }
}