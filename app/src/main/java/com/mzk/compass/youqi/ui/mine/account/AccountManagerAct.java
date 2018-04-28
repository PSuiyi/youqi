package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/4/26.
 */

public class AccountManagerAct extends BaseAppActivity {
    @Bind(R.id.commonRowGroup)
    ZnzRowGroupView commonRowGroup;

    private ArrayList<ZnzRowDescription> rowDescriptionList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_account_manager, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("账户管理");
    }

    @Override
    protected void initializeView() {
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("修改密码")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(UpdatePsdAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("手机")
                .withEnableArraw(true)
                .withTextSize(14)
                .withValue(" 18812345678")
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "修改手机号");
                    gotoActivity(CheckPhoneAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("银行卡")
                .withEnableArraw(true)
                .withTextSize(14)
                .withValue("已绑定")
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(BankCardListAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("身份认证")
                .withEnableArraw(true)
                .withTextSize(14)
                .withValue("去绑定")
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(IdentifyAct.class);
                })
                .build());
        commonRowGroup.notifyDataChanged(rowDescriptionList);
    }

    @Override
    protected void loadDataFromServer() {

    }
}
