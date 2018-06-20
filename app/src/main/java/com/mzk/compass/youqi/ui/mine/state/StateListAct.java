package com.mzk.compass.youqi.ui.mine.state;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.StateAdapter;
import com.mzk.compass.youqi.base.BaseAppListActivity;
import com.mzk.compass.youqi.bean.MessageBean;
import com.mzk.compass.youqi.bean.StateBean;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class StateListAct extends BaseAppListActivity<StateBean> {
    @Bind(R.id.cbSelectAll)
    CheckBox cbSelectAll;
    @Bind(R.id.tvDelete)
    TextView tvDelete;
    @Bind(R.id.llSelectAll)
    LinearLayout llSelectAll;

    private boolean isEdit = false;
    private boolean isAll = false;
    private String stateId;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_state, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("我的动态");
        znzToolBar.setNavRightText("编辑", mDataManager.getColor(R.color.red));
        znzToolBar.setOnNavRightClickListener(view -> {
            if (isEdit) {
                isEdit = false;
                znzToolBar.setNavRightText("编辑", mDataManager.getColor(R.color.red));
                mDataManager.setViewVisibility(llSelectAll, false);
            } else {
                isEdit = true;
                znzToolBar.setNavRightText("完成", mDataManager.getColor(R.color.red));
                mDataManager.setViewVisibility(llSelectAll, true);
            }
            for (StateBean stateBean : dataList) {
                stateBean.setChecked(isEdit);
            }
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    protected void initializeView() {
        cbSelectAll.setClickable(false);
        cbSelectAll.setEnabled(false);
        adapter = new StateAdapter(dataList);
        ((StateAdapter) adapter).setCanSwipe(true);
        rvRefresh.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            StateBean bean = dataList.get(position);
            switch (view.getId()) {
                case R.id.llDelete:
                    stateId = bean.getId();
                    new UIAlertDialog(activity)
                            .builder()
                            .setMsg("确定删除")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", v2 -> {
                                requestDelete();
                            })
                            .show();
                    break;
                case R.id.llContainer:
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", bean);
                    gotoActivity(StateDetailAct.class, bundle);
                    break;
                case R.id.cbSelect:
                    bean.setSelect(!bean.isSelect());
                    adapter.notifyDataSetChanged();
                    for (StateBean stateBean : dataList) {
                        if (!stateBean.isSelect()) {
                            cbSelectAll.setChecked(false);
                            isAll = false;
                            return;
                        }
                        cbSelectAll.setChecked(true);
                        isAll = true;
                    }
                    break;
            }
        });
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        return mModel.requestStateList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        JSONObject json = JSON.parseObject(response);
        dataList.addAll(JSON.parseArray(json.getString("data"), StateBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvDelete, R.id.llSelectAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDelete:
                String str = "";
                for (StateBean stateBean : dataList) {
                    if (stateBean.isSelect()) {
                        str = stateBean.getId() + "," + str;
                    }
                }
                if (StringUtil.isBlank(str)) {
                    mDataManager.showToast("请选择要删除的动态");
                    return;
                }
                stateId = str.substring(0, str.length() - 1);
                new UIAlertDialog(activity)
                        .builder()
                        .setMsg("确定删除")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", v2 -> {
                            requestDelete();
                        })
                        .show();
                break;
            case R.id.llSelectAll:
                if (isAll) {
                    isAll = false;
                } else {
                    isAll = true;
                }
                for (StateBean stateBean : dataList) {
                    stateBean.setSelect(isAll);
                }
                adapter.notifyDataSetChanged();
                cbSelectAll.setChecked(isAll);
                break;
        }
    }

    private void requestDelete() {
        Map<String, String> params = new HashMap<>();
        params.put("stateId", stateId);
        mModel.requestDeleteState(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("删除成功");
                resetRefresh();
                isEdit = false;
                znzToolBar.setNavRightText("编辑", mDataManager.getColor(R.color.red));
                mDataManager.setViewVisibility(llSelectAll, false);
            }
        });
    }
}
