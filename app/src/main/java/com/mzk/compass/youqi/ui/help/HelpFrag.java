package com.mzk.compass.youqi.ui.help;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.znz.compass.znzlibray.views.imageloder.GlideApp;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

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

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout_withnav, 2};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        znzToolBar.setSearchEnableEdit(false);
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
