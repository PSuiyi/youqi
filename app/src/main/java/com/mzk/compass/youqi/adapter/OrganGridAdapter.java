package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.OrganBean;
import com.mzk.compass.youqi.ui.home.organ.OrganDetailAct;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Date： 2017/5/4 2017
 * User： PSuiyi
 * Description：
 */

public class OrganGridAdapter extends BaseQuickAdapter<OrganBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvIntro)
    TextView tvIntro;

    public OrganGridAdapter(@Nullable List dataList) {
        super(R.layout.item_gv_organ, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrganBean bean) {
        setOnItemClickListener(this);
        ivImage.loadSquareImage(bean.getImage());
        mDataManager.setValueToView(tvTitle, bean.getTitle());
        mDataManager.setValueToView(tvIntro, bean.getSummary());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", bean.getAdPositionid());
        gotoActivity(OrganDetailAct.class, bundle);
    }
}
