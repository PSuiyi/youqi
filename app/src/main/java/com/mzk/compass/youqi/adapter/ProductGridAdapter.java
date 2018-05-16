package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.ProductBean;
import com.mzk.compass.youqi.ui.help.ProductDetailAct;
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

public class ProductGridAdapter extends BaseQuickAdapter<ProductBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivImage)
    HttpImageView ivImage;

    public ProductGridAdapter(@Nullable List dataList) {
        super(R.layout.item_gv_product, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean bean) {
        setOnItemClickListener(this);

        LinearLayout llContainer = helper.getView(R.id.llContainer);
        int width = mDataManager.getDeviceWidth(mContext) / 2 - DipUtil.dip2px(20);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (helper.getLayoutPosition() == 0) {
            layoutParams.leftMargin = DipUtil.dip2px(15);
            layoutParams.rightMargin = DipUtil.dip2px(5);
        } else {
            layoutParams.leftMargin = DipUtil.dip2px(5);
            layoutParams.rightMargin = DipUtil.dip2px(5);
        }
        llContainer.setLayoutParams(layoutParams);

        ivImage.loadRectImage(bean.getImage());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        gotoActivity(ProductDetailAct.class);
    }
}
