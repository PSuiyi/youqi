package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppFragment;
import com.mzk.compass.youqi.ui.mine.article.ArticleTabAct;
import com.mzk.compass.youqi.ui.mine.identify.IdentifyManagerAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.mzk.compass.youqi.ui.mine.order.OrderTabAct;
import com.mzk.compass.youqi.ui.mine.vip.RechargeAct;
import com.mzk.compass.youqi.ui.mine.vip.VipCenterAct;
import com.mzk.compass.youqi.ui.setting.SettingAct;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/3/29 2018
 * User： PSuiyi
 * Description：
 */
public class MineFrag extends BaseAppFragment {
    @Bind(R.id.commonRowGroup)
    ZnzRowGroupView commonRowGroup;

    private ArrayList<ZnzRowDescription> rowDescriptionList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_mine};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected void initializeView() {
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.wodexiangmu)
                .withTitle("我的项目")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(MineProjectTabAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.wodedingdan)
                .withTitle("我的订单")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(OrderTabAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.jinduchaxun)
                .withTitle("进度查询")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.wodeshoucang)
                .withTitle("我的收藏")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(MineFavTabAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.qiyerenzheng)
                .withTitle("认证管理")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(IdentifyManagerAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.huiyuanzhongxin)
                .withTitle("会员中心")
                .withValue("VIP会员2010-11-04到期")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(RechargeAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.wodetougao)
                .withTitle("我的投稿")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(ArticleTabAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.zhanghuguanli)
                .withTitle("账户管理")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.zhuanshukefu)
                .withTitle("专属客服")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(CustomerServiceAct.class);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withIconResId(R.mipmap.xitongshezhi)
                .withTitle("系统设置")
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(SettingAct.class);
                })
                .build());
        commonRowGroup.notifyDataChanged(rowDescriptionList);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.llInfo, R.id.ivMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llInfo:
                gotoActivity(MineInfoAct.class);
                break;
            case R.id.ivMessage:
                gotoActivity(MessageTabAct.class);
                break;
        }
    }
}
