package com.mzk.compass.youqi.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.utils.DipUtil;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class ProductAdapter extends BaseQuickAdapter<BaseZnzBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvMoneyOld)
    TextView tvMoneyOld;

    public ProductAdapter(@Nullable List dataList) {
        super(R.layout.item_gv_service, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseZnzBean bean) {
        setOnItemClickListener(this);

        tvMoneyOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //下划线
        tvMoneyOld.getPaint().setAntiAlias(true);//抗锯齿

        LinearLayout llContainer = helper.getView(R.id.llContainer);
        if ((helper.getAdapterPosition() - getHeaderLayoutCount()) % 2 == 0) {
            llContainer.setPadding(DipUtil.dip2px(15),
                    DipUtil.dip2px(5),
                    DipUtil.dip2px(5),
                    DipUtil.dip2px(5));
        } else {
            llContainer.setPadding(DipUtil.dip2px(5),
                    DipUtil.dip2px(5),
                    DipUtil.dip2px(15),
                    DipUtil.dip2px(5));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(ProjectDetailAct.class);
    }
}
