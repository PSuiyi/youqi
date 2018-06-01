package com.mzk.compass.youqi.ui.mine.article;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.ArticleBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class ArticleDetailAct extends BaseAppActivity {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvState)
    TextView tvState;
    @Bind(R.id.tvContent)
    TextView tvContent;
    private String id;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_article_detail, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("投稿详情");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        mModel.requestArticleDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    ArticleBean bean = JSON.parseObject(responseOriginal.getString("data"), ArticleBean.class);
                    mDataManager.setValueToView(tvTitle, bean.getTitle());
                    mDataManager.setValueToView(tvContent, bean.getContent());
                    mDataManager.setValueToView(tvTime, TimeUtils.millis2String(StringUtil.stringToLong(bean.getAddTime()), "yyyy-MM-dd HH:mm"));
                    if (StringUtil.isBlank(bean.getState())) {
                        mDataManager.setViewVisibility(tvState, false);
                    } else {
                        mDataManager.setViewVisibility(tvState, true);
                        switch (bean.getState()) {
                            case "1":
                                mDataManager.setValueToView(tvState, "已采纳");
                                tvState.setTextColor(mDataManager.getColor(R.color.green));
                                break;
                            case "2":
                                mDataManager.setValueToView(tvState, "审核中");
                                tvState.setTextColor(mDataManager.getColor(R.color.red));
                                break;
                            case "3":
                                mDataManager.setValueToView(tvState, "已拒绝");
                                tvState.setTextColor(mDataManager.getColor(R.color.text_gray));
                                break;
                            default:
                                mDataManager.setValueToView(tvState, "已拒绝");
                                tvState.setTextColor(mDataManager.getColor(R.color.text_gray));
                                break;
                        }
                    }
                }
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
