package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.CommentBean;
import com.mzk.compass.youqi.bean.DocBean;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.home.people.PeopleApproveAct;
import com.mzk.compass.youqi.ui.home.project.DocAct;
import com.mzk.compass.youqi.ui.mine.vip.VipCenterAct;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseMultiItemQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;
import com.znz.libvideo.listener.SampleListener;
import com.znz.libvideo.videoplayer.builder.GSYVideoOptionBuilder;
import com.znz.libvideo.videoplayer.listener.LockClickListener;
import com.znz.libvideo.videoplayer.utils.Debuger;
import com.znz.libvideo.videoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

public class DetailAdapter extends BaseMultiItemQuickAdapter<MultiBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    public DetailAdapter(List dataList) {
        super(dataList);
        addItemType(Constants.MultiType.ProjectIntro, R.layout.item_lv_project_intro);
        addItemType(Constants.MultiType.ProjectTeam, R.layout.item_lv_project_team);
        addItemType(Constants.MultiType.ProjectProduct, R.layout.item_lv_project_product);
        addItemType(Constants.MultiType.ProjectMarket, R.layout.item_lv_project_market);
        addItemType(Constants.MultiType.ProjectSolution, R.layout.item_lv_project_solution);
        addItemType(Constants.MultiType.ProjectMoney, R.layout.item_lv_project_money);
        addItemType(Constants.MultiType.ProjectFinancing, R.layout.item_lv_project_financing);
        addItemType(Constants.MultiType.ProjectData, R.layout.item_lv_project_data);
        addItemType(Constants.MultiType.ProjectCommentSection, R.layout.item_lv_comment_section);
        addItemType(Constants.MultiType.ProjectComment, R.layout.item_lv_comment);
        addItemType(Constants.MultiType.ProjectCommentNoData, R.layout.item_lv_comment_nodata);

