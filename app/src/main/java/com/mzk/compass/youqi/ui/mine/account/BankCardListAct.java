package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.BankCardAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;

/**
 * Created by Administrator on 2018/4/26.
 */

public class BankCardListAct extends BaseAppListActivity {
    private BankCardAdapter adapter;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_bank_card, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("绑定银行卡");
        znzToolBar.setNavRightText("提交");
        znzToolBar.setOnNavRightClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("from", "绑定银行卡");
            gotoActivity(CheckPhoneAct.class, bundle);
        });
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new BankCardAdapter(dataList);
        rvRefresh.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected void onRefreshSuccess(String response) {

    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
