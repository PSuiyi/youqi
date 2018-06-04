package com.mzk.compass.youqi.adapter;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.ui.home.product.ProductDetailAct;
import com.znz.compass.znzlibray.utils.DipUtil;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class ProductAdapter extends BaseQuickAdapter<ProductBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.tvMoneyOld)
    TextView tvMoneyOld;
    @Bind(R.id.ivImage)
    HttpImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvMoney)
    TextView tvMoney;
    @Bind(R.id.tvCount)
    TextView tvCount;
    @Bind(R.id.llContainer)
    LinearLayout llContainer;

    public ProductAdapter(@Nullable List dataList) {
        super(R.layout.item_gv_service, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean bean) {
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

        if (!StringUtil.isBlank(bean.getTitle())) {
            mDataManager.setValueToView(tvTitle, bean.getTitle());
        } else {
            mDataManager.setValueToView(tvTitle, bean.getName());
        }
        mDataManager.setValueToView(tvMoney, "¥" + bean.getRealPrice());
        mDataManager.setValueToView(tvMoneyOld, "原价：¥" + bean.getMarketPrice());
        mDataManager.setValueToView(tvCount, "成交量：" + bean.getShowNum());
        ivImage.loadSquareImage(bean.getImage());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        if (!StringUtil.isBlank(bean.getId())) {
            bundle.putString("id", bean.getId());
        } else {
            bundle.putString("id", bean.getLink());
        }
        gotoActivity(ProductDetailAct.class, bundle);
    }
}
