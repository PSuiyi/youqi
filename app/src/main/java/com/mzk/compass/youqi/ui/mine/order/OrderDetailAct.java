package com.mzk.compass.youqi.ui.mine.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.OrderProcessAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class OrderDetailAct extends BaseAppActivity {
    @Bind(R.id.rvOrderProcess)
    RecyclerView rvOrderProcess;

    private OrderProcessAdapter adapter;
    private List<BaseZnzBean> dataList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_order_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("订单详情");
    }

    @Override
    protected void initializeView() {
        View header = LayoutInflater.from(context).inflate(R.layout.header_order_detail, null);
        adapter = new OrderProcessAdapter(dataList);
        adapter.addHeaderView(header);
        rvOrderProcess.setLayoutManager(new LinearLayoutManager(activity));
        rvOrderProcess.setAdapter(adapter);
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
}
