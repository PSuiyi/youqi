package com.mzk.compass.youqi.ui.help;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProductAdapter;
import com.mzk.compass.youqi.adapter.ProductGridAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.utils.DipUtil;
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
    private RecyclerView rvProduct;
    private ProductGridAdapter productGridAdapter;
    private List<BaseZnzBean> productList = new ArrayList<>();
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
            gotoActivity(ProductListAct.class);
        });

        banner = bindViewById(header, R.id.banner);
        rvProduct = bindViewById(header, R.id.rvProduct);
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

        productList.add(new BaseZnzBean());
        productList.add(new BaseZnzBean());
        productList.add(new BaseZnzBean());
        productList.add(new BaseZnzBean());
        productList.add(new BaseZnzBean());

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

    }

    @Override
    protected void onRefreshSuccess(String response) {

    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
