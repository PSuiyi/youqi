package com.mzk.compass.youqi.ui.home.project;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.ProjectAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.ProjectBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class ProjectListFrag extends BaseAppListFragment<ProjectBean> {
    private String from;

    public static ProjectListFrag newInstance(String from) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        ProjectListFrag fragment = new ProjectListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_list_layout};
    }

    @Override
    protected void initializeVariate() {
        if (getArguments() != null) {
            from = getArguments().getString("from");
        }
    }

    @Override
    protected void initializeNavigation() {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        adapter = new ProjectAdapter(dataList);
        rvRefresh.setAdapter(adapter);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        switch (from) {
            case "全部":
                params.put("state", "0");
                return mModel.requestProjectMineList(params);
            case "待审核":
                params.put("state", "1");
                return mModel.requestProjectMineList(params);
            case "已发布":
                params.put("state", "2");
                return mModel.requestProjectMineList(params);
            case "已拒绝":
                params.put("state", "4");
                return mModel.requestProjectMineList(params);
            case "已下架":
                params.put("state", "3");
                return mModel.requestProjectMineList(params);
            case "收藏":
                params.put("state", "0");
                return mModel.requestProjectMineList(params);
            case "首页全部项目":
                return mModel.requestProjectList(params);
            default:
                return mModel.requestProjectList(params);
        }
    }

    @Override
    protected void onRefreshSuccess(String response) {
        switch (from) {
            case "全部":
            case "待审核":
            case "已发布":
            case "已拒绝":
            case "已下架":
            case "收藏":
                JSONObject json = JSON.parseObject(response);
                dataList.addAll(JSON.parseArray(json.getString("data"), ProjectBean.class));
                break;
            case "首页全部项目":
                dataList.addAll(JSON.parseArray(responseJson.getString("projectData"), ProjectBean.class));
                break;
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
