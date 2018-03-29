package com.mzk.compass.youqi.ui.setting;

import android.os.Bundle;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/3/26 2018
 * User： PSuiyi
 * Description：
 */

public class SettingAct extends BaseAppActivity {
    @Bind(R.id.commonRowGroup)
    ZnzRowGroupView commonRowGroup;
    private ArrayList rowDescriptionList;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_setting, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("设置");
    }

    @Override
    protected void initializeView() {
        rowDescriptionList = new ArrayList<>();
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("消息通知")
                .withTextSize(14)
                .withEnableSwitch(true)
                .withIsSwitchChecked(true)
                .withOnCheckedChangeListener((buttonView, isChecked) -> {
                })
                .build());
        try {
            rowDescriptionList.add(new ZnzRowDescription.Builder()
                    .withTitle("清理缓存")
                    .withTextSize(14)
//                    .withValue(DataCleanManager.getTotalCacheSize(activity))
                    .withOnClickListener(v -> {
                    })
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("意见反馈")
                .withEnableArraw(true)
                .withTextSize(14)
                .withOnClickListener(v -> {
                    gotoActivity(FeedbackAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("关于我们")
                .withEnableArraw(true)
                .withTextSize(14)
                .withOnClickListener(v -> {
                    gotoActivity(AboutUsAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("我的账号")
                .withEnableArraw(true)
                .withTextSize(14)
                .withOnClickListener(v -> {
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("更换手机")
                .withEnableArraw(true)
                .withTextSize(14)
                .withOnClickListener(v -> {
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("更改密码")
                .withEnableArraw(true)
                .withTextSize(14)
                .withOnClickListener(v -> {
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("检查更新")
                .withTextSize(14)
                .withEnableArraw(true)
                .withValue("已是最新版本")
                .withOnClickListener(v -> {
                })
                .build());
        commonRowGroup.notifyDataChanged(rowDescriptionList);

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
