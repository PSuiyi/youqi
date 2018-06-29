package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                .withTextSize(16)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(UpdatePsdAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("手机")
                .withEnableArraw(true)
                .withTextSize(16)
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
                .withTextSize(16)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    gotoActivity(CardListAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("身份认证")
                .withEnableArraw(true)
                .withTextSize(16)
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
        Map<String, String> params = new HashMap<>();
        mModel.requestAccountManger(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                if (json != null) {
                    rowDescriptionList.get(1).setValue(StringUtil.getSignPhone(json.getString("tel")));
                    if (StringUtil.isBlank(json.getString("bankcard"))) {
                        rowDescriptionList.get(2).setValue("未绑定");
                    } else {
                        rowDescriptionList.get(2).setValue(json.getString("bankcard"));
                    }
                    if (StringUtil.isBlank(json.getString("state"))) {
                        rowDescriptionList.get(3).setValue("去认证");
                    } else {
                        if (json.getString("state").equals("1")) {
                            rowDescriptionList.get(3).setValue("已认证");
                        } else {
                            rowDescriptionList.get(3).setValue("去认证");
                        }
                    }
                    commonRowGroup.notifyDataChanged(rowDescriptionList);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_PHONE:
                loadDataFromServer();
                break;
            case EventTags.REFRESH_BANK:
                loadDataFromServer();
                break;
        }
    }
}
