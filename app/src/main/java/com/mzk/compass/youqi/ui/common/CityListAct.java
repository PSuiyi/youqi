package com.mzk.compass.youqi.ui.common;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CityAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.CityBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class CityListAct extends BaseAppActivity {

    @Bind(R.id.rvCommonRefresh)
    RecyclerView rvCommonRefresh;

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
        if (getIntent().hasExtra("list")) {
            List<CityBean> list = (List<CityBean>) getIntent().getSerializableExtra("list");
            dataList.addAll(list);
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
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (from.equals("企业认证")) {
                EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_CITY_IDENTIFY, dataList.get(position)));
                finish();
            }
        });
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
