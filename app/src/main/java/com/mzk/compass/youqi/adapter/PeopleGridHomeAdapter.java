package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.ui.home.people.PeopleDetailAct;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.utils.DipUtil;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Date： 2017/5/4 2017
 * User： PSuiyi
 * Description：
 */

public class PeopleGridHomeAdapter extends BaseQuickAdapter<BaseZnzBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    public PeopleGridHomeAdapter(@Nullable List<BaseZnzBean> dataList) {
        super(R.layout.item_gv_people_home, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseZnzBean bean) {
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
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(PeopleDetailAct.class);
    }
}
