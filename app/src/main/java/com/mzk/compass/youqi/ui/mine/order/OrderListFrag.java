package com.mzk.compass.youqi.ui.mine.order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.OrderAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.OrderBean;
import com.mzk.compass.youqi.bean.ProjectBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.home.project.ProjectListFrag;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class OrderListFrag extends BaseAppListFragment<OrderBean> {

    private String from;

    public static OrderListFrag newInstance(String from) {
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        OrderListFrag fragment = new OrderListFrag();
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
        adapter = new OrderAdapter(dataList);
        rvRefresh.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            OrderBean bean = dataList.get(position);
            switch (view.getId()) {
                case R.id.tvCancal:
                    new UIAlertDialog(activity)
                            .builder()
                            .setMsg("确定取消订单")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", v2 -> {
                                updateOrder(bean.getOrderSerial());
                            })
                            .show();

                    break;
                case R.id.tvPay:
                    break;
            }
        });
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
        switch (from) {
            case "全部":
                params.put("state", "0");
                break;
            case "待付款":
                params.put("state", "1");
                break;
            case "待服务":
                params.put("state", "2");
                break;
            case "待确认服务结果":
                params.put("state", "3");
                break;
            case "已取消":
                params.put("state", "4");
                break;
        }
        return mModel.requestOrderList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        JSONObject json = JSON.parseObject(response);
        dataList.addAll(JSON.parseArray(json.getString("data"), OrderBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }

    private void updateOrder(String orderSerial) {
        Map<String, String> params = new HashMap<>();
        params.put("optype", "cancel");
        params.put("orderSerial", orderSerial);
        mModel.requestUpdateOrder(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                mDataManager.showToast("订单取消成功");
                resetRefresh();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_ORDER:
                resetRefresh();
                break;
        }
    }
}
