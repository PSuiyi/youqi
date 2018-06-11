package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MenuHelpAdapter;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.adapter.ProductGridAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.BannerBean;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.mzk.compass.youqi.utils.AppUtils;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/3/29 2018
 * User： PSuiyi
 * Description：
 */
public class HelpFrag extends BaseAppListFragment {
    private View header;
    private BGABanner banner;
    private RecyclerView rvProduct;
    private ProductGridAdapter productGridAdapter;
    private List<ProductBean> productList = new ArrayList<>();

    private RecyclerView rvMenuTop;
    private MenuHelpAdapter menuHelpAdapter;
    private List<MenuBean> menuBeanList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_help, 2};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchEnableEdit(false);
        znzToolBar.setSearchLeftImage(R.mipmap.fenlei);
        znzToolBar.setSearchRightImage(R.mipmap.xiaoxi);
        znzToolBar.setOnSearchLeftClickListener(v -> {
            gotoActivity(TypeListAct.class);
        });
        znzToolBar.setOnSearchClickListener(view -> {
            mDataManager.saveTempData(Constants.SearchType.SEARCHTYPE, "2");
            Bundle bundle = new Bundle();
            bundle.putString("from", "找商品");
            gotoActivity(SearchCommonAct.class, bundle);
        });

        znzToolBar.setOnSearchRightClickListener(v -> {
            gotoActivity(MessageTabAct.class);
        });
        znzToolBar.setSearchHint("找商品");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(activity, 2);
    }

    @Override
    protected void initializeView() {
        adapter = new ProductAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        header = View.inflate(activity, R.layout.header_help, null);
        rvMenuTop = bindViewById(header, R.id.rvMenuTop);
        rvMenuTop.setLayoutManager(new GridLayoutManager(activity,4));
        menuHelpAdapter = new MenuHelpAdapter(menuBeanList);
        rvMenuTop.setAdapter(menuHelpAdapter);

        banner = bindViewById(header, R.id.banner);
        rvProduct = bindViewById(header, R.id.rvProduct);
        adapter.addHeaderView(header);

        productGridAdapter = new ProductGridAdapter(productList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvProduct.setNestedScrollingEnabled(false);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setAdapter(productGridAdapter);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestHelpHome(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                productList.clear();
                productList.addAll(JSONArray.parseArray(responseObject.getString("mobileHelpBannerUnder"), ProductBean.class));
                productGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });

        Map<String, String> params1 = new HashMap<>();
        params1.put("bannerType", "MobileHelpBanner");
        mModel.requestBanner(params1, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                List<BannerBean> bannerBeanList = JSON.parseArray(responseOriginal.getString("data"), BannerBean.class);
                banner.setData(R.layout.banner_common, bannerBeanList, null);
                banner.setAdapter(new BGABanner.Adapter<LinearLayout, BannerBean>() {
                    @Override
                    public void fillBannerItem(BGABanner banner, LinearLayout container, BannerBean bean, int position) {
                        HttpImageView ivImage = container.findViewById(R.id.ivImage);
                        ivImage.loadRectImage(bean.getImage());
                    }
                });
                banner.setDelegate(new BGABanner.Delegate<LinearLayout, BannerBean>() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, LinearLayout container, BannerBean bean, int position) {
                        AppUtils.getInstance(activity).doBannerClick(activity, bean);
                    }
                });
            }
        });

        Map<String, String> params2 = new HashMap<>();
        mModel.requestCategory(params2, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                menuBeanList.clear();
                menuBeanList.addAll(JSONArray.parseArray(responseOriginal.getString("data"), MenuBean.class));
                menuHelpAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        return mModel.requestHelpRecommend(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSONArray.parseArray(response, ProductBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
