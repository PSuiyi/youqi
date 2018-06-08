package com.mzk.compass.youqi.ui.home.people;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CommentAdapter;
import com.mzk.compass.youqi.adapter.DetailAdapter;
import com.mzk.compass.youqi.adapter.MenuAdapter;
import com.mzk.compass.youqi.adapter.TradeAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.CommentBean;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.bean.StateBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.umeng.socialize.UMShareAPI;
import com.znz.compass.umeng.share.ShareBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/4/5 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleDetailAct extends BaseAppListActivity<CommentBean> {
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
    @Bind(R.id.llOpt)
    LinearLayout llOpt;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.tvSendComment)
    TextView tvSendComment;
    @Bind(R.id.llComment)
    LinearLayout llComment;
    private RecyclerView rvDetail;
    private RecyclerView rvMenu;
    private TextView tvRecommend;

    private DetailAdapter detailAdapter;
    private String id;

    private HttpImageView ivUserHeader;
    private TextView tvName;
    private TextView tvTag1;
    private TextView tvTag2;
    private TextView tvCountFav;
    private TextView tvCountComment;
    private RecyclerView rvTrade;
    private ImageView ivShare;
    private ImageView ivFav;

    private PeopleBean bean;
    private String currentPid;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_people_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("投资人主页");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new CommentAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        View header = View.inflate(activity, R.layout.header_detail_people, null);
        adapter.addHeaderView(header);
        rvMenu = bindViewById(header, R.id.rvMenu);
        rvDetail = bindViewById(header, R.id.rvDetail);
        ivUserHeader = bindViewById(header, R.id.ivUserHeader);
        tvName = bindViewById(header, R.id.tvName);
        tvTag1 = bindViewById(header, R.id.tvTag1);
        tvTag2 = bindViewById(header, R.id.tvTag2);
        rvTrade = bindViewById(header, R.id.rvTrade);
        tvCountFav = bindViewById(header, R.id.tvCountFav);
        tvCountComment = bindViewById(header, R.id.tvCountComment);
        ivFav = bindViewById(header, R.id.ivFav);
        ivShare = bindViewById(header, R.id.ivShare);

        ivFav.setOnClickListener(v -> {
            handleFav();
        });
        ivShare.setOnClickListener(v -> {
            handleShare(v);
        });

        tvRecommend = bindViewById(header, R.id.tvRecommend);
        tvRecommend.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            bundle.putString("from", "投资人");
            gotoActivity(RecommendSelfAct.class, bundle);
        });

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            CommentBean bean = dataList.get(position);
            switch (view.getId()) {
                case R.id.tvReply:
                    mDataManager.setViewVisibility(llOpt, false);
                    mDataManager.setViewVisibility(llComment, true);
                    currentPid = bean.getId();
                    mDataManager.toggleEditTextFocus(etComment, true);
                    break;
            }
        });

        //菜单栏
        List<MenuBean> menuBeanList = new ArrayList<>();
        menuBeanList.add(new MenuBean("投资人动态"));
        menuBeanList.add(new MenuBean("简介"));
        menuBeanList.add(new MenuBean("评论"));
        MenuAdapter adapterMenu = new MenuAdapter(menuBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapterMenu);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("investorId", id);
        mModel.requestPeopleDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSONObject.parseObject(responseOriginal.getString("data"), PeopleBean.class);
                ivUserHeader.loadHeaderImage(bean.getAvatar());
                mDataManager.setValueToView(tvName, bean.getUsername());
                mDataManager.setValueToView(tvCountComment, bean.getCommentsNum());
                mDataManager.setValueToView(tvCountFav, bean.getCollectionNum());
                mDataManager.setValueToView(tvTag1, bean.getName());
                mDataManager.setValueToView(tvTag2, bean.getGroupName());

                if (bean.getTradeid() != null & bean.getTradeid().size() > 0) {
                    mDataManager.setViewVisibility(rvTrade, true);
                    TradeAdapter adapter = new TradeAdapter(bean.getTradeid());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rvTrade.setLayoutManager(layoutManager);
                    rvTrade.setAdapter(adapter);
                } else {
                    mDataManager.setViewVisibility(rvTrade, false);
                }

                if (!StringUtil.isBlank(bean.getIsCollected())) {
                    if (bean.getIsCollected().equals("true")) {
                        ivFav.setImageResource(R.mipmap.shoucanghuang);
                        Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang2);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tvOption3.setCompoundDrawables(null, drawable, null, null);
                    } else {
                        ivFav.setImageResource(R.mipmap.shoucang);
                        Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucangxia);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tvOption3.setCompoundDrawables(null, drawable, null, null);
                    }
                } else {
                    ivFav.setImageResource(R.mipmap.shoucang);
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucangxia);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption3.setCompoundDrawables(null, drawable, null, null);
                }

                Map<String, String> params = new HashMap<>();
                params.put("page", "0");
                params.put("pageSize", "3");
                params.put("investorId", id);
                mModel.requestPeopleStateList(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        List<StateBean> stateBeanList = JSONArray.parseArray(responseObject.getString("data"), StateBean.class);
                        //项目详情
                        rvDetail.setLayoutManager(new LinearLayoutManager(activity));
                        List<MultiBean> multiBeanList = new ArrayList<>();
                        if (!stateBeanList.isEmpty()) {
                            MultiBean multiBean = new MultiBean(Constants.MultiType.PeopleState);
                            multiBean.setStateBeanList(stateBeanList);
                            multiBeanList.add(multiBean);
                        }
                        multiBeanList.add(new MultiBean(Constants.MultiType.PeopleIntro, bean));
                        detailAdapter = new DetailAdapter(multiBeanList);
                        rvDetail.setAdapter(detailAdapter);

                        detailAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                            switch (view.getId()) {
                                case R.id.llMore:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", id);
                                    gotoActivity(PeopleStateAct.class, bundle);
                                    break;
                            }
                        });
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        params.put("type", "投资人");
        params.put("id", id);
        return mModel.requestCommentList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSONArray.parseArray(responseJson.getString("data"), CommentBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvSendComment, R.id.tvOption1, R.id.tvOption2, R.id.tvOption3, R.id.tvOption4, R.id.tvOption5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvOption1:
                mDataManager.setViewVisibility(llOpt, false);
                mDataManager.setViewVisibility(llComment, true);
                currentPid = "0";
                mDataManager.toggleEditTextFocus(etComment, true);
                break;
            case R.id.tvOption2:
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("from", "投资人");
                gotoActivity(RecommendSelfAct.class, bundle);
                break;
            case R.id.tvOption3:
                handleFav();
                break;
            case R.id.tvOption4:
                handleShare(view);
                break;
            case R.id.tvOption5:
                rvRefresh.smoothScrollToPosition(0);
                break;
            case R.id.tvSendComment:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etComment))) {
                    mDataManager.showToast("请输入评论内容");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("pid", currentPid);
                params.put("type", "投资人");
                params.put("id", id);
                params.put("content", mDataManager.getValueFromView(etComment));
                mModel.requestSendComment(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        etComment.setText("");
                        mDataManager.showToast("评论成功");
                        mDataManager.setViewVisibility(llOpt, true);
                        mDataManager.setViewVisibility(llComment, false);
                        resetRefresh();
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
                break;
        }
    }

    private void handleShare(View view) {
        ShareBean shareBean = new ShareBean();
        shareBean.setUrl(Constants.share_url + "sharedetail/investor?id=" + bean.getId());
        shareBean.setImageUrl(bean.getAvatar());
        shareBean.setTitle(bean.getUserName());
        shareBean.setDescription(bean.getName());
        PopupWindowManager.getInstance(activity).showShare(view, activity, shareBean, (type, values) -> {

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);//完成回调
    }


    private void handleFav() {
        if (bean.getIsCollected().equals("true")) {
            cancalCollect();
        } else {
            addCollect();
        }
    }

    private void addCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "2");
        params.put("id", id);
        mModel.requestAddCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang2);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption3.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("true");
                ivFav.setImageResource(R.mipmap.shoucanghuang);
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PEOPLE));
            }
        });
    }

    private void cancalCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "2");
        params.put("id", id);
        mModel.requestCancalCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("取消收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucangxia);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption3.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("false");
                ivFav.setImageResource(R.mipmap.shoucang);
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PEOPLE));
            }
        });
    }
}
