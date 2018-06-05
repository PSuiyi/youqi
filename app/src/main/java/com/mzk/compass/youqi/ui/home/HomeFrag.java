package com.mzk.compass.youqi.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MultiAdapter;
import com.mzk.compass.youqi.base.BaseAppFragment;
import com.mzk.compass.youqi.bean.BannerBean;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.bean.OrganBean;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.CityListAct;
import com.mzk.compass.youqi.ui.common.HomeCityListAct;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.mzk.compass.youqi.ui.home.people.PeopleApproveAct;
import com.mzk.compass.youqi.ui.home.people.PeopleListAct;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
import com.mzk.compass.youqi.ui.home.project.ProjectListAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.mzk.compass.youqi.ui.publish.PublishAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.BitmapUtil;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.GlideApp;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Date： 2018/3/29 2018
 * User： PSuiyi
 * Description：
 */
public class HomeFrag extends BaseAppFragment {

    @Bind(R.id.rvHome)
    RecyclerView rvHome;
    private View header;
    private TextView tvMenu1;
    private TextView tvMenu2;
    private TextView tvMenu3;
    private TextView tvMenu4;
    private LinearLayout llHot1;
    private LinearLayout llHot2;
    private LinearLayout llHot3;
    private LinearLayout llHot4;
    private LinearLayout llHot5;
    private BGABanner banner;
    private List<String> imgPath = new ArrayList<>();

    private List<MultiBean> dataList = new ArrayList<>();
    private MultiAdapter adapter;
    private List<ProjectBean> projectBeanList = new ArrayList<>();

