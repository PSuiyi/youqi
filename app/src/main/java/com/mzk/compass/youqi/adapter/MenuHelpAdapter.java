package com.mzk.compass.youqi.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MenuBean;
import com.mzk.compass.youqi.ui.help.TypeListAct;
import com.mzk.compass.youqi.ui.home.product.ProductListAct;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.List;

import butterknife.Bind;

public class MenuHelpAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.ivLogo)
    HttpImageView ivLogo;
    @Bind(R.id.tvName)
    TextView tvName;

    public MenuHelpAdapter(@Nullable List dataList) {
        super(R.layout.item_lv_menu_help, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean bean) {
        setOnItemClickListener(this);
        ivLogo.loadSquareImage(bean.getImage());
        if (bean.getId().equals("8")) {
            mDataManager.setValueToView(tvName, "更多分类");
        } else {
            mDataManager.setValueToView(tvName, bean.getName());
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (bean.getId().equals("8")) {
            gotoActivity(TypeListAct.class);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("cateId", bean.getId());
            gotoActivity(ProductListAct.class, bundle);
        }
    }
}
