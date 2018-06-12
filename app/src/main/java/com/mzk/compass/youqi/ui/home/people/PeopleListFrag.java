package com.mzk.compass.youqi.ui.home.people;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.PeopleAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.FiltBean;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.umeng.socialize.UMShareAPI;
import com.znz.compass.umeng.share.ShareBean;
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
public class PeopleListFrag extends BaseAppListFragment<PeopleBean> {
    @Bind(R.id.tvOpt1)
    TextView tvOpt1;
    @Bind(R.id.tvOpt2)
    TextView tvOpt2;
    @Bind(R.id.llFilt1)
    LinearLayout llFilt1;
    @Bind(R.id.llFilt)
    LinearLayout llFilt;
    @Bind(R.id.tvTotal)
    TextView tvTotal;
    private String from;
    private String keywords;

    private List<FiltBean> filtList1 = new ArrayList<>();
    private List<FiltBean> filtList2 = new ArrayList<>();
    private String currentHangye;
    private String currentJieduan;

    public static PeopleListFrag newInstance(String from) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        PeopleListFrag fragment = new PeopleListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static PeopleListFrag newInstance(String from, String keywords) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        bundle.putString("keywords", keywords);
        PeopleListFrag fragment = new PeopleListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_list_people};
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
        adapter = new PeopleAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            PeopleBean bean = dataList.get(position);
            switch (view.getId()) {
                case R.id.ivShare:
                    ShareBean shareBean = new ShareBean();
                    shareBean.setUrl(Constants.share_url + "sharedetail/investor?id=" + bean.getId());
                    shareBean.setImageUrl(bean.getAvatar());
                    shareBean.setTitle(bean.getUserName());
                    shareBean.setDescription(bean.getName());
                    PopupWindowManager.getInstance(activity).showShare(view, activity, shareBean, (type, values) -> {

                    });
                    break;
                case R.id.ivFav:
                    if (bean.getIsCollected().equals("true")) {
                        Map<String, String> params = new HashMap<>();
                        params.put("type", "2");
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
                        params.put("type", "2");
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
            }
        });

        switch (from) {
            case "投资人":
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
        switch (from) {
            case "搜索":
                if (StringUtil.isBlank(keywords)) {
                    params.put("searchKey", "null");
                }else{
                    params.put("searchKey", keywords);
                }
                return mModel.requestPeopleList(params);
            case "收藏":
                params.put("type", "2");
                return mModel.requestCollect(params);
            case "投资人":
                return mModel.requestPeopleList(params);
        }
        return null;
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSONArray.parseArray(responseJson.getString("data"), PeopleBean.class));
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
            case EventTags.REFRESH_SEARCH_PEOPLE:
                keywords = event.getValue();
                resetRefresh();
                break;
            case EventTags.REFRESH_COLLECT_PEOPLE:
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

    @OnClick({R.id.tvOpt1, R.id.tvOpt2})
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
        }
    }
}
