package com.mzk.compass.youqi.ui.home.product;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.help.ProductOrderComfirmAct;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIActionSheetDialog;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
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
    @Bind(R.id.tvNumber)
    TextView tvNumber;
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
                bean.setCount("1");
                ivImage.loadSquareImage(bean.getMobilePhoto());
                mDataManager.setValueToView(tvTitle, bean.getName());
                mDataManager.setValueToView(tvMoney, "¥" + bean.getRealPrice());
                mDataManager.setValueToView(tvMoneyOld, "原价：¥" + bean.getMarketPrice());
                mDataManager.setValueToView(tvCountPayed, bean.getShowNum());
                mDataManager.setValueToView(tvNumber, bean.getCount());
                wvDetail.loadContent(bean.getContent());
                if (bean.getIsCollected().equals("true")) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvFav.setCompoundDrawables(null, drawable, null, null);
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvFav.setCompoundDrawables(null, drawable, null, null);
                }
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

    @OnClick({R.id.llCity, R.id.llArea, R.id.tvFav, R.id.tvPhone, R.id.tvBuy, R.id.ivDown, R.id.ivAdd})
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
                if (bean.getIsCollected().equals("true")) {
                    cancalCollect();
                } else {
                    addCollect();
                }
                break;
            case R.id.tvPhone:
                mDataManager.callPhone(activity, "400-8888-8888");
                break;
            case R.id.tvBuy:
                Map<String, String> params = new HashMap<>();
                params.put("num", bean.getCount());
                params.put("productId", bean.getId());
                mModel.requestOrderConfirm(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        bean.setTotalMoney(getTotalMoney());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bean", bean);
                        gotoActivity(ProductOrderComfirmAct.class, bundle);
                    }
                });
                break;
            case R.id.ivDown:
                if (StringUtil.stringToInt(mDataManager.getValueFromView(tvNumber)) <= 1) {
                    return;
                }
                bean.setCount(StringUtil.getNumDown(mDataManager.getValueFromView(tvNumber)));
                mDataManager.setValueToView(tvNumber, bean.getCount());
                break;
            case R.id.ivAdd:
                bean.setCount(StringUtil.getNumUP(mDataManager.getValueFromView(tvNumber)));
                mDataManager.setValueToView(tvNumber, bean.getCount());
                break;
        }
    }

    private String getTotalMoney() {
        if (StringUtil.isBlank(bean.getRealPrice())) {
            return "0";
        }
        if (StringUtil.isBlank(bean.getCount())) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(bean.getRealPrice());
        BigDecimal b2 = new BigDecimal(bean.getCount());
        double total = b1.multiply(b2).doubleValue();
        return total + "";
    }

    private void addCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "4");
        params.put("id", id);
        mModel.requestAddCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvFav.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("true");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PRODUCT));
            }
        });
    }

    private void cancalCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "4");
        params.put("id", id);
        mModel.requestCancalCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("取消收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvFav.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("false");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PRODUCT));
            }
        });
    }
}
