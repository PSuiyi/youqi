package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppFragment;
import com.mzk.compass.youqi.bean.UserBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.common.WebViewAct;
import com.mzk.compass.youqi.ui.mine.account.AccountManagerAct;
import com.mzk.compass.youqi.ui.mine.article.ArticleTabAct;
import com.mzk.compass.youqi.ui.mine.identify.IdentifyManagerAct;
import com.mzk.compass.youqi.ui.mine.identify.company.IdentifyProcessAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.mzk.compass.youqi.ui.mine.order.OrderTabAct;
import com.mzk.compass.youqi.ui.mine.state.StateListAct;
import com.mzk.compass.youqi.ui.mine.vip.VipCenterAct;
import com.mzk.compass.youqi.ui.setting.SettingAct;
import com.mzk.compass.youqi.utils.AppUtils;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    @Bind(R.id.tvNickName)
    TextView tvNickName;
    @Bind(R.id.tvCompany)
    TextView tvCompany;
    @Bind(R.id.ivHeader)
    HttpImageView ivHeader;
    @Bind(R.id.llInfo)
    LinearLayout llInfo;
    @Bind(R.id.ivMessage)
    ImageView ivMessage;
    @Bind(R.id.tvYongjin)
    TextView tvYongjin;
    @Bind(R.id.tvBalance)
    TextView tvBalance;
    @Bind(R.id.tvMessageCount)
    TextView tvMessageCount;
    @Bind(R.id.ivVip)
    ImageView ivVip;
    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;
    @Bind(R.id.znzRemind)
    ZnzRemind znzRemind;
    @Bind(R.id.llNetworkStatus)
    LinearLayout llNetworkStatus;
    @Bind(R.id.tvDot)
    TextView tvDot;
    private UserBean bean;

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
                .withEnableHide(true)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(IdentifyProcessAct.class);
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
                .withEnableArraw(true)
                .withTextSize(14)
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    gotoActivity(VipCenterAct.class);
                })
                .build());
        if (!StringUtil.isBlank(mDataManager.readTempData(Constants.User.USERTYPE)) && mDataManager.readTempData(Constants.User.USERTYPE).equals("1")) {
            rowDescriptionList.add(new ZnzRowDescription.Builder()
                    .withIconResId(R.mipmap.wodedongtai)
                    .withTitle("我的动态")
                    .withEnableArraw(true)
                    .withTextSize(14)
                    .withTitleColor(mDataManager.getColor(R.color.text_color))
                    .withOnClickListener(v -> {
                        gotoActivity(StateListAct.class);
                    })
                    .build());
        }
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
                    gotoActivity(AccountManagerAct.class);
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
        setData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Map<String, String> params = new HashMap<>();
        mModel.requestUserDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    bean = JSON.parseObject(responseOriginal.getString("data"), UserBean.class);

                    mDataManager.setValueToView(tvYongjin, bean.getYongjin(), "0");
                    mDataManager.setValueToView(tvBalance, bean.getBalance(), "0");
                    if (StringUtil.isBlank(bean.getMsgTotal()) || bean.getMsgTotal().equals("0")) {
                        mDataManager.setViewVisibility(tvDot, false);
                    } else {
                        mDataManager.setViewVisibility(tvDot, true);
                        mDataManager.setValueToView(tvDot, bean.getMsgTotal(), "0");
                    }

                    AppUtils.getInstance(context).saveUserData(bean);
                    setData();
                }
            }
        });
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestMessageCount(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (StringUtil.isBlank(responseOriginal.getString("data")) || responseOriginal.getString("data").equals("0")) {
                    tvMessageCount.setVisibility(View.GONE);
                } else {
                    tvMessageCount.setVisibility(View.VISIBLE);
                    tvMessageCount.setText(responseOriginal.getString("data"));
                }
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    private void setData() {
        mDataManager.setValueToView(tvNickName, AppUtils.getInstance(context).getUserName());
        mDataManager.setValueToView(tvCompany, AppUtils.getInstance(context).getCompanyName(), "");
        if (StringUtil.isBlank(mDataManager.readTempData(Constants.User.ISVIP))) {
            mDataManager.setViewVisibility(ivVip, false);
        } else {
            if (mDataManager.readTempData(Constants.User.ISVIP).equals("1")) {
                mDataManager.setViewVisibility(ivVip, true);
                rowDescriptionList.get(5).setValue("VIP会员" + TimeUtils.getFormatTime(mDataManager.readTempData(Constants.User.VIPTIME), "yyyy-MM-dd") + "到期");
            } else {
                mDataManager.setViewVisibility(ivVip, false);
                rowDescriptionList.get(5).setValue("");
            }
            commonRowGroup.notifyDataChanged(rowDescriptionList);
        }
        ivHeader.loadHeaderImage(mDataManager.readTempData(Constants.User.AVATAR));
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

    @OnClick({R.id.llInfo, R.id.ivMessage, R.id.llOne})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llInfo:
                gotoActivity(MineInfoAct.class);
                break;
            case R.id.ivMessage:
                gotoActivity(MessageTabAct.class);
                break;
            case R.id.llOne:
                Bundle bundle = new Bundle();
                bundle.putString("title", "工商财税");
                bundle.putString("url", "http://oa.njyzt.com:98/app/index.aspx?token=c0UmdXvfPPSzXHZf663b02f3lBVjZWlGTtsmH5&mobile=" + mDataManager.readTempData(ZnzConstants.ACCOUNT));
                mDataManager.gotoActivity(WebViewAct.class, bundle);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_USERINFO:
            case EventTags.REFRESH_PAY_VIP:
                loadDataFromServer();
                break;
        }
    }
}
