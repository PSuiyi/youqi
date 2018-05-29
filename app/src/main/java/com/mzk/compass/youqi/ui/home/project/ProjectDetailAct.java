package com.mzk.compass.youqi.ui.home.project;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CommentAdapter;
import com.mzk.compass.youqi.adapter.DetailAdapter;
import com.mzk.compass.youqi.adapter.MenuAdapter;
import com.mzk.compass.youqi.adapter.PeopleGridAdapter;
import com.mzk.compass.youqi.adapter.TradeAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.bean.NewsBean;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.home.people.PeopleListAct;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
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
public class ProjectDetailAct extends BaseAppListActivity {
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
    private RecyclerView rvPeople;
    private LinearLayout llMore;
    private RecyclerView rvMenu;
    private RecyclerView rvProject;

    private DetailAdapter detailAdapter;
    private String id;
    private ProjectBean bean;
    private List<PeopleBean> userList = new ArrayList<>();

    private HttpImageView ivImage;
    private HttpImageView ivLogo;
    private TextView tvName;
    private TextView tvTag;
    private TextView tvContent;
    private TextView tvCompanyName;
    private TextView tvShizhi;
    private TextView tvCountFav;
    private TextView tvCountComment;
    private TextView tvCountView;
    private ImageView ivShare;
    private ImageView ivFav;
    private TextView tvAddress;
    private TextView tvState;
    private TextView tvMoney;
    private TextView tvTime;
    private TextView tvCompany;
    private RecyclerView rvTrade;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_project_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("项目详情");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new CommentAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        View header = View.inflate(activity, R.layout.header_detail_project, null);
        adapter.addHeaderView(header);
        llMore = bindViewById(header, R.id.llMore);
        rvMenu = bindViewById(header, R.id.rvMenu);
        rvPeople = bindViewById(header, R.id.rvPeople);
        rvProject = bindViewById(header, R.id.rvProject);

        ivImage = bindViewById(header, R.id.ivImage);
        ivLogo = bindViewById(header, R.id.ivLogo);
        tvName = bindViewById(header, R.id.tvName);
        tvAddress = bindViewById(header, R.id.tvAddress);
        tvCompany = bindViewById(header, R.id.tvCompany);
        tvContent = bindViewById(header, R.id.tvContent);
        tvCompanyName = bindViewById(header, R.id.tvCompanyName);
        tvCountComment = bindViewById(header, R.id.tvCountComment);
        tvCountFav = bindViewById(header, R.id.tvCountFav);
        tvCountView = bindViewById(header, R.id.tvCountView);
        tvMoney = bindViewById(header, R.id.tvMoney);
        ivShare = bindViewById(header, R.id.ivShare);
        ivFav = bindViewById(header, R.id.ivFav);
        tvTime = bindViewById(header, R.id.tvTime);
        tvTag = bindViewById(header, R.id.tvTag);
        rvTrade = bindViewById(header, R.id.rvTrade);
        tvShizhi = bindViewById(header, R.id.tvShizhi);
        tvState = bindViewById(header, R.id.tvState);

        PeopleGridAdapter peopleGridAdapter = new PeopleGridAdapter(userList);
        rvPeople.setLayoutManager(new GridLayoutManager(activity, 6));
        rvPeople.setAdapter(peopleGridAdapter);

        llMore.setOnClickListener(v -> {
            gotoActivity(PeopleListAct.class);
        });


        //菜单栏
        List<MenuBean> menuBeanList = new ArrayList<>();
        menuBeanList.add(new MenuBean("项目简介"));
        menuBeanList.add(new MenuBean("团队介绍"));
        menuBeanList.add(new MenuBean("产品形态"));
        menuBeanList.add(new MenuBean("市场分析"));
        menuBeanList.add(new MenuBean("解决方案"));
        menuBeanList.add(new MenuBean("盈利模式"));
        menuBeanList.add(new MenuBean("项目资料"));
        menuBeanList.add(new MenuBean("项目评论"));
        MenuAdapter adapter = new MenuAdapter(menuBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapter);


        //项目详情
        rvProject.setLayoutManager(new LinearLayoutManager(activity));
        List<MultiBean> multiBeanList = new ArrayList<>();
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectIntro));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectTeam));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectProduct));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectMarket));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectSolution));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectMoney));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectFinancing));
        multiBeanList.add(new MultiBean(Constants.MultiType.ProjectData));
        detailAdapter = new DetailAdapter(multiBeanList);
        rvProject.setAdapter(detailAdapter);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("projectId", id);
        mModel.requestProjectDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSONObject.parseObject(responseOriginal.getString("data"), ProjectBean.class);
                ivImage.loadHttpImage(bean.getLogo());
                ivLogo.loadHeaderImage(bean.getCompanyLogo());
                mDataManager.setValueToView(tvName, bean.getName());
                mDataManager.setValueToView(tvTag, bean.getRname());
                mDataManager.setValueToView(tvContent, bean.getTitle());
                mDataManager.setValueToView(tvCountFav, bean.getCollectionNum());
                mDataManager.setValueToView(tvCountComment, bean.getCommentsNum());
                mDataManager.setValueToView(tvCountView, bean.getVisiteNum());
                mDataManager.setValueToView(tvCompanyName, bean.getCompanyName());
                mDataManager.setValueToView(tvShizhi, bean.getRongzijine());



                mDataManager.setValueToView(tvAddress, bean.getProvince() + bean.getCity() + bean.getArea() + bean.getAddress());
                //运营状态 1运营中 2 已运营 3 未运营
                if (!StringUtil.isBlank(bean.getRunState())) {
                    switch (bean.getRunState()) {
                        case "1":
                            tvState.setText("运营中");
                            break;
                        case "2":
                            tvState.setText("已运营");
                            break;
                        case "3":
                            tvState.setText("未运营");
                            break;
                    }
                } else {
                    tvState.setText("未运营");
                }

                mDataManager.setValueToView(tvCompany, bean.getCompanyName());
                mDataManager.setValueToView(tvTime, TimeUtils.getFormatTime(bean.getCreateTime(), "yyyy-MM-dd"));


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
        params.put("type", "项目");
        params.put("id", id);
        return mModel.requestCommentList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        dataList.addAll(JSONArray.parseArray(responseJson.getString("data"), NewsBean.class));
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
                PopupWindowManager.getInstance(activity).showChatProject(view, new PopupWindowManager.OnPopupWindowClickListener() {
                    @Override
                    public void onPopupWindowClick(String type, String[] values) {

                    }
                });
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
        params.put("type", "1");
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
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PROJECT));
            }
        });
    }

    private void cancalCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
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
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PROJECT));
            }
        });
    }
}
