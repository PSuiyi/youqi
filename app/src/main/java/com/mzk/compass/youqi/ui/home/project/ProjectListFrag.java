package com.mzk.compass.youqi.ui.home.project;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProjectAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.FiltBean;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.PopupWindowManager;
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
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class ProjectListFrag extends BaseAppListFragment<ProjectBean> {
    @Bind(R.id.tvOpt1)
    TextView tvOpt1;
    @Bind(R.id.tvOpt2)
    TextView tvOpt2;
    @Bind(R.id.tvOpt3)
    TextView tvOpt3;
    @Bind(R.id.tvOpt4)
    TextView tvOpt4;
    @Bind(R.id.tvSort1)
    TextView tvSort1;
    @Bind(R.id.tvSort2)
    TextView tvSort2;
    @Bind(R.id.tvSort3)
    TextView tvSort3;
    @Bind(R.id.tvTotal)
    TextView tvTotal;
    @Bind(R.id.llFilt)
    LinearLayout llFilt;
    @Bind(R.id.llFilt1)
    LinearLayout llFilt1;
    private String from;

    private String keywords;
    private List<FiltBean> filtList1 = new ArrayList<>();
    private List<FiltBean> filtList2 = new ArrayList<>();
    private List<FiltBean> filtList3 = new ArrayList<>();
    private List<FiltBean> filtList4 = new ArrayList<>();
    private String currentHangye;
    private String currentJieduan;
    private String currentArea;
    private String currentMoney;

    public static ProjectListFrag newInstance(String from) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        ProjectListFrag fragment = new ProjectListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ProjectListFrag newInstance(String from, String keywords) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        bundle.putString("keywords", keywords);
        ProjectListFrag fragment = new ProjectListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_list_project};
    }

    @Override
    protected void initializeVariate() {
        if (getArguments() != null) {
            from = getArguments().getString("from");
        }
        if (getArguments() != null) {
            keywords = getArguments().getString("keywords");
        }
    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new ProjectAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        switch (from) {
            case "首页全部项目":
            case "搜索":
                mDataManager.setViewVisibility(llFilt, true);
                break;
        }
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestFiltList(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                filtList1.clear();
                filtList2.clear();
                filtList3.clear();
                filtList4.clear();

                if (!StringUtil.isBlank(responseObject.getString("trades"))) {
                    FiltBean filtBean = new FiltBean();
                    filtBean.setName("全部");
                    filtBean.setChecked(true);
                    filtList1.add(filtBean);
                    filtList1.addAll(JSONArray.parseArray(responseObject.getString("trades"), FiltBean.class));
                }
                if (!StringUtil.isBlank(responseObject.getString("rounds"))) {
                    FiltBean filtBean = new FiltBean();
                    filtBean.setChecked(true);
                    filtBean.setName("全部");
                    filtList2.add(filtBean);
                    filtList2.addAll(JSONArray.parseArray(responseObject.getString("rounds"), FiltBean.class));
                }
                if (!StringUtil.isBlank(responseObject.getString("provinceData"))) {
                    FiltBean filtBean = new FiltBean();
                    filtBean.setChecked(true);
                    filtBean.setName("全部");
                    filtList3.add(filtBean);
                    filtList3.addAll(JSONArray.parseArray(responseObject.getString("provinceData"), FiltBean.class));
                }
                if (!StringUtil.isBlank(responseObject.getString("turnovers"))) {
                    FiltBean filtBean = new FiltBean();
                    filtBean.setChecked(true);
                    filtBean.setName("全部");
                    filtList4.add(filtBean);
                    filtList4.addAll(JSONArray.parseArray(responseObject.getString("turnovers"), FiltBean.class));
                }
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        if (!StringUtil.isBlank(currentHangye)) {
            params.put("tradeId", currentHangye);
        }
        if (!StringUtil.isBlank(currentJieduan)) {
            params.put("roundId", currentJieduan);
        }
        if (!StringUtil.isBlank(currentArea)) {
            params.put("provinceId", currentArea);
        }
        if (!StringUtil.isBlank(currentMoney)) {
            params.put("turnoverId", currentMoney);
        }

        switch (from) {
            case "全部":
                params.put("state", "0");
                return mModel.requestProjectMineList(params);
            case "待审核":
                params.put("state", "1");
                return mModel.requestProjectMineList(params);
            case "已发布":
                params.put("state", "2");
                return mModel.requestProjectMineList(params);
            case "已拒绝":
                params.put("state", "4");
                return mModel.requestProjectMineList(params);
            case "已下架":
                params.put("state", "3");
                return mModel.requestProjectMineList(params);
            case "收藏":
                params.put("type", "1");
                return mModel.requestCollect(params);
            case "首页全部项目":
                return mModel.requestProjectList(params);
            case "搜索":
                params.put("searchKey", keywords);
                return mModel.requestProjectList(params);
            default:
                return mModel.requestProjectList(params);
        }
    }

    @Override
    protected void onRefreshSuccess(String response) {
        switch (from) {
            case "全部":
            case "待审核":
            case "已发布":
            case "已拒绝":
            case "已下架":
            case "收藏":
                JSONObject json = JSON.parseObject(response);
                dataList.addAll(JSON.parseArray(json.getString("data"), ProjectBean.class));
                break;
            case "首页全部项目":
            case "搜索":
                dataList.addAll(JSON.parseArray(responseJson.getString("projectData"), ProjectBean.class));
                break;
        }

        adapter.notifyDataSetChanged();

        mDataManager.setValueToView(tvTotal, "共有" + responseJson.getString("totalCount") + "条");
    }

    @Override
    protected void onRefreshFail(String error) {

    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
            case EventTags.REFRESH_SEARCH_PROJECT:
                keywords = event.getValue();
                resetRefresh();
                break;
            case EventTags.REFRESH_COLLECT_PROJECT:
                resetRefresh();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tvOpt1, R.id.tvOpt2, R.id.tvOpt3, R.id.tvOpt4, R.id.tvSort1, R.id.tvSort2, R.id.tvSort3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvOpt1:
                PopupWindowManager.getInstance(activity).showFilt(llFilt1, filtList1, (type, values) -> {
                    currentHangye = values[0];
                    if (values[1].equals("全部")) {
                        tvOpt1.setText("所属行业");
                    } else {
                        tvOpt1.setText(values[1]);
                    }
                    resetRefresh();
                });
                break;
            case R.id.tvOpt2:
                PopupWindowManager.getInstance(activity).showFilt(llFilt1, filtList2, (type, values) -> {
                    currentJieduan = values[0];
                    if (values[1].equals("全部")) {
                        tvOpt2.setText("获投阶段");
                    } else {
                        tvOpt2.setText(values[1]);
                    }
                    resetRefresh();
                });
                break;
            case R.id.tvOpt3:
                PopupWindowManager.getInstance(activity).showFilt(llFilt1, filtList3, (type, values) -> {
                    currentArea = values[0];
                    if (values[1].equals("全部")) {
                        tvOpt3.setText("所在地区");
                    } else {
                        tvOpt3.setText(values[1]);
                    }
                    resetRefresh();
                });
                break;
            case R.id.tvOpt4:
                PopupWindowManager.getInstance(activity).showFilt(llFilt1, filtList4, (type, values) -> {
                    currentMoney = values[0];
                    if (values[1].equals("全部")) {
                        tvOpt4.setText("营业额");
                    } else {
                        tvOpt4.setText(values[1]);
                    }
                    resetRefresh();
                });
                break;
            case R.id.tvSort1:
                break;
            case R.id.tvSort2:
                break;
            case R.id.tvSort3:
                break;
        }
    }
}
