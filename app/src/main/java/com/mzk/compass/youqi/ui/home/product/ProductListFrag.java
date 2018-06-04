package com.mzk.compass.youqi.ui.home.product;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.utils.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/3/31 2018
 * User： PSuiyi
 * Description：
 */
public class ProductListFrag extends BaseAppListFragment {
    private String from;
    private String keywords;
    private String cateId;

    public static ProductListFrag newInstance(String from) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        ProductListFrag fragment = new ProductListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ProductListFrag newInstance(String from, String keywords) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        bundle.putString("keywords", keywords);
        ProductListFrag fragment = new ProductListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout};
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
        return new GridLayoutManager(activity, 2);
    }

    @Override
    protected void initializeView() {
        adapter = new ProductAdapter(dataList);
        rvRefresh.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        if (!StringUtil.isBlank(cateId)) {
            params.put("cateId", cateId);
        }
        switch (from) {
            case "搜索":
                params.put("searchKey", keywords);
                return mModel.requestProductList(params);
            case "收藏":
                params.put("type", "4");
                return mModel.requestCollect(params);
            case "商品服务":
                return mModel.requestProductList(params);
        }
        return null;
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSON.parseArray(responseJson.getString("productData"), ProductBean.class));
        adapter.notifyDataSetChanged();
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
            case EventTags.REFRESH_SEARCH_PRODUCT:
                keywords = event.getValue();
                resetRefresh();
                break;
            case EventTags.REFRESH_COLLECT_PRODUCT:
                resetRefresh();
                break;
        }
    }
}
