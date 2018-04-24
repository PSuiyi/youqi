package com.mzk.compass.youqi.ui.mine.identify.company;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.IndustryAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.IndustryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/24.
 */

public class IndustryAct extends BaseAppActivity {
    @Bind(R.id.rvIndustry)
    RecyclerView rvIndustry;

    private IndustryAdapter adapter;
    private List<IndustryBean> dataList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_industry, 1};
    }

    @Override
    protected void initializeVariate() {
        dataList.add(new IndustryBean("人工智能"));
        dataList.add(new IndustryBean("物联网"));
        dataList.add(new IndustryBean("云计算"));
        dataList.add(new IndustryBean("大消费"));
        dataList.add(new IndustryBean("游戏"));
        dataList.add(new IndustryBean("其他"));
        dataList.add(new IndustryBean("旅游"));
        dataList.add(new IndustryBean("智能硬件"));
        dataList.add(new IndustryBean("TMT"));
        dataList.add(new IndustryBean("餐饮服务"));
        dataList.add(new IndustryBean("本地生活"));
        dataList.add(new IndustryBean("共享经济"));
        dataList.add(new IndustryBean("艺术设计"));
        dataList.add(new IndustryBean("互联网"));
        dataList.add(new IndustryBean("汽车交通"));
        dataList.add(new IndustryBean("文化娱乐"));
        dataList.add(new IndustryBean("影视"));
        dataList.add(new IndustryBean("社交服务"));
        dataList.add(new IndustryBean("农业"));
        dataList.add(new IndustryBean("房产服务"));
        dataList.add(new IndustryBean("企业服务"));
        dataList.add(new IndustryBean("区块链"));
        dataList.add(new IndustryBean("移动互联网"));
        dataList.add(new IndustryBean("高端制造"));
        dataList.add(new IndustryBean("医疗健康"));
        dataList.add(new IndustryBean("社交网络"));
        dataList.add(new IndustryBean("O2O"));
        dataList.add(new IndustryBean("短视频"));
        dataList.add(new IndustryBean("环保"));
        dataList.add(new IndustryBean("旅游"));
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("所属行业");
        znzToolBar.setNavRightText("确定");
        znzToolBar.setOnNavRightClickListener(view -> {
            finish();
        });
    }

    @Override
    protected void initializeView() {
        adapter = new IndustryAdapter(dataList);
        rvIndustry.setLayoutManager(new GridLayoutManager(activity, 3));
        rvIndustry.setAdapter(adapter);
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
