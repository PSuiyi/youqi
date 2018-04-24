package com.mzk.compass.youqi.ui.mine.identify.personal;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MemberDetailAct extends BaseAppActivity {
    @Bind(R.id.etName)
    EditTextWithDel etName;
    @Bind(R.id.etPhone)
    EditTextWithDel etPhone;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_member_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
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
                mDataManager.setValueToView(etName, "康抗抗");
                mDataManager.setValueToView(etPhone, "17745815546");
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
