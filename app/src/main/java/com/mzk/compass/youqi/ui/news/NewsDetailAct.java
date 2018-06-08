package com.mzk.compass.youqi.ui.news;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CommentAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.CommentBean;
import com.mzk.compass.youqi.bean.NewsBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.umeng.socialize.UMShareAPI;
import com.znz.compass.umeng.share.ShareBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.utils.ZnzLog;
import com.znz.compass.znzlibray.views.WebViewWithProgress;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
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
public class NewsDetailAct extends BaseAppListActivity<CommentBean> implements View.OnLayoutChangeListener {

    @Bind(R.id.tvOption1)
    TextView tvOption1;
    @Bind(R.id.tvOption2)
    TextView tvOption2;
    @Bind(R.id.tvOption3)
    TextView tvOption3;
    @Bind(R.id.tvOption4)
    TextView tvOption4;
    @Bind(R.id.llOpt)
    LinearLayout llOpt;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.tvSendComment)
    TextView tvSendComment;
    @Bind(R.id.llComment)
    LinearLayout llComment;
    @Bind(R.id.llRootView)
    LinearLayout llRootView;
    private View header;
    private String id;

    private TextView tvTitle;
    private TextView tvCountFav;
    private TextView tvCountComment;
    private TextView tvTime;
    private WebViewWithProgress wvContent;
    private NewsBean bean;
    private String currentPid = "0";
    private boolean isKeyboardOn;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_news_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("详情");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new CommentAdapter(dataList);
        rvRefresh.setAdapter(adapter);

        header = View.inflate(activity, R.layout.header_news_detail, null);
        adapter.addHeaderView(header);

        tvTitle = bindViewById(header, R.id.tvTitle);
        tvCountFav = bindViewById(header, R.id.tvCountFav);
        tvCountComment = bindViewById(header, R.id.tvCountComment);
        tvTime = bindViewById(header, R.id.tvTime);
        wvContent = bindViewById(header, R.id.wvContent);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
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

        llRootView.addOnLayoutChangeListener(this);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("newsId", id);
        mModel.requestNewsDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                bean = JSONObject.parseObject(responseOriginal.getString("data"), NewsBean.class);
                mDataManager.setValueToView(tvTitle, bean.getTitle());
                mDataManager.setValueToView(tvCountFav, bean.getCollectionNum());
                mDataManager.setValueToView(tvCountComment, bean.getVisiteNum());
                mDataManager.setValueToView(tvTime, TimeUtils.getFriendlyTimeSpanByNow(bean.getAddTime()));

                wvContent.loadContent(bean.getContent());
                if (bean.getIsCollected().equals("true")) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption2.setCompoundDrawables(null, drawable, null, null);
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucangxia);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption2.setCompoundDrawables(null, drawable, null, null);
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
        params.put("type", "优报道");
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

    private void addCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "5");
        params.put("id", id);
        mModel.requestAddCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang2);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption2.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("true");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_NEWS));
            }
        });
    }

    private void cancalCollect() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "5");
        params.put("id", id);
        mModel.requestCancalCollect(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("取消收藏成功");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucangxia);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvOption2.setCompoundDrawables(null, drawable, null, null);
                bean.setIsCollected("false");
                EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_COLLECT_NEWS));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvOption1, R.id.tvOption2, R.id.tvOption3, R.id.tvOption4, R.id.tvSendComment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvOption1:
                mDataManager.setViewVisibility(llOpt, false);
                mDataManager.setViewVisibility(llComment, true);
                currentPid = "0";
                mDataManager.toggleEditTextFocus(etComment, true);
                break;
            case R.id.tvOption2:
                if (bean.getIsCollected().equals("true")) {
                    cancalCollect();
                } else {
                    addCollect();
                }
                break;
            case R.id.tvOption3:
                ShareBean shareBean = new ShareBean();
                shareBean.setUrl(Constants.share_url + "sharedetail/news?id=" + bean.getId());
                shareBean.setImageUrl(bean.getImage());
                shareBean.setTitle(bean.getTitle());
                shareBean.setDescription(bean.getSummary());
                PopupWindowManager.getInstance(activity).showShare(view, activity, shareBean, (type, values) -> {

                });
                break;
            case R.id.tvOption4:
                break;
            case R.id.tvSendComment:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etComment))) {
                    mDataManager.showToast("请输入评论内容");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("pid", currentPid);
                params.put("type", "优报道");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);//完成回调
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > mDataManager.getDeviceHeight(activity) / 3)) {
            ZnzLog.e("监听到软键盘---->" + "弹起....");
            isKeyboardOn = true;
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > mDataManager.getDeviceHeight(activity) / 3)) {
            ZnzLog.e("监听到软键盘---->" + "关闭....");
            isKeyboardOn = false;
//            runOnUiThread(() -> {
//                mDataManager.setViewVisibility(llOpt, true);
//                mDataManager.setViewVisibility(llComment, false);
//            });
        }
    }
}
