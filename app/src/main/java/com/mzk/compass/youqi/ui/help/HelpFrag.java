package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.adapter.ProductGridAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.BannerBean;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.mzk.compass.youqi.ui.home.product.ProductListAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.imageloder.GlideApp;

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
    private TextView tvMenu1;
    private TextView tvMenu2;
    private TextView tvMenu3;
    private TextView tvMenu4;
    private BGABanner banner;
    private List<String> imgPath = new ArrayList<>();
    private RecyclerView rvProduct;
    private ProductGridAdapter productGridAdapter;
    private List<ProductBean> productList = new ArrayList<>();
    private View tvMenu5;
    private View tvMenu6;
    private View tvMenu7;
    private View tvMenu8;

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
            bundle.putString("from", "搜索投资人");
            gotoActivity(SearchCommonAct.class, bundle);
        });

        znzToolBar.setOnSearchRightClickListener(v -> {
            gotoActivity(MessageTabAct.class);
        });
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
        tvMenu1 = bindViewById(header, R.id.tvMenu1);
        tvMenu2 = bindViewById(header, R.id.tvMenu2);
        tvMenu3 = bindViewById(header, R.id.tvMenu3);
        tvMenu4 = bindViewById(header, R.id.tvMenu4);
        tvMenu5 = bindViewById(header, R.id.tvMenu5);
        tvMenu6 = bindViewById(header, R.id.tvMenu6);
        tvMenu7 = bindViewById(header, R.id.tvMenu7);
        tvMenu8 = bindViewById(header, R.id.tvMenu8);

        tvMenu1.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu2.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu3.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu4.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu5.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu6.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu7.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu8.setOnClickListener(v -> {
            gotoActivity(TypeListAct.class);
        });

        banner = bindViewById(header, R.id.banner);
        rvProduct = bindViewById(header, R.id.rvProduct);
        adapter.addHeaderView(header);

        banner.setDelegate((banner, itemView, model, position) -> {
        });
        banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                GlideApp.with(activity)
                        .load(model)
                        .placeholder(R.mipmap.default_image_rect)
                        .error(R.mipmap.default_image_rect)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });

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
                productList.addAll(JSONArray.parseArray(responseOriginal.getString("data"), ProductBean.class));
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
                List<BannerBean> list = new ArrayList<>();
                list.addAll(JSON.parseArray(responseOriginal.getString("data"), BannerBean.class));
                if (!list.isEmpty()) {
                    for (BannerBean bannerBean : list) {
                        imgPath.add(bannerBean.getImage());
                    }
                    banner.setData(imgPath, imgPath);
                }
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
