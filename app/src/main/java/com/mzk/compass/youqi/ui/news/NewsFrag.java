package com.mzk.compass.youqi.ui.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ViewPageAdapter;
import com.mzk.compass.youqi.base.BaseAppFragment;
import com.mzk.compass.youqi.bean.BannerBean;
import com.mzk.compass.youqi.bean.TypeBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;
import com.znz.compass.znzlibray.views.imageloder.GlideApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Date： 2018/3/29 2018
 * User： PSuiyi
 * Description：
 */
public class NewsFrag extends BaseAppFragment {
    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;
    @Bind(R.id.znzRemind)
    ZnzRemind znzRemind;
    @Bind(R.id.llNetworkStatus)
    LinearLayout llNetworkStatus;
    @Bind(R.id.banner)
    BGABanner banner;
    @Bind(R.id.commonTabLayout)
    TabLayout commonTabLayout;
    @Bind(R.id.commonViewPager)
    ViewPager commonViewPager;

    private List<String> tabNames = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    private List<String> imgPath = new ArrayList<>();
    private List<String> path = new ArrayList<>();

    private List<TypeBean> typeBeanList = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_news, 2};
    }

    @Override
    protected void initializeVariate() {
    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchEnableEdit(false);
        znzToolBar.setOnSearchClickListener(view -> {
            mDataManager.saveTempData(Constants.SearchType.SEARCHTYPE, "1");
            Bundle bundle = new Bundle();
            bundle.putString("from", "搜索优报道");
            gotoActivity(SearchCommonAct.class, bundle);
        });

        znzToolBar.setSearchRightImage(R.mipmap.xiaoxi);
        znzToolBar.setOnSearchRightClickListener(v -> {
            gotoActivity(MessageTabAct.class);
        });
    }

    @Override
    protected void initializeView() {
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
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestNewsType(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                typeBeanList.clear();
                typeBeanList.addAll(JSONArray.parseArray(responseOriginal.getString("data"), TypeBean.class));

                for (TypeBean typeBean : typeBeanList) {
                    tabNames.add(typeBean.getName());
                    fragmentList.add(new NewsListFrag().newInstance(typeBean.getId()));
                }

                commonViewPager.setAdapter(new ViewPageAdapter(getChildFragmentManager(), tabNames, fragmentList));
                commonTabLayout.setupWithViewPager(commonViewPager);
                commonViewPager.setOffscreenPageLimit(fragmentList.size());
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
        Map<String, String> params1 = new HashMap<>();
        params1.put("bannerType", "MobileNewsBanner");
        mModel.requestBanner(params1, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                imgPath.clear();
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
}
