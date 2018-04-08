package com.mzk.compass.youqi.ui.home.people;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.home.InvestorListFrag;
import com.znz.compass.znzlibray.utils.FragmentUtil;

/**
 * Date： 2018/4/8 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleListAct extends BaseAppActivity {
    private InvestorListFrag fragment;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_people_list, 2};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected void initializeView() {
        fragment = new InvestorListFrag();
        FragmentUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container);
    }

    @Override
    protected void loadDataFromServer() {

    }
}
