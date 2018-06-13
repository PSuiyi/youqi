package com.mzk.compass.youqi.ui.common;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CityAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.CityBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class HomeCityListAct extends BaseAppActivity {

    @Bind(R.id.rvCommonRefresh)
    RecyclerView rvCommonRefresh;
    private LinearLayout llCity;

    private List<CityBean> dataList = new ArrayList<>();
    private CityAdapter adapter;

    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout_withnav, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("城市选择");
    }


    @Override
    protected void initializeView() {
        adapter = new CityAdapter(dataList);
        rvCommonRefresh.setAdapter(adapter);
        rvCommonRefresh.setLayoutManager(new LinearLayoutManager(activity));
        adapter.setOnItemClickListener((adapter, view, position) -> {
            new UIAlertDialog(activity)
                    .builder()
                    .setMsg("目前只开通南京区服务,其他城市敬请期待！")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", v2 -> {

                    })
                    .show();
        });


        View header = View.inflate(activity, R.layout.header_city, null);
        llCity = bindViewById(header, R.id.llCity);
        llCity.setOnClickListener(v -> {
            finish();
        });
        adapter.addHeaderView(header);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "0");
        mModel.requestCityList(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                dataList.addAll(JSONArray.parseArray(responseOriginal.getString("data"), CityBean.class));
                for (CityBean cityBean : dataList) {
                    if (cityBean.getName().equals("南京市")) {
                        dataList.remove(cityBean);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
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
