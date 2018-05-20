package com.mzk.compass.youqi.ui.home.product;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.ui.help.ProductOrderComfirmAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIActionSheetDialog;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/16 2018
 * User： PSuiyi
 * Description：
 */
public class ProductDetailAct extends BaseAppActivity {
    @Bind(R.id.llCity)
    LinearLayout llCity;
    @Bind(R.id.llArea)
    LinearLayout llArea;
    @Bind(R.id.tvFav)
    TextView tvFav;
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.tvBuy)
    TextView tvBuy;
    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.ivFav)
    ImageView ivFav;
    @Bind(R.id.tvMoney)
    TextView tvMoney;
    @Bind(R.id.tvMoneyOld)
    TextView tvMoneyOld;
    @Bind(R.id.tvCountPayed)
    TextView tvCountPayed;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.wvDetail)
    WebViewWithProgress wvDetail;
    private String id;
    private ProductBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_product_detail};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("productId", id);
        mModel.requestProductDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSONObject.parseObject(responseOriginal.getString("data"), ProductBean.class);
                ivImage.loadSquareImage(bean.getMobilePhoto());
                mDataManager.setValueToView(tvTitle, bean.getName());
                mDataManager.setValueToView(tvMoney, "¥" + bean.getRealPrice());
                mDataManager.setValueToView(tvMoneyOld, "原价：¥" + bean.getMarketPrice());
                mDataManager.setValueToView(tvCountPayed, bean.getShowNum());
                wvDetail.loadContent(bean.getContent());
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.llCity, R.id.llArea, R.id.tvFav, R.id.tvPhone, R.id.tvBuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCity:
                new UIAlertDialog(activity)
                        .builder()
                        .setMsg("目前只开通南京区服务,其他城市敬请期待！")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", v2 -> {

                        })
                        .show();
                break;
            case R.id.llArea:
                List<String> items = new ArrayList<>();
                items.add("鼓楼区");
                items.add("鼓楼区");
                items.add("鼓楼区");
                items.add("鼓楼区");
                items.add("鼓楼区");
                items.add("鼓楼区");
                items.add("鼓楼区");
                items.add("鼓楼区");
                new UIActionSheetDialog(activity)
                        .builder()
                        .addSheetItemList(items, null, which -> {

                        })
                        .show();
                break;
            case R.id.tvFav:
                break;
            case R.id.tvPhone:
                mDataManager.callPhone(activity, "400-8888-8888");
                break;
            case R.id.tvBuy:
                gotoActivity(ProductOrderComfirmAct.class);
                break;
        }
    }
}
