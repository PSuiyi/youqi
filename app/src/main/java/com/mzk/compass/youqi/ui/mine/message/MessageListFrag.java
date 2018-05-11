package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MessageAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.MessageBean;
import com.znz.compass.znzlibray.views.recyclerview.BaseQuickAdapter;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class MessageListFrag extends BaseAppListFragment<MessageBean> {

    private String from;

    public static MessageListFrag newInstance(String from) {
        Bundle args = new Bundle();
        args.putString("from", from);
        MessageListFrag fragment = new MessageListFrag();
        fragment.setArguments(args);
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
        adapter = new MessageAdapter(dataList);
        rvRefresh.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            switch (from) {
                case "互动消息":
                    gotoActivity(MessageInteractAct.class);
                    break;
                case "交易信息":
                    gotoActivity(MessageTradeAct.class);
                    break;
                case "系统信息":
                    gotoActivity(MessageSystemAct.class);
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
            case "互动消息":
                params.put("type", "0");
                break;
            case "交易信息":
                params.put("type", "1");
                break;
            case "系统信息":
                params.put("type", "2");
                break;
        }
        return mModel.requestMessageList(params);
    }

    @Override
    protected void onRefreshSuccess(String response) {
        JSONObject json = JSON.parseObject(response);
        dataList.addAll(JSON.parseArray(json.getString("data"), MessageBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

    }
}
