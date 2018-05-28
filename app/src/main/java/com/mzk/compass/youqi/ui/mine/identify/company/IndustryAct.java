package com.mzk.compass.youqi.ui.mine.identify.company;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.IndustryAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.IndustryBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;

import org.greenrobot.eventbus.EventBus;

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
    private List<IndustryBean> selectList = new ArrayList<>();
    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_industry, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        if (getIntent().hasExtra("list")) {
            dataList = (List<IndustryBean>) getIntent().getSerializableExtra("list");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName(from);
        znzToolBar.setNavRightText("确定");
        znzToolBar.setOnNavRightClickListener(view -> {
            for (IndustryBean industryBean : dataList) {
                if (industryBean.isSelect()) {
                    selectList.add(industryBean);
                }
            }
            if (selectList.isEmpty()) {
                switch (from) {
                    case "关注行业":
                        mDataManager.showToast("请选择行业");
                        break;
                    case "投资轮次":
                        mDataManager.showToast("请选择轮次");
                        break;
                }
                return;
            }
            switch (from) {
                case "关注行业":
                    EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_HANYE, selectList));
                    break;
                case "投资轮次":
                    EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_LUNCI, selectList));
                    break;
            }
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
