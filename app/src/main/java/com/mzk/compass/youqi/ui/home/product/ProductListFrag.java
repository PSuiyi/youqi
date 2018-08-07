package com.mzk.compass.youqi.ui.home.product;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIActionSheetDialog;

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
 * Date： 2018/3/31 2018
 * User： PSuiyi
 * Description：
 */
public class ProductListFrag extends BaseAppListFragment {
    @Bind(R.id.tvSort1)
    TextView tvSort1;
    @Bind(R.id.ivSortTop1)
    ImageView ivSortTop1;
    @Bind(R.id.ivSortBottom1)
    ImageView ivSortBottom1;
    @Bind(R.id.tvSort2)
    TextView tvSort2;
    @Bind(R.id.ivSortTop2)
    ImageView ivSortTop2;
    @Bind(R.id.ivSortBottom2)
    ImageView ivSortBottom2;
    @Bind(R.id.llFilt)
    LinearLayout llFilt;
    @Bind(R.id.tvType)
    TextView tvType;
    private String from;
    private String keywords;
    private String cateId;
    private String cateIdParent;
    private String order;
    private int order1;
    private int order2;

    private List<MenuBean> menuBeanList = new ArrayList<>();

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
        cateIdParent = cateId;
    }

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_list_product};
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

        switch (from) {
            case "商品服务":
            case "搜索":
                mDataManager.setViewVisibility(llFilt, true);
                break;
        }
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params2 = new HashMap<>();
        mModel.requestCategory(params2, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                menuBeanList.clear();
                menuBeanList.addAll(JSONArray.parseArray(responseOriginal.getString("data"), MenuBean.class));
            }
        });
    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        if (!StringUtil.isBlank(cateId)) {
            params.put("cateId", cateId);
        }
        if (!StringUtil.isBlank(order)) {
            params.put("order", order);
        }
        switch (from) {
            case "搜索":
                if (StringUtil.isBlank(keywords)) {
                    params.put("searchKey", "null");
                } else {
                    params.put("searchKey", keywords);
                }
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
        if (from.equals("收藏")) {
            dataList.addAll(JSON.parseArray(responseJson.getString("data"), ProductBean.class));
        } else {
            dataList.addAll(JSON.parseArray(responseJson.getString("productData"), ProductBean.class));
        }
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

    @OnClick({R.id.tvSort1, R.id.tvSort2, R.id.tvType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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

                ivSortTop2.setImageResource(R.mipmap.shanglagray);
                ivSortBottom2.setImageResource(R.mipmap.xialagray);
                order2 = 0;

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

                ivSortTop1.setImageResource(R.mipmap.shanglagray);
                ivSortBottom1.setImageResource(R.mipmap.xialagray);
                order1 = 0;

                resetRefresh();
                break;
            case R.id.tvType:
                List<String> items = new ArrayList<>();
                List<MenuBean> menus = new ArrayList<>();
                items.add("全部");
                for (MenuBean menuBean : menuBeanList) {
                    if (menuBean.getId().equals(cateIdParent)) {
                        for (MenuBean bean : menuBean.getSon()) {
                            items.add(bean.getName());
                            menus.add(bean);
                        }
                        break;
                    }
                }
                new UIActionSheetDialog(activity)
                        .builder()
                        .addSheetItemList(items, null, which -> {
                            if (items.get(which).equals("全部")) {
                                cateId = cateIdParent;
                                tvType.setText("全部");
                            } else {
                                cateId = menus.get(which - 1).getId();
                                tvType.setText(items.get(which));
                            }
                            resetRefresh();
                        })
                        .show();
                break;
        }
    }
}
