package com.mzk.compass.youqi.ui.home.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProjectAdapter;
import com.mzk.compass.youqi.adapter.ProjectMineAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.FiltBean;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.umeng.socialize.UMShareAPI;
import com.znz.compass.umeng.share.ShareBean;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;
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
    @Bind(R.id.ivSortTop1)
    ImageView ivSortTop1;
    @Bind(R.id.ivSortBottom1)
    ImageView ivSortBottom1;
    @Bind(R.id.ivSortTop2)
    ImageView ivSortTop2;
    @Bind(R.id.ivSortBottom2)
    ImageView ivSortBottom2;
    @Bind(R.id.ivSortTop3)
    ImageView ivSortTop3;
    @Bind(R.id.ivSortBottom3)
    ImageView ivSortBottom3;
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
    private String order;
    private int order1;
    private int order2;
    private int order3;

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
        switch (from) {
            case "全部":
            case "待审核":
            case "已发布":
            case "已拒绝":
            case "已下架":
                adapter = new ProjectMineAdapter(dataList);
                break;
            case "收藏":
            case "首页全部项目":
            case "搜索":
            default:
                adapter = new ProjectAdapter(dataList);
                break;
        }
        rvRefresh.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            ProjectBean bean = dataList.get(position);
            switch (view.getId()) {
                case R.id.ivShare:
                    ShareBean shareBean = new ShareBean();
                    shareBean.setUrl(Constants.share_url + "sharedetail/index?id=" + bean.getId());
                    shareBean.setImageUrl(bean.getLogo());
                    shareBean.setTitle(bean.getName());
                    shareBean.setDescription(bean.getTitle());
                    PopupWindowManager.getInstance(activity).showShare(view, activity, shareBean, (type, values) -> {

                    });
                    break;
                case R.id.ivFav:
                    if (bean.getIsCollected().equals("true")) {
                        Map<String, String> params = new HashMap<>();
                        params.put("type", "1");
                        params.put("id", bean.getId());
                        mModel.requestCancalCollect(params, new ZnzHttpListener() {
                            @Override
                            public void onSuccess(JSONObject responseOriginal) {
                                super.onSuccess(responseOriginal);
//                                bean.setIsCollected("false");
//                                adapter.notifyDataSetChanged();
                                resetRefresh();
                            }
                        });
                    } else {
                        Map<String, String> params = new HashMap<>();
                        params.put("type", "1");
                        params.put("id", bean.getId());
                        mModel.requestAddCollect(params, new ZnzHttpListener() {
                            @Override
                            public void onSuccess(JSONObject responseOriginal) {
                                super.onSuccess(responseOriginal);
//                                bean.setIsCollected("true");
//                                adapter.notifyDataSetChanged();
                                resetRefresh();
                            }
                        });
                    }
                    break;
                case R.id.tvShenhe:
                    switch (bean.getState()) {
                        case "1"://1 表示 正在审核总
                            break;
                        case "2"://2 表示 审核通过已上线
                            updateProject(bean.getId(), "offsale");
                            break;
                        case "3"://3 表示已下线
                            updateProject(bean.getId(), "onsale");
                            break;
                        case "4"://4 表示审核拒绝
                            updateProject(bean.getId(), "delete");
                            break;
                    }
                    break;
            }
        });

        switch (from) {
            case "首页全部项目":
            case "搜索":
                mDataManager.setViewVisibility(llFilt, true);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);//完成回调
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

        if (!StringUtil.isBlank(order)) {
            params.put("order", order);
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
                if (StringUtil.isBlank(keywords)) {
                    params.put("searchKey", "null");
                } else {
                    params.put("searchKey", keywords);
                }
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
            case EventTags.REFRESH_PROJECT_STATE:
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
                switch (order1) {
                    case 0:
                        ivSortTop1.setImageResource(R.mipmap.shanglared);
                        ivSortBottom1.setImageResource(R.mipmap.xialagray);
                        order = "0";
                        order1 = 1;
                        break;
                    case 1:
                        ivSortTop1.setImageResource(R.mipmap.shanglagray);
                        ivSortBottom1.setImageResource(R.mipmap.xialared);
                        order = "1";
                        order1 = 2;
                        break;
                    case 2:
                        ivSortTop1.setImageResource(R.mipmap.shanglared);
                        ivSortBottom1.setImageResource(R.mipmap.xialagray);
                        order = "0";
                        order1 = 1;
                        break;
                }

                tvSort1.setTextColor(mDataManager.getColor(R.color.red));
                tvSort2.setTextColor(mDataManager.getColor(R.color.text_gray));
                tvSort3.setTextColor(mDataManager.getColor(R.color.text_gray));

                ivSortTop2.setImageResource(R.mipmap.shanglagray);
                ivSortBottom2.setImageResource(R.mipmap.xialagray);
                order2 = 0;

                ivSortTop3.setImageResource(R.mipmap.shanglagray);
                ivSortBottom3.setImageResource(R.mipmap.xialagray);
                order3 = 0;

                resetRefresh();
                break;
            case R.id.tvSort2:
                switch (order2) {
                    case 0:
                        ivSortTop2.setImageResource(R.mipmap.shanglared);
                        ivSortBottom2.setImageResource(R.mipmap.xialagray);
                        order = "2";
                        order2 = 1;
                        break;
                    case 1:
                        ivSortTop2.setImageResource(R.mipmap.shanglagray);
                        ivSortBottom2.setImageResource(R.mipmap.xialared);
                        order = "3";
                        order2 = 2;
                        break;
                    case 2:
                        ivSortTop2.setImageResource(R.mipmap.shanglared);
                        ivSortBottom2.setImageResource(R.mipmap.xialagray);
                        order = "2";
                        order2 = 1;
                        break;
                }

                tvSort1.setTextColor(mDataManager.getColor(R.color.text_gray));
                tvSort2.setTextColor(mDataManager.getColor(R.color.red));
                tvSort3.setTextColor(mDataManager.getColor(R.color.text_gray));

                ivSortTop1.setImageResource(R.mipmap.shanglagray);
                ivSortBottom1.setImageResource(R.mipmap.xialagray);
                order1 = 0;

                ivSortTop3.setImageResource(R.mipmap.shanglagray);
                ivSortBottom3.setImageResource(R.mipmap.xialagray);
                order3 = 0;

                resetRefresh();
                break;
            case R.id.tvSort3:
                switch (order3) {
                    case 0:
                        ivSortTop3.setImageResource(R.mipmap.shanglared);
                        ivSortBottom3.setImageResource(R.mipmap.xialagray);
                        order = "4";
                        order3 = 1;
                        break;
                    case 1:
                        ivSortTop3.setImageResource(R.mipmap.shanglagray);
                        ivSortBottom3.setImageResource(R.mipmap.xialared);
                        order = "5";
                        order3 = 2;
                        break;
                    case 2:
                        ivSortTop3.setImageResource(R.mipmap.shanglared);
                        ivSortBottom3.setImageResource(R.mipmap.xialagray);
                        order = "4";
                        order3 = 1;
                        break;
                }

                tvSort1.setTextColor(mDataManager.getColor(R.color.text_gray));
                tvSort2.setTextColor(mDataManager.getColor(R.color.text_gray));
                tvSort3.setTextColor(mDataManager.getColor(R.color.red));

                ivSortTop2.setImageResource(R.mipmap.shanglagray);
                ivSortBottom2.setImageResource(R.mipmap.xialagray);
                order2 = 0;

                ivSortTop1.setImageResource(R.mipmap.shanglagray);
                ivSortBottom1.setImageResource(R.mipmap.xialagray);
                order1 = 0;

                resetRefresh();
                break;
        }
    }

    private void updateProject(String id, String optype) {
        Map<String, String> params = new HashMap<>();
        params.put("projectId", id);
        params.put("optype", optype);
        mModel.requestUpdateProjectState(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                switch (optype) {
                    case "delete":
                        mDataManager.showToast("删除成功");
                        break;
                    case "onsale":
                        mDataManager.showToast("上架成功");
                        break;
                    case "offsale":
                        mDataManager.showToast("下架成功");
                        break;
                }
                EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_PROJECT_STATE));
            }
        });
    }
}
