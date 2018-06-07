package com.mzk.compass.youqi.ui.home.project;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.mzk.compass.youqi.adapter.PeopleGridAdapter;
import com.mzk.compass.youqi.adapter.TradeAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.CommentBean;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
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
public class ProjectDetailAct extends BaseAppListActivity<CommentBean> {
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
    private String currentPid;

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

        ivFav.setOnClickListener(v -> {
            handleFav();
        });

        ivShare.setOnClickListener(v -> {
            handleShare(v);
        });

        llMore.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            gotoActivity(PeopleViewAct.class, bundle);
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
        MenuAdapter adapterMenu = new MenuAdapter(menuBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapterMenu);

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
                ivImage.loadSquareImage(bean.getLogo());
                ivLogo.loadSquareImage(bean.getCompanyLogo());
                mDataManager.setValueToView(tvName, bean.getName());
                mDataManager.setValueToView(tvTag, bean.getRounds().getName());
                mDataManager.setValueToView(tvContent, bean.getTitle());
                mDataManager.setValueToView(tvCountFav, bean.getCollectionNum());
                mDataManager.setValueToView(tvCountComment, bean.getCommentsNum());
                mDataManager.setValueToView(tvCountView, bean.getVisiteNum());
                mDataManager.setValueToView(tvCompanyName, bean.getCompanyName());
                mDataManager.setValueToView(tvShizhi, bean.getRongzijine());
                mDataManager.setValueToView(tvMoney, bean.getTurnover().getName());


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

                //项目详情
                rvProject.setLayoutManager(new LinearLayoutManager(activity));
                List<MultiBean> multiBeanList = new ArrayList<>();
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectIntro, bean));
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectTeam, bean));
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectProduct, bean));
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectMarket, bean));
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectSolution, bean));
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectMoney, bean));
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectFinancing, bean));
                multiBeanList.add(new MultiBean(Constants.MultiType.ProjectData, bean));
                detailAdapter = new DetailAdapter(multiBeanList);
                rvProject.setAdapter(detailAdapter);
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });

        Map<String, String> params1 = new HashMap<>();
        params1.put("page", "0");
        params1.put("pageSize", "6");
        params1.put("projectId", id);
        mModel.requestPeopleViewList(params1, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                userList.clear();
                userList.addAll(JSONArray.parseArray(responseObject.getString("data"), PeopleBean.class));
                PeopleGridAdapter peopleGridAdapter = new PeopleGridAdapter(userList);
                rvPeople.setLayoutManager(new GridLayoutManager(activity, 6));
                rvPeople.setAdapter(peopleGridAdapter);
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
                if (!StringUtil.isBlank(bean.getCanArrangeTalk()) && bean.getCanArrangeTalk().equals("true")) {
                    PopupWindowManager.getInstance(activity).showChatProject(view, (type, values) -> {
                        switch (type) {
                            case "站内信":
                                Bundle bundle = new Bundle();
                                bundle.putString("id", id);
                                gotoActivity(SendMessageAct.class, bundle);
                                break;
                            case "电话":
                                mDataManager.callPhone(activity, bean.getTel());
                                break;
                        }
                    });
                } else {
                    mDataManager.showToast("暂时无法约谈");
                }
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
                params.put("type", "项目");
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
        PopupWindowManager.getInstance(activity).showShare(view, (type, values) -> {

        });
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
        params.put("type", "1");
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
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucangxia);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption3.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("false");
                ivFav.setImageResource(R.mipmap.shoucang);
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_PROJECT));
            }
        });
    }
}
