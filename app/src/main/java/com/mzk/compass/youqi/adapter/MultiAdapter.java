package com.mzk.compass.youqi.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.api.ApiModel;
import com.mzk.compass.youqi.bean.MultiBean;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.home.organ.OrganListAct;
import com.mzk.compass.youqi.ui.home.people.PeopleListAct;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
import com.mzk.compass.youqi.ui.home.project.ProjectListAct;
import com.mzk.compass.youqi.utils.PopupWindowManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.recyclerview.BaseMultiItemQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;
import com.znz.compass.znzlibray.views.recyclerview.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiAdapter extends BaseMultiItemQuickAdapter<MultiBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    private final ApiModel mModel;

    public MultiAdapter(List dataList, ApiModel mModel) {
        super(dataList);
        this.mModel = mModel;
        addItemType(Constants.MultiType.Section, R.layout.item_lv_section);
        addItemType(Constants.MultiType.Project, R.layout.item_lv_home_project);
        addItemType(Constants.MultiType.People, R.layout.item_lv_recycle);
        addItemType(Constants.MultiType.Organ, R.layout.item_lv_home_organ);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiBean bean) {
        setOnItemClickListener(this);
        switch (bean.getItemType()) {
            case Constants.MultiType.Section:
                helper.setText(R.id.tvTitle, bean.getValue());
                break;
            case Constants.MultiType.Project:
                ProjectAdapter projectAdapter = new ProjectAdapter(bean.getProjectBeanList());
                RecyclerView rvProject = helper.getView(R.id.rvRecycler);
                LinearLayoutManager proLayoutManager = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rvProject.setLayoutManager(proLayoutManager);
                rvProject.setAdapter(projectAdapter);
                helper.addOnClickListener(R.id.llChange);

                projectAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ProjectBean projectBean = bean.getProjectBeanList().get(position);
                        switch (view.getId()) {
                            case R.id.ivShare:
                                PopupWindowManager.getInstance(mContext).showShare(view, (type, values) -> {

                                });
                                break;
                            case R.id.ivFav:
                                if (projectBean.getIsCollected().equals("true")) {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("type", "1");
                                    params.put("id", projectBean.getId());
                                    mModel.requestCancalCollect(params, new ZnzHttpListener() {
                                        @Override
                                        public void onSuccess(JSONObject responseOriginal) {
                                            super.onSuccess(responseOriginal);
                                            projectBean.setIsCollected("false");
                                            projectAdapter.notifyDataSetChanged();
                                        }
                                    });
                                } else {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("type", "1");
                                    params.put("id", projectBean.getId());
                                    mModel.requestAddCollect(params, new ZnzHttpListener() {
                                        @Override
                                        public void onSuccess(JSONObject responseOriginal) {
                                            super.onSuccess(responseOriginal);
                                            projectBean.setIsCollected("true");
                                            projectAdapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                                break;
                        }
                    }
                });
                break;
            case Constants.MultiType.People:
                PeopleGridHomeAdapter adapter = new PeopleGridHomeAdapter(bean.getPeopleList());
                RecyclerView rvPeople = helper.getView(R.id.rvRecycler);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rvPeople.setLayoutManager(layoutManager);
                rvPeople.setAdapter(adapter);
                break;
            case Constants.MultiType.Organ:
                OrganGridAdapter adapter2 = new OrganGridAdapter(bean.getOrganList());
                RecyclerView rvOrgan = helper.getView(R.id.rvRecycler);
                rvOrgan.setLayoutManager(new GridLayoutManager(mContext, 4));
                rvOrgan.setAdapter(adapter2);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (bean.getItemType()) {
            case Constants.MultiType.Section:
                switch (bean.getValue()) {
                    case "精选创业项目":
                        gotoActivity(ProjectListAct.class);
                        break;
                    case "明星投资人":
                        gotoActivity(PeopleListAct.class);
                        break;
                    case "精选机构":
                        gotoActivity(OrganListAct.class);
                        break;
                }
                break;
            case Constants.MultiType.Project:
                gotoActivity(ProjectDetailAct.class);
                break;
            case Constants.MultiType.People:
                break;
            case Constants.MultiType.Organ:
                break;
        }
    }

}
