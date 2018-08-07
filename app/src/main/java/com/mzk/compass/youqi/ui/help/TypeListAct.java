package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.TypeLeftAdapter;
import com.mzk.compass.youqi.adapter.TypeRightAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.CategoryBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<CategoryBean> leftList = new ArrayList<>();
    private List<CategoryBean> rightList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_type_list, 2};
    }

    @Override
    protected void initializeVariate() {
    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchHint("找服务");
        znzToolBar.setOnSearchClickListener(view -> {
            mDataManager.saveTempData(Constants.SearchType.SEARCHTYPE, "2");
            Bundle bundle = new Bundle();
            bundle.putString("from", "找商品");
            gotoActivity(SearchCommonAct.class, bundle);
        });
    }

    @Override
    protected void initializeView() {
        rvLeft.setLayoutManager(new LinearLayoutManager(activity));
        leftAdapter = new TypeLeftAdapter(leftList);
        rvLeft.setAdapter(leftAdapter);

        rvRight.setLayoutManager(new LinearLayoutManager(activity));
        rightAdapter = new TypeRightAdapter(rightList);
        rvRight.setAdapter(rightAdapter);

        leftAdapter.setOnItemClickListener((adapter, view, position) -> {
            rightList.clear();
            rightList.addAll(leftList.get(position).getSon());
            rightAdapter.setParentId(leftList.get(position).getId());
            rightAdapter.notifyDataSetChanged();
        });

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestCategory(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                leftList.clear();
                rightList.clear();
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    leftList.addAll(JSON.parseArray(responseOriginal.getString("data"), CategoryBean.class));
                    if (!leftList.isEmpty()) {
                        rightList.addAll(leftList.get(0).getSon());
                        rightAdapter.setParentId(leftList.get(0).getId());
                        rightAdapter.notifyDataSetChanged();
                    }
                    leftAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
