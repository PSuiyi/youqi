package com.mzk.compass.youqi.ui.home.people;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIActionSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class RecommendSelfAct extends BaseAppActivity {
    @Bind(R.id.tvProject)
    TextView tvProject;
    @Bind(R.id.llProject)
    LinearLayout llProject;
    @Bind(R.id.etRemark)
    EditText etRemark;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;

    private List<ProjectBean> projectBeanList = new ArrayList<>();
    private String currentId;
    private String id;
    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_recommend_self, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("自荐");
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestRecomSelfProject(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                projectBeanList.clear();
                projectBeanList.addAll(JSONArray.parseArray(responseOriginal.getString("data"), ProjectBean.class));
            }

            @Override
            public void onFail(String error) {
                super.onFail(error);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.llProject, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llProject:
                if (projectBeanList.isEmpty()) {
                    mDataManager.showToast("暂时没有项目，请先创建项目");
                    return;
                }
                List<String> items = new ArrayList<>();
                for (ProjectBean projectBean : projectBeanList) {
                    items.add(projectBean.getName());
                }
                new UIActionSheetDialog(activity)
                        .builder()
                        .addSheetItemList(items, null, which -> {
                            tvProject.setText(items.get(which));
                            currentId = projectBeanList.get(which).getId();
                        })
                        .show();
                break;
            case R.id.tvSubmit:
                if (StringUtil.isBlank(currentId)) {
                    mDataManager.showToast("请选择自荐项目");
                    return;
                }

                if (StringUtil.isBlank(mDataManager.getValueFromView(etRemark))) {
                    mDataManager.showToast("请输入备注");
                    return;
                }

                Map<String, String> params = new HashMap<>();
                if (from.equals("投资人")) {
                    params.put("type", "2");
                } else {
                    params.put("type", "1");
                }
                params.put("id", id);
                params.put("content", mDataManager.getValueFromView(etRemark));
                mModel.requestRecomSelf(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
                break;
        }
    }
}
