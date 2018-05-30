package com.mzk.compass.youqi.ui.home.people;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
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
public class PeopleDetailAct extends BaseAppListActivity {
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

    private PeopleBean bean;

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

        tvRecommend = bindViewById(header, R.id.tvRecommend);
        tvRecommend.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            bundle.putString("from", "投资人");
            gotoActivity(RecommendSelfAct.class, bundle);
        });

        //菜单栏
        List<MenuBean> menuBeanList = new ArrayList<>();
        menuBeanList.add(new MenuBean("投资人动态"));
        menuBeanList.add(new MenuBean("简介"));
        menuBeanList.add(new MenuBean("评论"));
        MenuAdapter adapter = new MenuAdapter(menuBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapter);


        //项目详情
        rvDetail.setLayoutManager(new LinearLayoutManager(activity));
        List<MultiBean> multiBeanList = new ArrayList<>();
        multiBeanList.add(new MultiBean(Constants.MultiType.PeopleState));
        multiBeanList.add(new MultiBean(Constants.MultiType.PeopleIntro));
        detailAdapter = new DetailAdapter(multiBeanList);
        rvDetail.setAdapter(detailAdapter);
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
                if (bean.getIsCollected().equals("true")) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption3.setCompoundDrawables(null, drawable, null, null);
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption3.setCompoundDrawables(null, drawable, null, null);
                }
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

    @OnClick({R.id.tvOption1, R.id.tvOption2, R.id.tvOption3, R.id.tvOption4, R.id.tvOption5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvOption1:
                break;
            case R.id.tvOption2:
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("from", "投资人");
                gotoActivity(RecommendSelfAct.class, bundle);
                break;
            case R.id.tvOption3:
                if (bean.getIsCollected().equals("true")) {
                    cancalCollect();
                } else {
                    addCollect();
                }
                break;
            case R.id.tvOption4:
                break;
            case R.id.tvOption5:
                rvRefresh.smoothScrollToPosition(0);
                break;
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
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption3.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("true");
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
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption3.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("false");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PEOPLE));
            }
        });
    }
}
