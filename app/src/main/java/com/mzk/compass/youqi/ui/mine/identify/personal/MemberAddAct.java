package com.mzk.compass.youqi.ui.mine.identify.personal;

import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.MemberBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MemberAddAct extends BaseAppActivity {
    @Bind(R.id.etName)
    EditTextWithDel etName;
    @Bind(R.id.etPhone)
    EditTextWithDel etPhone;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    private String from;
    private MemberBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_member_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        if (getIntent().hasExtra("bean")) {
            bean = (MemberBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("成员管理");
    }

    @Override
    protected void initializeView() {
        switch (from) {
            case "创建":
                mDataManager.setValueToView(tvSubmit, "创建");
                break;
            case "编辑":
                mDataManager.setValueToView(tvSubmit, "保存");
                if (StringUtil.isBlank(bean.getUsername())) {
                    mDataManager.setValueToView(etName, bean.getInvitename(), "");
                } else {
                    mDataManager.setValueToView(etName, bean.getUsername(), "");
                }
                mDataManager.setValueToView(etPhone, bean.getTel(), "");
                if (bean.getState().equals("3")) {
                    mDataManager.setViewVisibility(tvSubmit, false);
                    etName.setFocusable(false);
                    etPhone.setFocusable(false);
                } else {
                    mDataManager.setViewVisibility(tvSubmit, true);
                    etName.setFocusable(true);
                    etPhone.setFocusable(true);
                }
                break;
        }
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick(R.id.tvSubmit)
    public void onViewClicked() {
        if (StringUtil.isBlank(mDataManager.getValueFromView(etName))) {
            mDataManager.showToast("请输入姓名");
            return;
        }
        if (StringUtil.isBlank(mDataManager.getValueFromView(etName))) {
            mDataManager.showToast("请输入手机号");
            return;
        }
        if (!StringUtil.isMobile(mDataManager.getValueFromView(etPhone))) {
            mDataManager.showToast("请输入正确的手机号");
            return;
        }
        switch (from) {
            case "创建":
                addMember();
                break;
            case "编辑":
                updateMember();
                break;
        }
    }

    private void addMember() {
        Map<String, String> params = new HashMap<>();
        params.put("username", mDataManager.getValueFromView(etName));
        params.put("tel", mDataManager.getValueFromView(etPhone));
        mModel.requestMemberAdd(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("成员添加成功");
                EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_MEMBER));
                finish();
            }
        });
    }

    private void updateMember() {
        Map<String, String> params = new HashMap<>();
        params.put("username", mDataManager.getValueFromView(etName));
        params.put("tel", mDataManager.getValueFromView(etPhone));
        params.put("id", bean.getId());
        mModel.requestMemberUpdate(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("成员修改成功");
                EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_MEMBER));
                finish();
            }
        });
    }

}
