package com.mzk.compass.youqi.ui.help;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIActionSheetDialog;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_product_detail};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {

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
                mDataManager.callPhone(activity,"400-8888-8888");
                break;
            case R.id.tvBuy:
                gotoActivity(ProductOrderComfirmAct.class);
                break;
        }
    }
}
