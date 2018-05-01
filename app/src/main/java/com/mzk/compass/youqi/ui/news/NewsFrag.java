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

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ViewPageAdapter;
import com.mzk.compass.youqi.base.BaseAppFragment;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.SearchCommonAct;
import com.mzk.compass.youqi.ui.mine.message.MessageTabAct;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;
import com.znz.compass.znzlibray.views.imageloder.GlideApp;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.frag_news, 2};
    }

    @Override
    protected void initializeVariate() {
        tabNames.add("政策解读");
        tabNames.add("资讯推送");
        tabNames.add("风口行业");
        tabNames.add("财税资讯");
        tabNames.add("创业事迹");

        fragmentList.add(new NewsListFrag());
        fragmentList.add(new NewsListFrag());
        fragmentList.add(new NewsListFrag());
        fragmentList.add(new NewsListFrag());
        fragmentList.add(new NewsListFrag());


        imgPath.add("http://pic.58pic.com/58pic/11/79/25/56e58PICEkR.jpg");
        imgPath.add("http://pic18.nipic.com/20111216/6647776_200041153000_2.jpg");
        imgPath.add("http://file06.16sucai.com/2016/0419/ef244d70b96ff51ec4c0a6d8d0811597.jpg");
        imgPath.add("http://pic.58pic.com/58pic/16/50/28/38E58PICcgV_1024.jpg");
        imgPath.add("http://pic.qiantucdn.com/58pic/18/21/29/55ed2fef9346d_1024.jpg");
        path.add("http://pic.58pic.com/58pic/11/79/25/56e58PICEkR.jpg");
        path.add("http://pic18.nipic.com/20111216/6647776_200041153000_2.jpg");
        path.add("http://file06.16sucai.com/2016/0419/ef244d70b96ff51ec4c0a6d8d0811597.jpg");
        path.add("http://pic.58pic.com/58pic/16/50/28/38E58PICcgV_1024.jpg");
        path.add("http://pic.qiantucdn.com/58pic/18/21/29/55ed2fef9346d_1024.jpg");
    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchEnableEdit(false);
        znzToolBar.setOnSearchClickListener(view -> {
            mDataManager.saveTempData(Constants.SearchType.SEARCHTYPE, "1");
            Bundle bundle = new Bundle();
            bundle.putString("from", "搜索项目");
            gotoActivity(SearchCommonAct.class, bundle);
        });

        znzToolBar.setSearchRightImage(R.mipmap.xiaoxi);
        znzToolBar.setOnSearchRightClickListener(v -> {
            gotoActivity(MessageTabAct.class);
        });
    }

    @Override
    protected void initializeView() {
        commonViewPager.setAdapter(new ViewPageAdapter(getChildFragmentManager(), tabNames, fragmentList));
        commonTabLayout.setupWithViewPager(commonViewPager);
        commonViewPager.setOffscreenPageLimit(fragmentList.size());


        banner.setData(imgPath, path);
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
