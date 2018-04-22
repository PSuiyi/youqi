package com.mzk.compass.youqi.ui.home.project;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.home.ProjectListFrag;
import com.znz.compass.znzlibray.utils.FragmentUtil;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class ProjectListAct extends BaseAppActivity {

    private ProjectListFrag fragment;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_project_list, 2};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchLeftImage(R.drawable.topback);
        znzToolBar.setOnSearchLeftClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void initializeView() {
        fragment = new ProjectListFrag();
        FragmentUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container);
    }

    @Override
    protected void loadDataFromServer() {

    }
}
