package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MemberBean;
import com.mzk.compass.youqi.ui.mine.identify.personal.MemberDetailAct;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class MemberAdapter extends BaseQuickAdapter<MemberBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivHeader)
    HttpImageView ivHeader;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvStatus)
    TextView tvStatus;

    public MemberAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_menber, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberBean bean) {
        setOnItemClickListener(this);
        ivHeader.loadHeaderImage(bean.getAvatar());
        if (StringUtil.isBlank(bean.getUsername())) {
            mDataManager.setValueToView(tvName, bean.getInvitename());
        } else {
            mDataManager.setValueToView(tvName, bean.getUsername());
        }
        if (StringUtil.isBlank(bean.getState()) | bean.getState().equals("2")) {
            tvStatus.setText("未激活");
        } else {
            tvStatus.setText("已激活");
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("from", "编辑");
        bundle.putSerializable("bean", bean);
        gotoActivity(MemberDetailAct.class, bundle);
    }
}
