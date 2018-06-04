package com.mzk.compass.youqi.ui.home.organ;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.OrganBean;
import com.mzk.compass.youqi.bean.TagYouBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.home.people.RecommendSelfAct;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.scRootView)
    ScrollView scRootView;
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

                if (bean.getIsCollected().equals("true")) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption3.setCompoundDrawables(null, drawable, null, null);
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption3.setCompoundDrawables(null, drawable, null, null);
                }
                if (bean.getTradeid() != null & bean.getTradeid().size() > 0) {
                    String temp = "";
                    for (TagYouBean tagYouBean : bean.getTradeid()) {
                        temp += tagYouBean.getName() + " ";
                    }
                    tvIndusty.setText("所属行业：" + temp);
                } else {
                    tvIndusty.setText("所属行业：暂无数据");
                }
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    private void addCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "3");
        params.put("id", id);
        mModel.requestAddCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang2);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption3.setCompoundDrawables(null, drawable, null, null);
                ivFav.setImageResource(R.mipmap.shoucanghuang);
                bean.setIsCollected("true");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_ORGAN));
            }
        });
    }

    private void cancalCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "3");
        params.put("id", id);
        mModel.requestCancalCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("取消收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption3.setCompoundDrawables(null, drawable, null, null);
                ivFav.setImageResource(R.mipmap.shoucang);
                bean.setIsCollected("false");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_ORGAN));
            }
        });
    }


    @OnClick({R.id.tvOption1, R.id.ivFav, R.id.ivShare, R.id.tvRecommend, R.id.tvOption2, R.id.tvOption3, R.id.tvOption4, R.id.tvOption5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvOption1:
                break;
            case R.id.tvRecommend:
            case R.id.tvOption2:
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("from", "投资机构");
                gotoActivity(RecommendSelfAct.class, bundle);
                break;
            case R.id.ivFav:
            case R.id.tvOption3:
                if (bean.getIsCollected().equals("true")) {
                    cancalCollect();
                } else {
                    addCollect();
                }
                break;
            case R.id.ivShare:
            case R.id.tvOption4:
                PopupWindowManager.getInstance(activity).showShare(view, (type, values) -> {

                });
                break;
            case R.id.tvOption5:
                scRootView.smoothScrollTo(0, 0);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
