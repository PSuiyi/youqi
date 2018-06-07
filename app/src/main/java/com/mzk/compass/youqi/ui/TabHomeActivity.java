package com.mzk.compass.youqi.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.help.HelpFrag;
import com.mzk.compass.youqi.ui.home.HomeFrag;
import com.mzk.compass.youqi.ui.login.BindAct;
import com.mzk.compass.youqi.ui.login.LoginAct;
import com.mzk.compass.youqi.ui.mine.MineFrag;
import com.mzk.compass.youqi.ui.news.NewsFrag;
import com.mzk.compass.youqi.ui.publish.PublishAct;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.znz.compass.znzlibray.common.DataManager;
import com.znz.compass.znzlibray.eventbus.BaseEvent;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.FragmentUtil;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


public class TabHomeActivity extends BaseAppActivity {
    @Bind(R.id.main_container)
    LinearLayout mainContainer;
    @Bind(R.id.radioButton1)
    RadioButton radioButton1;
    @Bind(R.id.radioButton2)
    RadioButton radioButton2;
    @Bind(R.id.radioButton3)
    RadioButton radioButton3;
    @Bind(R.id.radioButton4)
    RadioButton radioButton4;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.llAdd)
    LinearLayout llAdd;
    private HomeFrag homeFragment;
    private HelpFrag meetingFragment;
    private NewsFrag groupFragment;
    private MineFrag mineFragment;
    private FragmentUtil fragmentUtil;
    private FragmentManager fragmentManager;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.activity_tab};
    }

    @Override
    protected void initializeVariate() {
    }

    @Override
    protected void initializeNavigation() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void initializeView() {
        fragmentUtil = new FragmentUtil();
        fragmentManager = getSupportFragmentManager();

        if (homeFragment == null) {
            homeFragment = new HomeFrag();
        }
        fragmentManager.beginTransaction().add(R.id.main_container, homeFragment).commit();
        fragmentUtil.mContent = homeFragment;
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick({R.id.radioButton1, R.id.radioButton2, R.id.llAdd, R.id.radioButton3, R.id.radioButton4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButton1:
                if (homeFragment == null) {
                    homeFragment = new HomeFrag();
                }
                fragmentUtil.switchContent(homeFragment, R.id.main_container, fragmentManager);
                break;
            case R.id.radioButton2:
                if (meetingFragment == null) {
                    meetingFragment = new HelpFrag();
                }
                fragmentUtil.switchContent(meetingFragment, R.id.main_container, fragmentManager);
                break;
            case R.id.llAdd:
                Map<String, String> params = new HashMap<>();
                mModel.requestCheckIdentify(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                        if (!StringUtil.isBlank(json.getString("usertype"))) {
                            String status = json.getString("usertype");
                            if (!StringUtil.isBlank(status) && status.equals("1")) {
                                PopupWindowManager.getInstance(activity).showPublish(llAdd, new PopupWindowManager.OnPopupWindowClickListener() {
                                    @Override
                                    public void onPopupWindowClick(String type, String[] values) {

                                    }
                                });
                            } else {
                                mDataManager.gotoActivity(PublishAct.class);
                            }
                        } else {
                            mDataManager.gotoActivity(PublishAct.class);
                        }
                    }
                });
                break;
            case R.id.radioButton3:
                if (groupFragment == null) {
                    groupFragment = new NewsFrag();
                }
                fragmentUtil.switchContent(groupFragment, R.id.main_container, fragmentManager);
                break;
            case R.id.radioButton4:
                if (mineFragment == null) {
                    mineFragment = new MineFrag();
                }
                fragmentUtil.switchContent(mineFragment, R.id.main_container, fragmentManager);
                break;
        }
    }

    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            DataManager.getInstance(this).showToast(R.string.app_exit_again);
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
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
    public void onMessageEvent(BaseEvent event) {
        if (event.getFlag() == 0x90000) {
            mDataManager.logout(activity, LoginAct.class);
        }
        if (event.getFlag() == 0x90001) {
            gotoActivity(BindAct.class);
        }
    }
}
