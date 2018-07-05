package com.mzk.compass.youqi.ui.setting;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/26.
 */

public class AboutUsAct extends BaseAppActivity {


    @Bind(R.id.tvVer)
    TextView tvVer;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;
    @Bind(R.id.znzRemind)
    ZnzRemind znzRemind;
    @Bind(R.id.llNetworkStatus)
    LinearLayout llNetworkStatus;
    @Bind(R.id.wvDetail)
    WebViewWithProgress wvDetail;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_aboutus, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("关于我们");
    }

    @Override
    protected void initializeView() {
    }

    @Override
    protected void loadDataFromServer() {
        mDataManager.setValueToView(tvVer, "优企" + StringUtil.getVersionName(context) + "v");
        Map<String, String> params = new HashMap<>();
        mModel.requestAboutUs(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
//                    mDataManager.setValueToView(tvContent, json.getString("content"));
                    wvDetail.loadContent(json.getString("content"));
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
