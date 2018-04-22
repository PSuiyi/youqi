package com.mzk.compass.youqi.ui.help;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.ui.home.ProductListFrag;
import com.znz.compass.znzlibray.utils.FragmentUtil;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class ProductListAct extends BaseAppActivity {

    private ProductListFrag fragment;

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
        fragment = new ProductListFrag();
        FragmentUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container);
    }

    @Override
    protected void loadDataFromServer() {

    }
}
