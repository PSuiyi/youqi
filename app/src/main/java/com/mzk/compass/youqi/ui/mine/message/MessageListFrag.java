package com.mzk.compass.youqi.ui.mine.message;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.MessageAdapter;
import com.mzk.compass.youqi.base.BaseAppListFragment;
import com.mzk.compass.youqi.bean.MessageBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.znz.compass.znzlibray.eventbus.EventManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class MessageListFrag extends BaseAppListFragment<MessageBean> {

    @Bind(R.id.llSelectAll)
    LinearLayout llSelectAll;
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
        return new int[]{R.layout.frag_message};
    }

    @Override
    protected void initializeVariate() {
        if (getArguments() != null) {
            from = getArguments().getString("from");
        }
        dataList.add(new MessageBean());
        dataList.add(new MessageBean());
        dataList.add(new MessageBean());
        dataList.add(new MessageBean());
        dataList.add(new MessageBean());
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
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            MessageBean bean = dataList.get(position);
            switch (view.getId()) {
                case R.id.llDelete:
                    break;
                case R.id.llContainer:
                    Bundle bundle = new Bundle();
                    bundle.putString("id", dataList.get(position).getId());
                    switch (from) {
                        case "互动消息":
                            gotoActivity(MessageInteractAct.class, bundle);
                            break;
                        case "交易信息":
                            gotoActivity(MessageTradeAct.class, bundle);
                            break;
                        case "系统信息":
                            gotoActivity(MessageSystemAct.class, bundle);
                            break;
                    }
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

//    @Override
//    protected Observable<ResponseBody> requestCustomeRefreshObservable() {
//        switch (from) {
//            case "互动消息":
//                params.put("type", "0");
//                break;
//            case "交易信息":
//                params.put("type", "1");
//                break;
//            case "系统信息":
//                params.put("type", "2");
//                break;
//        }
//        return mModel.requestMessageList(params);
//    }

    @Override
    protected void onRefreshSuccess(String response) {
        JSONObject json = JSON.parseObject(response);
        dataList.addAll(JSON.parseArray(json.getString("data"), MessageBean.class));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRefreshFail(String error) {

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
            case EventTags.REFRESH_MESSAGE_EDIT:
                switch (event.getValue()) {
                    case "完成":
                        for (MessageBean messageBean : dataList) {
                            messageBean.setEdit(false);
                        }
                        adapter.notifyDataSetChanged();
                        mDataManager.setViewVisibility(llSelectAll, false);
                        break;
                    case "编辑":
                        for (MessageBean messageBean : dataList) {
                            messageBean.setEdit(true);
                        }
                        adapter.notifyDataSetChanged();
                        mDataManager.setViewVisibility(llSelectAll, true);
                        break;
                }

                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.cbSelectAll, R.id.tvDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cbSelectAll:
                for (MessageBean messageBean : dataList) {
                    messageBean.setSelect(true);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.tvDelete:
                break;
        }
    }
}
