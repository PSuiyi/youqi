package com.mzk.compass.youqi.ui.home.project;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.DocBean;

/**
 * Date： 2018/7/17 2018
 * User： PSuiyi
 * Description：
 */
public class DocAct extends BaseAppActivity {
    private DocBean docBean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_doc, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("docBean")) {
            docBean = (DocBean) getIntent().getSerializableExtra("docBean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("查看文档");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

    }
}
