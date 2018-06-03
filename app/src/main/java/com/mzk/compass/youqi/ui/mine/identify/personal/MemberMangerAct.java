package com.mzk.compass.youqi.ui.mine.identify.personal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MemberAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.MemberBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MemberMangerAct extends BaseAppActivity {
    @Bind(R.id.rvMember)
    RecyclerView rvMember;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    private MemberAdapter adapter;
    private List<MemberBean> dataList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_manger_member, 1};
    }

    @Override
    protected void initializeVariate() {
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("成员管理");
    }

    @Override
    protected void initializeView() {
        adapter = new MemberAdapter(dataList);
        rvMember.setLayoutManager(new LinearLayoutManager(activity));
        rvMember.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("pageSize", "100");
        mModel.requestMemberList(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                    dataList.clear();
                    dataList.addAll(JSON.parseArray(json.getString("data"), MemberBean.class));
                    adapter.notifyDataSetChanged();
                    if (json.getString("canOperate").equals("false")) {
                        tvSubmit.setVisibility(View.GONE);
                    } else {
                        tvSubmit.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
                tvSubmit.setVisibility(View.GONE);
            }
        });
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_MEMBER:
                loadDataFromServer();
                break;
        }
    }
}
