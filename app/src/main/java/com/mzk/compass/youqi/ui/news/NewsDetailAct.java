package com.mzk.compass.youqi.ui.news;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.CommentAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.CommentBean;
import com.mzk.compass.youqi.bean.NewsBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
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
public class NewsDetailAct extends BaseAppListActivity {

    @Bind(R.id.tvOption1)
    TextView tvOption1;
    @Bind(R.id.tvOption2)
    TextView tvOption2;
    @Bind(R.id.tvOption3)
    TextView tvOption3;
    @Bind(R.id.tvOption4)
    TextView tvOption4;
    private View header;
    private String id;

    private TextView tvTitle;
    private TextView tvCountFav;
    private TextView tvCountComment;
    private TextView tvTime;
    private WebViewWithProgress wvContent;
    private NewsBean bean;

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
                wvContent.loadContent(bean.getSummary());
                if (bean.getIsCollected().equals("true")) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvOption2.setCompoundDrawables(null, drawable, null, null);
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
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
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucanghuang);
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
                Drawable drawable = context.getResources().getDrawable(R.mipmap.shoucang);
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

    @OnClick({R.id.tvOption1, R.id.tvOption2, R.id.tvOption3, R.id.tvOption4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvOption1:
                break;
            case R.id.tvOption2:
                if (bean.getIsCollected().equals("true")) {
                    cancalCollect();
                } else {
                    addCollect();
                }
                break;
            case R.id.tvOption3:
                break;
            case R.id.tvOption4:
                break;
        }
    }
}
