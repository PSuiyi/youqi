package com.mzk.compass.youqi.ui.home.organ;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.OrganBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/4/22 2018
 * User： PSuiyi
 * Description：
 */
public class OrganDetailAct extends BaseAppActivity {

    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;
    @Bind(R.id.znzRemind)
    ZnzRemind znzRemind;
    @Bind(R.id.llNetworkStatus)
    LinearLayout llNetworkStatus;
    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvType)
    TextView tvType;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.tvWeb)
    TextView tvWeb;
    @Bind(R.id.tvIndusty)
    TextView tvIndusty;
    @Bind(R.id.tvCountFav)
    TextView tvCountFav;
    @Bind(R.id.tvCountView)
    TextView tvCountView;
    @Bind(R.id.ivShare)
    ImageView ivShare;
    @Bind(R.id.ivFav)
    ImageView ivFav;
    @Bind(R.id.tvRecommend)
    TextView tvRecommend;
    @Bind(R.id.wvDetail)
    WebViewWithProgress wvDetail;
    @Bind(R.id.tvLike)
    TextView tvLike;
    @Bind(R.id.wvModel)
    WebViewWithProgress wvModel;
    @Bind(R.id.tvOption1)
    TextView tvOption1;
    @Bind(R.id.tvOption2)
    TextView tvOption2;
    @Bind(R.id.tvOption3)
    TextView tvOption3;
    @Bind(R.id.tvOption4)
    TextView tvOption4;
    @Bind(R.id.tvOption5)
    TextView tvOption5;
    private String id;
    private OrganBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_organ_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("机构详情");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("companyId", id);
        mModel.requestOrganDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSONObject.parseObject(responseOriginal.getString("data"), OrganBean.class);
                ivImage.loadSquareImage(bean.getLogo());
                mDataManager.setValueToView(tvName, bean.getCname());
                mDataManager.setValueToView(tvCountView, bean.getVisiteNum());
                mDataManager.setValueToView(tvCountFav, bean.getCollectionNum());
                wvDetail.loadContent(bean.getSummary());
                wvModel.loadContent(bean.getExample());
                mDataManager.setValueToView(tvWeb, bean.getWebsite());
                mDataManager.setValueToView(tvAddress, bean.getProvince() + bean.getCity() + bean.getArea() + bean.getAddress());


//                if (bean.getTradeid() != null & bean.getTradeid().size() > 0) {
//                    mDataManager.setViewVisibility(rvTrade, true);
//                    TradeAdapter adapter = new TradeAdapter(bean.getTradeid());
//                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
//                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                    rvTrade.setLayoutManager(layoutManager);
//                    rvTrade.setAdapter(adapter);
//                } else {
//                    mDataManager.setViewVisibility(rvTrade, false);
//                }
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
}
