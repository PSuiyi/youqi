package com.mzk.compass.youqi.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.TagYouBean;
import com.znz.compass.znzlibray.utils.DipUtil;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class TradeAdapter extends BaseQuickAdapter<TagYouBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvCategory)
    TextView tvCategory;

    public TradeAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_trade, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagYouBean bean) {
        mDataManager.setValueToView(tvCategory, bean.getName());

        if (!StringUtil.isBlank(bean.getFontcolor())) {
            String[] co = bean.getFontcolor().split(",");
            int r = StringUtil.stringToInt(co[0]);
            int g = StringUtil.stringToInt(co[1]);
            int b = StringUtil.stringToInt(co[2]);
            tvCategory.setTextColor(Color.rgb(r, g, b));
        } else {
            tvCategory.setTextColor(mDataManager.getColor(R.color.blue));
        }

        if (!StringUtil.isBlank(bean.getBackgroundcolor())) {
            String[] co = bean.getBackgroundcolor().split(",");
            int r = StringUtil.stringToInt(co[0]);
            int g = StringUtil.stringToInt(co[1]);
            int b = StringUtil.stringToInt(co[2]);
            GradientDrawable gd = (GradientDrawable) tvCategory.getBackground();
            int roundRadius = 2; // 8dp 圆角半径
            gd.setColor(Color.rgb(r, g, b));
            gd.setCornerRadius(DipUtil.dip2px(roundRadius));
        } else {
            tvCategory.setBackgroundResource(R.drawable.bg_round_blue_2);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