    private HttpImageView ivImage1;
    private HttpImageView ivImage2;
    private HttpImageView ivImage3;
    private HttpImageView ivImage4;
    private HttpImageView ivImage5;
    private List<ProjectBean> hotList = new ArrayList<>();
    private int page = 0;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_home, 2};
    }

    @Override
    protected void initializeVariate() {
    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchEnableEdit(false);
        znzToolBar.setSearchLeft("南京", BitmapUtil.getResourceDrawable(activity, R.mipmap.xiala));
        znzToolBar.setSearchRightImage(R.mipmap.xiaoxi);
        znzToolBar.setOnSearchLeftClickListener(v -> {
            gotoActivity(HomeCityListAct.class);
        });
        znzToolBar.setOnSearchClickListener(view -> {
            mDataManager.saveTempData(Constants.SearchType.SEARCHTYPE, "1");
            Bundle bundle = new Bundle();
            bundle.putString("from", "搜索项目");
            gotoActivity(SearchCommonAct.class, bundle);
        });

        znzToolBar.setOnSearchRightClickListener(v -> {
            gotoActivity(MessageTabAct.class);
        });
    }

    @Override
    protected void initializeView() {
        adapter = new MultiAdapter(dataList);
        rvHome.setLayoutManager(new LinearLayoutManager(activity));
        rvHome.setAdapter(adapter);

        header = View.inflate(activity, R.layout.header_home, null);
        tvMenu1 = bindViewById(header, R.id.tvMenu1);
        tvMenu2 = bindViewById(header, R.id.tvMenu2);
        tvMenu3 = bindViewById(header, R.id.tvMenu3);
        tvMenu4 = bindViewById(header, R.id.tvMenu4);

        ivImage1 = bindViewById(header, R.id.ivImage1);
        ivImage2 = bindViewById(header, R.id.ivImage2);
        ivImage3 = bindViewById(header, R.id.ivImage3);
        ivImage4 = bindViewById(header, R.id.ivImage4);
        ivImage5 = bindViewById(header, R.id.ivImage5);

        tvMenu1.setOnClickListener(v -> {
            gotoActivity(ProjectListAct.class);
        });
        tvMenu2.setOnClickListener(v -> {
            gotoActivity(PublishAct.class);
        });
        tvMenu3.setOnClickListener(v -> {
            gotoActivity(PeopleListAct.class);
        });
        tvMenu4.setOnClickListener(v -> {
            gotoActivity(PeopleApproveAct.class);
        });


        llHot1 = bindViewById(header, R.id.llHot1);
        llHot2 = bindViewById(header, R.id.llHot2);
        llHot3 = bindViewById(header, R.id.llHot3);
        llHot4 = bindViewById(header, R.id.llHot4);
        llHot5 = bindViewById(header, R.id.llHot5);

        banner = bindViewById(header, R.id.banner);
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


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.llChange:
                        requestHomeData();
                        break;
                }
            }
        });
    }

    @Override
    protected void loadDataFromServer() {
        requestHomeData();

        Map<String, String> params1 = new HashMap<>();
        params1.put("bannerType", "MobileCompanyBanner");
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
                    banner.setDelegate((banner, itemView, model, position) -> {
                    });
                }
            }
        });
    }

    private void requestHomeData() {
        Map<String, String> params2 = new HashMap<>();
        params2.put("page", page + "");
        mModel.requestHomeRecommend(params2, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                page++;
                projectBeanList.clear();
                projectBeanList.addAll(JSONArray.parseArray(responseOriginal.getString("data"), ProjectBean.class));
                dataList.clear();

                Map<String, String> params = new HashMap<>();
                mModel.requestHome(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        if (!StringUtil.isBlank(responseObject.getString("mobileCompanyRecommendBig"))) {
                            hotList.clear();
                            hotList.addAll(JSONArray.parseArray(responseObject.getString("mobileCompanyRecommendBig"), ProjectBean.class));
                            if (!StringUtil.isBlank(responseObject.getString("mobileCompanyRecommendSmall"))) {
                                hotList.addAll(JSONArray.parseArray(responseObject.getString("mobileCompanyRecommendSmall"), ProjectBean.class));
                            }
                            if (!hotList.isEmpty()) {
                                if (hotList.size() >= 5) {
                                    ivImage1.loadVerImage(hotList.get(0).getLogo());
                                    ivImage2.loadVerImage(hotList.get(1).getLogo());
                                    ivImage3.loadVerImage(hotList.get(2).getLogo());
                                    ivImage4.loadVerImage(hotList.get(3).getLogo());
                                    ivImage5.loadVerImage(hotList.get(4).getLogo());

                                    ivImage1.setOnClickListener(v -> {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", hotList.get(0).getLink());
                                        gotoActivity(ProjectDetailAct.class, bundle);
                                    });
                                    ivImage2.setOnClickListener(v -> {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", hotList.get(1).getLink());
                                        gotoActivity(ProjectDetailAct.class, bundle);
                                    });
                                    ivImage3.setOnClickListener(v -> {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", hotList.get(2).getLink());
                                        gotoActivity(ProjectDetailAct.class, bundle);
                                    });
                                    ivImage4.setOnClickListener(v -> {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", hotList.get(3).getLink());
                                        gotoActivity(ProjectDetailAct.class, bundle);
                                    });
                                    ivImage5.setOnClickListener(v -> {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", hotList.get(4).getLink());
                                        gotoActivity(ProjectDetailAct.class, bundle);
                                    });
                                }
                            }
                        }

                        if (!projectBeanList.isEmpty()) {
                            dataList.add(new MultiBean(Constants.MultiType.Section, "精选创业项目"));
                            MultiBean multiBean = new MultiBean(Constants.MultiType.Project);
                            multiBean.setProjectBeanList(projectBeanList);
                            dataList.add(multiBean);
                        }

                        if (!StringUtil.isBlank(responseObject.getString("mobileCompanyInvestorStar"))) {
                            dataList.add(new MultiBean(Constants.MultiType.Section, "明星投资人"));
                            MultiBean multiBean = new MultiBean(Constants.MultiType.People);
                            multiBean.setPeopleList(JSONArray.parseArray(responseObject.getString("mobileCompanyInvestorStar"), PeopleBean.class));
                            dataList.add(multiBean);
                        }

                        if (!StringUtil.isBlank(responseObject.getString("mobileCompanyInstitution"))) {
                            dataList.add(new MultiBean(Constants.MultiType.Section, "精选机构"));
                            dataList.add(new MultiBean(Constants.MultiType.Organ, JSONArray.parseArray(responseObject.getString("mobileCompanyInstitution"), OrganBean.class)));
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }
}
