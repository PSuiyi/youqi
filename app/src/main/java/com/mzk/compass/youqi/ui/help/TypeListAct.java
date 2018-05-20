package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.TypeLeftAdapter;
import com.mzk.compass.youqi.adapter.TypeRightAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/3/31 2018
 * User： PSuiyi
 * Description：
 */
public class TypeListAct extends BaseAppActivity {
    @Bind(R.id.rvLeft)
    RecyclerView rvLeft;
    @Bind(R.id.rvRight)
    RecyclerView rvRight;

    private TypeLeftAdapter leftAdapter;
    private TypeRightAdapter rightAdapter;
    private List<BaseZnzBean> leftList = new ArrayList<>();
    private List<BaseZnzBean> rightList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_type_list, 2};
    }

    @Override
    protected void initializeVariate() {
        leftList.add(new BaseZnzBean());
        leftList.add(new BaseZnzBean());
        leftList.add(new BaseZnzBean());
        leftList.add(new BaseZnzBean());
        leftList.add(new BaseZnzBean());
        leftList.add(new BaseZnzBean());

        rightList.add(new BaseZnzBean());
        rightList.add(new BaseZnzBean());
        rightList.add(new BaseZnzBean());
        rightList.add(new BaseZnzBean());
        rightList.add(new BaseZnzBean());
        rightList.add(new BaseZnzBean());
        rightList.add(new BaseZnzBean());
        rightList.add(new BaseZnzBean());
    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected void initializeView() {
        rvLeft.setLayoutManager(new LinearLayoutManager(activity));
        leftAdapter = new TypeLeftAdapter(leftList);
        rvLeft.setAdapter(leftAdapter);

        rvRight.setLayoutManager(new LinearLayoutManager(activity));
        rightAdapter = new TypeRightAdapter(rightList);
        rvRight.setAdapter(rightAdapter);
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
