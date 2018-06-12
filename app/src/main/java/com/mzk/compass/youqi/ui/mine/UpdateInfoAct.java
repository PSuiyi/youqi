package com.mzk.compass.youqi.ui.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.AppUtils;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/18.
 */

public class UpdateInfoAct extends BaseAppActivity {
    @Bind(R.id.etContent)
    EditTextWithDel etContent;
    private String from;
    private String value;
    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_update_info, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        if (getIntent().hasExtra("value")) {
            value = getIntent().getStringExtra("value");
        }
    }

    @Override
    protected void initializeNavigation() {
        if (StringUtil.isBlank(from)) {
            setTitleName(getResources().getString(R.string.app_name));
        } else {
            setTitleName(from);
        }
    }

    @Override
    protected void initializeView() {
        etContent.setFocusable(true);
        InputMethodManager imm = ( InputMethodManager ) etContent.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        imm.showSoftInput(etContent,InputMethodManager.SHOW_FORCED);
        if (StringUtil.isBlank(from)) {
            return;
        }
        switch (from) {
            case "姓名":
                etContent.setHint("请输入姓名");
                mDataManager.setValueToView(etContent, value, "");
                break;
            case "所属公司":
                etContent.setHint("编辑所属公司20字内");
                break;
            case "职务":
                etContent.setHint("编辑职务10字以内");
                break;
            case "电子邮箱":
                etContent.setHint("请输入电子邮箱");
                break;
            case "联系地址":
                etContent.setHint("请输入详细地址");
                break;
        }
        mDataManager.setValueToView(etContent, value, "");
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

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        Map<String, String> params = new HashMap<>();
        switch (from) {
            case "姓名":
                if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
                    mDataManager.showToast("请输入姓名");
                    return;
                }
                params.put("key", "username");
                break;
            case "所属公司":
                if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
                    mDataManager.showToast("请输入所属公司");
                    return;
                }
                params.put("key", "companyName");
                break;
            case "职务":
                if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
                    mDataManager.showToast("请输入职务");
                    return;
                }
                params.put("key", "duty");
                break;
            case "电子邮箱":
                if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
                    mDataManager.showToast("请输入电子邮箱");
                    return;
                }
                params.put("key", "email");
                break;
            case "联系地址":
                if (StringUtil.isBlank(mDataManager.getValueFromView(etContent))) {
                    mDataManager.showToast("请输入联系地址");
                    return;
                }
                params.put("key", "address");
                break;
        }
        params.put("value", mDataManager.getValueFromView(etContent));
        mModel.requestUpdateUserInfo(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                switch (from) {
                    case "姓名":
                        mDataManager.saveTempData(Constants.User.USERNAME, mDataManager.getValueFromView(etContent));
                        break;
                    case "所属公司":
                        mDataManager.saveTempData(Constants.User.CNAME, mDataManager.getValueFromView(etContent));
                        mDataManager.saveTempData(Constants.User.COMPANYNAME, mDataManager.getValueFromView(etContent));
                        break;
                    case "职务":
                        mDataManager.saveTempData(Constants.User.DUTY, mDataManager.getValueFromView(etContent));
                        break;
                    case "电子邮箱":
                        mDataManager.saveTempData(Constants.User.EMAIL, mDataManager.getValueFromView(etContent));
                        break;
                    case "联系地址":
                        mDataManager.saveTempData(Constants.User.ADDRESS, mDataManager.getValueFromView(etContent));
                        break;
                }
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_USERINFO));
                finish();
            }
        });
    }
}
