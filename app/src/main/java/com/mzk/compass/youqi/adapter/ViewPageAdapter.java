package com.mzk.compass.youqi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;

import java.util.List;

/**
 * User： PSuiyi
 * Description：公共viewpager适配器
 */

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    private List<String> tabNames;
    private List<Fragment> fragments;

    public ViewPageAdapter(FragmentManager fm, List<String> tabNames, List<Fragment> fragments) {
        super(fm);
        this.tabNames = tabNames;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 这个函数就是给TabLayout的Tab设定Title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }

    public View getTabView(Context context, int position) {
        View tabView = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tvTitle = (TextView) tabView.findViewById(R.id.tvTitle);
        tvTitle.setText(tabNames.get(position));
        return tabView;
    }
}
