package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/18.
 */

public class UpdateInfoAct extends BaseAppActivity {
    @Bind(R.id.etContent)
    EditTextWithDel etContent;
    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_update_info, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
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
        if (StringUtil.isBlank(from)) {
            return;
        }
        switch (from) {
            case "姓名":
                etContent.setHint("请输入姓名");
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
