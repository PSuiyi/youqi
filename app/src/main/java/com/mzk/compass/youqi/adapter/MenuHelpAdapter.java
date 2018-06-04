package com.mzk.compass.youqi.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.bean.MenuBean;
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
        mDataManager.setValueToView(tvName, bean.getName());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}