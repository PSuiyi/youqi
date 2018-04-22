package com.mzk.compass.youqi.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MultiAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.CityListAct;
import com.mzk.compass.youqi.ui.help.ProductListAct;
import com.mzk.compass.youqi.ui.home.people.PeopleListAct;
import com.mzk.compass.youqi.ui.home.project.ProjectListAct;
import com.znz.compass.znzlibray.utils.BitmapUtil;
import com.znz.compass.znzlibray.views.imageloder.GlideApp;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Date： 2018/3/29 2018
 * User： PSuiyi
 * Description：
 */
public class HomeFrag extends BaseAppListFragment {

    private View header;
    private TextView tvMenu1;
    private TextView tvMenu2;
    private TextView tvMenu3;
    private TextView tvMenu4;
    private BGABanner banner;
    private List<String> imgPath = new ArrayList<>();

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout_withnav, 2};
    }

    @Override
    protected void initializeVariate() {
        dataList.add(new MultiBean(Constants.MultiType.Section, "精选创业项目"));
        dataList.add(new MultiBean(Constants.MultiType.Project));
        dataList.add(new MultiBean(Constants.MultiType.Project));
        dataList.add(new MultiBean(Constants.MultiType.Project));
        dataList.add(new MultiBean(Constants.MultiType.Project));
        dataList.add(new MultiBean(Constants.MultiType.Section, "明星投资人"));
        dataList.add(new MultiBean(Constants.MultiType.People));
        dataList.add(new MultiBean(Constants.MultiType.Section, "精选机构"));
        dataList.add(new MultiBean(Constants.MultiType.Organ));
    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchEnableEdit(false);
        znzToolBar.setSearchLeft("南京", BitmapUtil.getResourceDrawable(activity, R.mipmap.xiala));
        znzToolBar.setSearchRightImage(R.mipmap.xiaoxi);
        znzToolBar.setOnSearchLeftClickListener(v -> {
            gotoActivity(CityListAct.class);
        });
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new MultiAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        header = View.inflate(activity, R.layout.header_home, null);
        tvMenu1 = bindViewById(header, R.id.tvMenu1);
        tvMenu2 = bindViewById(header, R.id.tvMenu2);
        tvMenu3 = bindViewById(header, R.id.tvMenu3);
        tvMenu4 = bindViewById(header, R.id.tvMenu4);

        tvMenu1.setOnClickListener(v -> {
            gotoActivity(ProjectListAct.class);
        });
        tvMenu2.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
        });
        tvMenu3.setOnClickListener(v -> {
            gotoActivity(PeopleListAct.class);
        });
        tvMenu4.setOnClickListener(v -> {
            gotoActivity(ProductListAct.class);
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

    }

    @Override
    protected void onRefreshSuccess(String response) {

    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
