package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.IndustryBean;
import com.mzk.compass.youqi.bean.InteractMsgDetailBean;
import com.znz.compass.znzlibray.bean.TagBean;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.TimeUtils;
import com.znz.compass.znzlibray.views.ZnzTagView;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MessageInteractAct extends BaseAppActivity {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvTag)
    TextView tvTag;
    @Bind(R.id.tvDiscribe)
    TextView tvDiscribe;
    @Bind(R.id.ivHeader)
    HttpImageView ivHeader;
    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tagView)
    ZnzTagView tagView;
    private InteractMsgDetailBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_message_interact, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("bean")) {
            bean = (InteractMsgDetailBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("消息详情");
        znzToolBar.setNavRightText("回复");
        znzToolBar.setOnNavRightClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("bean", bean);
            gotoActivity(MessageReplyAct.class, bundle);
        });
    }

    @Override
    protected void initializeView() {
        ivImage.loadHttpImage(bean.getLogo());
        mDataManager.setValueToView(tvTitle, bean.getProjectName());
        mDataManager.setValueToView(tvTag, bean.getName());
        mDataManager.setValueToView(tvDiscribe, bean.getProjectTitle());
        mDataManager.setValueToView(tvUserName, bean.getUsername());
        mDataManager.setValueToView(tvContent, bean.getContent());
        mDataManager.setValueToView(tvTime, TimeUtils.millis2String(StringUtil.stringToLong(bean.getAddTime()) * 1000, "yyyy.MM.dd HH:mm"));
        ivHeader.loadHeaderImage(bean.getAvatar());
        if (bean.getTradeid() != null & !bean.getTradeid().isEmpty()) {
            List<TagBean> list = new ArrayList<>();
            for (IndustryBean industryBean : bean.getTradeid()) {
                TagBean tagBean = new TagBean();
                tagBean.setId(industryBean.getId());
                tagBean.setTitle(industryBean.getName());
                list.add(tagBean);
            }
            tagView.setDataList(list);
        }
    }

    @Override
    protected void loadDataFromServer() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
