package com.mzk.compass.youqi.ui.setting;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.login.LoginAct;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/3/26 2018
 * User： PSuiyi
 * Description：
 */

public class SettingAct extends BaseAppActivity {
    @Bind(R.id.commonRowGroup)
    ZnzRowGroupView commonRowGroup;
    @Bind(R.id.llLogout)
    LinearLayout llLogout;
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
                .withTitle("检查更新")
                .withTextSize(14)
                .withEnableArraw(true)
                .withValue("已是最新版本")
                .withOnClickListener(v -> {
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("用户反馈")
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

    @OnClick(R.id.llLogout)
    public void onViewClicked() {
        mDataManager.logout(activity, LoginAct.class);
    }
}