        addItemType(Constants.MultiType.PeopleState, R.layout.item_lv_people_state);
        addItemType(Constants.MultiType.PeopleIntro, R.layout.item_lv_people_intro);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBean bean) {
        setOnItemClickListener(this);
        switch (bean.getItemType()) {
            case Constants.MultiType.ProjectIntro:
                WebViewWithProgress wvDeIntro = helper.getView(R.id.wvIntro);
                wvDeIntro.loadContent(bean.getProjectBean().getProjectProfile());
                break;
            case Constants.MultiType.ProjectTeam:
                PeopleTeamAdapter teamAdapter = new PeopleTeamAdapter(bean.getProjectBean().getTeam());
                RecyclerView rvTeam = helper.getView(R.id.rvRecycler);
                LinearLayoutManager teamLayoutManager = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rvTeam.setLayoutManager(teamLayoutManager);
                rvTeam.setAdapter(teamAdapter);
                break;
            case Constants.MultiType.ProjectProduct:
                WebViewWithProgress wvProduct = helper.getView(R.id.wvProduct);
                wvProduct.loadContent(bean.getProjectBean().getProductshape());
                break;
            case Constants.MultiType.ProjectMarket:
                WebViewWithProgress wvMarket = helper.getView(R.id.wvMarket);
                wvMarket.loadContent(bean.getProjectBean().getMarket());
                break;
            case Constants.MultiType.ProjectSolution:
                WebViewWithProgress wvSolution = helper.getView(R.id.wvSolution);
                wvSolution.loadContent(bean.getProjectBean().getSolusion());
                break;
            case Constants.MultiType.ProjectMoney:
                WebViewWithProgress wvMoney = helper.getView(R.id.wvMoney);
                wvMoney.loadContent(bean.getProjectBean().getProfitModel());
                break;
            case Constants.MultiType.ProjectFinancing:
                WebViewWithProgress wvFinan = helper.getView(R.id.wvFinan);
                wvFinan.loadContent(bean.getProjectBean().getFinancing());
                break;
            case Constants.MultiType.ProjectData:
                LinearLayout llViewDoc = helper.getView(R.id.llViewDoc);
                TextView tvViewDoc = helper.getView(R.id.tvViewDoc);
                TextView tvNoDoc = helper.getView(R.id.tvNoDoc);
                LinearLayout llNoVip = helper.getView(R.id.llNoVip);
                StandardGSYVideoPlayer detailPlayer = helper.getView(R.id.detailPlayer);

                helper.setOnClickListener(R.id.tvRenzheng, v -> {
                    gotoActivity(PeopleApproveAct.class);
                });
                helper.setOnClickListener(R.id.tvVip, v -> {
                    gotoActivity(VipCenterAct.class);
                });

                if (StringUtil.isBlank(bean.getProjectBean().getShowProjectResource())
                        || bean.getProjectBean().getShowProjectResource().equals("false")) {
                    mDataManager.setViewVisibility(llNoVip, true);
                    mDataManager.setViewVisibility(llViewDoc, false);
                    mDataManager.setViewVisibility(detailPlayer, false);
                    return;
                }

                RadioGroup rbGroup = helper.getView(R.id.rbGroup);
                rbGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.rb1:
                            mDataManager.setViewVisibility(llViewDoc, true);
                            mDataManager.setViewVisibility(detailPlayer, false);
                            handleDoc(bean, tvViewDoc, tvNoDoc, "2");
                            break;
                        case R.id.rb2:
                            mDataManager.setViewVisibility(llViewDoc, false);
                            mDataManager.setViewVisibility(detailPlayer, true);
                            break;
                        case R.id.rb3:
                            mDataManager.setViewVisibility(llViewDoc, true);
                            mDataManager.setViewVisibility(detailPlayer, false);
                            handleDoc(bean, tvViewDoc, tvNoDoc, "3");
                            break;
                        case R.id.rb4:
                            mDataManager.setViewVisibility(llViewDoc, true);
                            mDataManager.setViewVisibility(detailPlayer, false);
                            handleDoc(bean, tvViewDoc, tvNoDoc, "1");
                            break;
                    }
                });

                switch (rbGroup.getCheckedRadioButtonId()) {
                    case R.id.rb1:
                        handleDoc(bean, tvViewDoc, tvNoDoc, "2");
                        break;
                    case R.id.rb2:
                        break;
                    case R.id.rb3:
                        handleDoc(bean, tvViewDoc, tvNoDoc, "3");
                        break;
                    case R.id.rb4:
                        handleDoc(bean, tvViewDoc, tvNoDoc, "1");
                        break;
                }


                HttpImageView ivImage = new HttpImageView(mContext);
                ivImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ivImage.loadRectImage(bean.getProjectBean().getRoadshowVideoPic());

                GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
                gsyVideoOption.setThumbImageView(ivImage)
                        .setIsTouchWiget(true)
                        .setRotateViewAuto(false)
                        .setLockLand(false)
                        .setShowFullAnimation(false)
                        .setNeedLockFull(true)
                        .setEnlargeImageRes(R.mipmap.icon_qunpin)
                        .setShrinkImageRes(R.mipmap.icon_qunpin)
                        .setSeekRatio(1)
                        .setUrl(ZnzConstants.IMAGE_ULR + bean.getProjectBean().getRoadshowVideo())
                        .setCacheWithPlay(false)
                        .setStandardVideoAllCallBack(new SampleListener() {
                            @Override
                            public void onPrepared(String url, Object... objects) {
                                Debuger.printfError("***** onPrepared **** " + objects[0]);
                                Debuger.printfError("***** onPrepared **** " + objects[1]);
                                super.onPrepared(url, objects);
                            }

                            @Override
                            public void onEnterFullscreen(String url, Object... objects) {
                                super.onEnterFullscreen(url, objects);
                                Debuger.printfError("***** onEnterFullscreen **** " + objects[0]);//title
                                Debuger.printfError("***** onEnterFullscreen **** " + objects[1]);//当前全屏player
                            }

                            @Override
                            public void onAutoComplete(String url, Object... objects) {
                                super.onAutoComplete(url, objects);
                            }

                            @Override
                            public void onClickStartError(String url, Object... objects) {
                                super.onClickStartError(url, objects);
                            }

                            @Override
                            public void onQuitFullscreen(String url, Object... objects) {
                                super.onQuitFullscreen(url, objects);
                                Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                                Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
                            }
                        })
                        .setLockClickListener(new LockClickListener() {
                            @Override
                            public void onClick(View view, boolean lock) {
                            }
                        }).build(detailPlayer);
                break;
            case Constants.MultiType.PeopleState:
                helper.addOnClickListener(R.id.llMore);
                StateAdapter stateAdapter = new StateAdapter(bean.getStateBeanList());
                RecyclerView rvState = helper.getView(R.id.rvRecycler);
                LinearLayoutManager proLayoutManager = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rvState.setLayoutManager(proLayoutManager);
                rvState.setAdapter(stateAdapter);
                break;
            case Constants.MultiType.PeopleIntro:
                WebViewWithProgress wvIntro = helper.getView(R.id.wvIntro);
                wvIntro.loadContent(bean.getPeopleBean().getExample());
                break;
            case Constants.MultiType.ProjectComment:
                CommentBean commentBean = bean.getCommentBean();
                RecyclerView rvReply = helper.getView(R.id.rvReply);
                if (commentBean.getReply() != null && !commentBean.getReply().isEmpty()) {
                    helper.setVisible(R.id.ivBg, true);
                    helper.setVisible(R.id.llBg, true);
                    helper.setVisible(R.id.rvReply, true);
                    CommentReplyAdapter adapter = new CommentReplyAdapter(commentBean.getReply());
                    rvReply.setLayoutManager(new LinearLayoutManager(mContext));
                    rvReply.setAdapter(adapter);
                } else {
                    helper.setVisible(R.id.ivBg, false);
                    helper.setVisible(R.id.llBg, false);
                    rvReply.setVisibility(View.GONE);
                }

                helper.addOnClickListener(R.id.tvReply);
                helper.setText(R.id.tvUserName, commentBean.getUsername());
                helper.setText(R.id.tvContent, commentBean.getContent());
                helper.setText(R.id.tvTime, TimeUtils.getFriendlyTimeSpanByNow(commentBean.getAddTime()));
                HttpImageView ivUserHeader = helper.getView(R.id.ivUserHeader);
                ivUserHeader.loadHeaderImage(commentBean.getAvatar());
                break;
        }
    }

    private void handleDoc(MultiBean bean, TextView tvViewDoc, TextView tvNoDoc, String type) {
        if (bean.getProjectBean().getAttachment() == null) {
            mDataManager.setViewVisibility(tvNoDoc, true);
            mDataManager.setViewVisibility(tvViewDoc, false);
        } else {
            if (!bean.getProjectBean().getAttachment().isEmpty()) {
                //文件类型，1.商业计划书 2.项目相关资料 3.财务报表
                DocBean docBean = hasDoc(bean, type);
                if (docBean != null) {
                    mDataManager.setViewVisibility(tvNoDoc, false);
                    mDataManager.setViewVisibility(tvViewDoc, true);
                    tvViewDoc.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bean", docBean);
                        gotoActivity(DocAct.class, bundle);
                    });
                } else {
                    mDataManager.setViewVisibility(tvNoDoc, true);
                    mDataManager.setViewVisibility(tvViewDoc, false);
                }
            } else {
                mDataManager.setViewVisibility(tvNoDoc, true);
                mDataManager.setViewVisibility(tvViewDoc, false);
            }
        }
    }

    /**
     * 判断是否有文档
     *
     * @param bean
     * @param type
     * @return
     */
    private DocBean hasDoc(MultiBean bean, String type) {
        //文件类型，1.商业计划书 2.项目相关资料 3.财务报表
        for (DocBean docBean : bean.getProjectBean().getAttachment()) {
            if (docBean.getFiletype().equals(type)) {
                return docBean;
            }
        }
        return null;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (bean.getItemType()) {
            case Constants.MultiType.Section:
                break;
            case Constants.MultiType.Project:
                break;
        }
    }

}
