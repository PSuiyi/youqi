package com.mzk.compass.youqi.ui.mine.identify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MenberAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.bean.BaseZnzBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MemberMangerAct extends BaseAppActivity {
    @Bind(R.id.rvMember)
    RecyclerView rvMember;
    private MenberAdapter adapter;
    private List<BaseZnzBean> dataList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_manger_member, 1};
    }

    @Override
    protected void initializeVariate() {
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
        dataList.add(new BaseZnzBean());
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("成员管理");
    }

    @Override
    protected void initializeView() {
        adapter = new MenberAdapter(dataList);
        rvMember.setLayoutManager(new LinearLayoutManager(activity));
        rvMember.setAdapter(adapter);
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

    @OnClick({R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSubmit:
                Bundle bundle = new Bundle();
                bundle.putString("from", "创建");
                gotoActivity(MemberDetailAct.class, bundle);
                break;
        }
    }
}
