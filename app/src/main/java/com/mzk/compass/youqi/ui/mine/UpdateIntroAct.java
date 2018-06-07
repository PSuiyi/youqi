package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/18.
 */

public class UpdateIntroAct extends BaseAppActivity {
    @Bind(R.id.etContent)
    EditText etContent;
    private String value;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_update_intro, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("value")) {
            value = getIntent().getStringExtra("value");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("个人简介");
    }

    @Override
    protected void initializeView() {
        etContent.setText(value);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
            mDataManager.showToast("请输入职务");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("key", "introduce");
        params.put("value", mDataManager.getValueFromView(etContent));
        mModel.requestUpdateUserInfo(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.saveTempData(Constants.User.INTRODUCE, mDataManager.getValueFromView(etContent));
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_USERINFO));
                finish();
            }
        });
    }
}
