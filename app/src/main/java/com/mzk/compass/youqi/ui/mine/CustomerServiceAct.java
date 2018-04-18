package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CustomerAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/18.
 * 专属客服
 */

public class CustomerServiceAct extends BaseAppActivity {

    @Bind(R.id.rvKefu)
    RecyclerView rvKefu;
    private CustomerAdapter adapter;
    private List<BaseZnzBean> dataList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_customer_service, 1};
    }

    @Override
    protected void initializeVariate() {
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("专属客服");
    }

    @Override
    protected void initializeView() {
        adapter = new CustomerAdapter(dataList);
        rvKefu.setLayoutManager(new LinearLayoutManager(activity));
        rvKefu.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tvPhone:
                    new UIAlertDialog(activity)
                            .builder()
                            .setTitle("确定拨打电话")
                            .setMsg("联系电话：15390870645")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", v2 -> {

                            })
                            .show();
                    break;
            }
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
}
