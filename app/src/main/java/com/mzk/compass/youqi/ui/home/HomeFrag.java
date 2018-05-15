package com.mzk.compass.youqi.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MultiAdapter;
import com.mzk.compass.youqi.base.BaseAppFragment;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.bean.OrganBean;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.CityListAct;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.mzk.compass.youqi.ui.home.people.PeopleListAct;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
import com.mzk.compass.youqi.ui.home.project.ProjectListAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.mzk.compass.youqi.ui.publish.PublishAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.BitmapUtil;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.GlideApp;

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
            gotoActivity(CityListAct.class);
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

        llHot1.setOnClickListener(v -> {
            gotoActivity(ProjectDetailAct.class);
        });
        llHot2.setOnClickListener(v -> {
            gotoActivity(ProjectDetailAct.class);
        });
        llHot3.setOnClickListener(v -> {
            gotoActivity(ProjectDetailAct.class);
        });
        llHot4.setOnClickListener(v -> {
            gotoActivity(ProjectDetailAct.class);
        });
        llHot5.setOnClickListener(v -> {
            gotoActivity(ProjectDetailAct.class);
        });

        banner = bindViewById(header, R.id.banner);
        adapter.addHeaderView(header);


        imgPath.add("http://pic.58pic.com/58pic/11/79/25/56e58PICEkR.jpg");
        imgPath.add("http://pic18.nipic.com/20111216/6647776_200041153000_2.jpg");
        imgPath.add("http://file06.16sucai.com/2016/0419/ef244d70b96ff51ec4c0a6d8d0811597.jpg");
        imgPath.add("http://pic.58pic.com/58pic/16/50/28/38E58PICcgV_1024.jpg");
        imgPath.add("http://pic.qiantucdn.com/58pic/18/21/29/55ed2fef9346d_1024.jpg");
        banner.setData(imgPath, imgPath);
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
        mModel.requestHome(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                dataList.add(new MultiBean(Constants.MultiType.Section, "精选创业项目"));
                dataList.add(new MultiBean(Constants.MultiType.Project));
                dataList.add(new MultiBean(Constants.MultiType.Project));
                dataList.add(new MultiBean(Constants.MultiType.Project));
                dataList.add(new MultiBean(Constants.MultiType.Project));


                if (!StringUtil.isBlank(responseObject.getString("MobileCompanyInvestorStar"))) {
                    dataList.add(new MultiBean(Constants.MultiType.Section, "明星投资人"));
                    MultiBean multiBean = new MultiBean(Constants.MultiType.People);
                    multiBean.setPeopleList(JSONArray.parseArray(responseObject.getString("MobileCompanyInvestorStar"), PeopleBean.class));
                    dataList.add(multiBean);
                }

                if (!StringUtil.isBlank(responseObject.getString("MobileCompanyInstitution"))) {
                    dataList.add(new MultiBean(Constants.MultiType.Section, "精选机构"));
                    dataList.add(new MultiBean(Constants.MultiType.Organ, JSONArray.parseArray(responseObject.getString("MobileCompanyInstitution"), OrganBean.class)));
                }
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }
}
