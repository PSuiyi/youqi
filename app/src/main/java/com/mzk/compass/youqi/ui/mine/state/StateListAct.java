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
import com.mzk.compass.youqi.bean.StateBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;

import org.greenrobot.eventbus.EventBus;

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
        znzToolBar.setNavRightText("编辑");
        znzToolBar.setOnNavRightClickListener(view -> {
            if (isEdit) {
                isEdit = false;
                znzToolBar.setNavRightText("编辑");
                mDataManager.setViewVisibility(llSelectAll, true);
            } else {
                isEdit = true;
                znzToolBar.setNavRightText("完成");
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
        rvRefresh.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            StateBean bean = dataList.get(position);
            switch (view.getId()) {
                case R.id.llDelete:
                    break;
                case R.id.llContainer:
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", bean);
                    gotoActivity(StateDetailAct.class, bundle);
                    break;
                case R.id.cbSelect:
                    bean.setSelect(true);
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
                break;
            case R.id.llSelectAll:
                break;
        }
    }
}
