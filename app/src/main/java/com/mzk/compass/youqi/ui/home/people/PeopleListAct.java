package com.mzk.compass.youqi.ui.home.people;

import android.os.Bundle;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.znz.compass.znzlibray.utils.FragmentUtil;

/**
 * Date： 2018/4/8 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleListAct extends BaseAppActivity {
    private PeopleListFrag fragment;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_people_list, 2};
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
        znzToolBar.setOnSearchClickListener(view -> {
            mDataManager.saveTempData(Constants.SearchType.SEARCHTYPE, "1");
            Bundle bundle = new Bundle();
            bundle.putString("from", "搜索投资人");
            gotoActivity(SearchCommonAct.class, bundle);
        });
    }

    @Override
    protected void initializeView() {
        fragment = PeopleListFrag.newInstance("投资人");
        FragmentUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container);
    }

    @Override
    protected void loadDataFromServer() {

    }
}
