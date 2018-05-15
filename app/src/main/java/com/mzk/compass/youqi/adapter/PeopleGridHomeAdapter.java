package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.PeopleBean;
import com.mzk.compass.youqi.ui.home.people.PeopleDetailAct;
import com.znz.compass.znzlibray.utils.DipUtil;
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

public class PeopleGridHomeAdapter extends BaseQuickAdapter<PeopleBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvOrganName)
    TextView tvOrganName;
    @Bind(R.id.tvTag)
    TextView tvTag;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;

    public PeopleGridHomeAdapter(@Nullable List dataList) {
        super(R.layout.item_gv_people_home, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeopleBean bean) {
        setOnItemClickListener(this);

        LinearLayout llContainer = helper.getView(R.id.llContainer);
        int width = mDataManager.getDeviceWidth(mContext) / 3 - DipUtil.dip2px(11);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (helper.getLayoutPosition() == 0) {
            layoutParams.leftMargin = DipUtil.dip2px(15);
            layoutParams.rightMargin = DipUtil.dip2px(5);
        } else {
            layoutParams.leftMargin = DipUtil.dip2px(5);
            layoutParams.rightMargin = DipUtil.dip2px(5);
        }
        llContainer.setLayoutParams(layoutParams);

        ivImage.loadHeaderImage(bean.getAvatar());
        mDataManager.setValueToView(tvUserName, bean.getTitle());
        mDataManager.setValueToView(tvOrganName, bean.getGroupName());
        mDataManager.setValueToView(tvTag, bean.getIntroduce());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(PeopleDetailAct.class);
    }
}
