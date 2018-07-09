package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.TradeAdapter;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.bean.TagYouBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.znz.compass.umeng.share.ShareBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIActionSheetDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/6.
 */

public class ProjectRongZiAct extends BaseAppActivity {
    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvTag)
    TextView tvTag;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.rvTrade)
    RecyclerView rvTrade;
    @Bind(R.id.ivLogo)
    HttpImageView ivLogo;
    @Bind(R.id.tvCompanyName)
    TextView tvCompanyName;
    @Bind(R.id.tvShizhi)
    TextView tvShizhi;
    @Bind(R.id.tvCountFav)
    TextView tvCountFav;
    @Bind(R.id.tvCountComment)
    TextView tvCountComment;
    @Bind(R.id.tvCountView)
    TextView tvCountView;
    @Bind(R.id.ivShare)
    ImageView ivShare;
    @Bind(R.id.ivFav)
    ImageView ivFav;
    @Bind(R.id.tvLunci)
    TextView tvLunci;
    @Bind(R.id.llLuci)
    LinearLayout llLuci;
    @Bind(R.id.etPrice)
    EditText etPrice;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    private ProjectBean bean;
    private List<TagYouBean> list = new ArrayList<>();
    private TradeAdapter adapter;
    private String roundsId;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_project_rongzi, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("bean")) {
            bean = (ProjectBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("我的项目");
    }

    @Override
    protected void initializeView() {
        adapter = new TradeAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTrade.setLayoutManager(layoutManager);
        rvTrade.setAdapter(adapter);

        mDataManager.setValueToView(tvCountFav, bean.getCollectionNum(), "0");
        mDataManager.setValueToView(tvCountComment, bean.getCommentsNum(), "0");
        mDataManager.setValueToView(tvCountView, bean.getVisiteNum(), "0");

        ivImage.loadSquareImage(bean.getLogo());
        mDataManager.setValueToView(tvName, bean.getName());
        mDataManager.setValueToView(tvContent, bean.getTitle());

        if (!StringUtil.isBlank(bean.getRname())) {
            mDataManager.setValueToView(tvTag, bean.getRname());
        } else {
            mDataManager.setValueToView(tvTag, bean.getRoundsid());
        }

        if (bean.getTradeid() != null & bean.getTradeid().size() > 0) {
            mDataManager.setViewVisibility(rvTrade, true);
            TradeAdapter adapter = new TradeAdapter(bean.getTradeid());
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(activity);
            layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvTrade.setLayoutManager(layoutManager2);
            rvTrade.setAdapter(adapter);
        } else {
            mDataManager.setViewVisibility(rvTrade, false);
        }

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("projectId", bean.getId());
        mModel.requestRongZiDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                    bean = JSON.parseObject(json.getString("project"), ProjectBean.class);
                    if (!StringUtil.isBlank(bean.getIsCollected())) {
                        if (bean.getIsCollected().equals("true")) {
                            ivFav.setImageResource(R.mipmap.shoucanghuang);
                        } else {
                            ivFav.setImageResource(R.mipmap.shoucang);
                        }
                    } else {
                        ivFav.setImageResource(R.mipmap.shoucang);
                    }

                    list.addAll(JSON.parseArray(json.getString("roundsData"), TagYouBean.class));
                    adapter.notifyDataSetChanged();
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

    @OnClick({R.id.ivShare, R.id.ivFav, R.id.llLuci, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivShare:
                ShareBean shareBean = new ShareBean();
                shareBean.setUrl(Constants.share_url + "sharedetail/index?id=" + bean.getId());
                shareBean.setImageUrl(bean.getLogo());
                shareBean.setTitle(bean.getName());
                shareBean.setDescription(bean.getTitle());
                PopupWindowManager.getInstance(activity).showShare(view, activity, shareBean, (type, values) -> {

                });
                break;
            case R.id.ivFav:
                handleFav();
                break;
            case R.id.llLuci:
                if (list.isEmpty()) {
                    mDataManager.showToast("暂无轮次");
                    return;
                }
                List<String> items = new ArrayList<>();
                for (TagYouBean tagYouBean : list) {
                    items.add(tagYouBean.getName());
                }
                new UIActionSheetDialog(activity)
                        .builder()
                        .addSheetItemList(items, null, which -> {
                            tvLunci.setText(items.get(which));
                            roundsId = list.get(which).getId();
                        })
                        .show();
                break;
            case R.id.tvSubmit:
                if (StringUtil.isBlank(roundsId)) {
                    mDataManager.showToast("请选择轮次");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etPrice))) {
                    mDataManager.showToast("请输入金额");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("projectId", mDataManager.getValueFromView(etPrice));
                params.put("money", bean.getId());
                params.put("roundsId", roundsId);
                mModel.requestRongZiSubmit(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_PROJECT_RONGZI));
                        finish();
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
                break;
        }
    }

    private void handleFav() {
        if (!StringUtil.isBlank(bean.getIsCollected())) {
            if (bean.getIsCollected().equals("true")) {
                cancalCollect();
            } else {
                addCollect();
            }
        }
    }

    private void addCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        params.put("id", bean.getId());
        mModel.requestAddCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("收藏成功");
                bean.setIsCollected("true");
                ivFav.setImageResource(R.mipmap.shoucanghuang);
                tvCountFav.setText(StringUtil.getNumUP(mDataManager.getValueFromView(tvCountFav)));
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PROJECT));
            }
        });
    }

    private void cancalCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        params.put("id", bean.getId());
        mModel.requestCancalCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("取消收藏成功");
                bean.setIsCollected("false");
                ivFav.setImageResource(R.mipmap.shoucang);
                tvCountFav.setText(StringUtil.getNumDown(mDataManager.getValueFromView(tvCountFav)));
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PROJECT));
            }
        });
    }
}
