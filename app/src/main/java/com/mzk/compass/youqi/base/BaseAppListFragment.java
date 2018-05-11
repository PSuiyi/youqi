package com.mzk.compass.youqi.base;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.api.ApiModel;
import com.socks.library.KLog;
import com.znz.compass.znzlibray.base.BaseListFragment;
import com.znz.compass.znzlibray.bean.BaseZnzBean;
import com.znz.compass.znzlibray.common.ZnzConstants;
import com.znz.compass.znzlibray.utils.StringUtil;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Date： 2017/9/4 2017
 * User： PSuiyi
 * Description：
 */

public abstract class BaseAppListFragment<T extends BaseZnzBean> extends BaseListFragment<T> {
    protected ApiModel mModel;
    protected JSONObject jsonObject;
    private View headerNoDataView;

    @Override
    protected void initializeAppBusiness() {
        mModel = new ApiModel(activity, this);
    }

    /**
     * 列表访问网络请求
     *
     * @param action
     */
    protected void customeRefreshRequest(final int action) {
        if (mModel == null) {
            KLog.e("请实例化model");
            setTempDataList();
            return;
        }

        currentAction = action;

        //是用clear还是重新new都有道理，暂时无法取舍
        params = new HashMap<>();

        if (requestCustomeRefreshObservable() == null) {
            setTempDataList();
        } else {
            if (action == ACTION_PULL_TO_REFRESH) {
                currentPageIndex = 1;
            }

            if (isNormalList) {
                params.put("pageSize", "100");
            } else {
                params.put("pageSize", "10");
            }
            params.put("page", currentPageIndex + "");

            subscriberList = new Subscriber<ResponseBody>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    onRefreshFail(e.toString());
                    e.printStackTrace();
                    mSwipeRefreshLayout.setRefreshing(false);
                    handleNoData();
                    adapter.loadMoreFail();
                }

                @Override
                public void onNext(ResponseBody responseBody) {
                    try {
                        //写在这数据是加载完才删除
                        if (action == ACTION_PULL_TO_REFRESH) {
                            dataList.clear();
                        }

                        String responseStr = responseBody.string();
                        if (responseStr.contains("status_code=90000")) {
                            mDataManager.tokenTimeOut(context);
                            return;
                        }

                        jsonObject = JSON.parseObject(responseStr);
                        int totalCount = 0;
                        if (jsonObject.getString("status_code").equals("0")) {
                            try {
                                if (!isNormalList) {
                                    totalCount = StringUtil.stringToInt(JSON.parseObject(jsonObject.getString("data")).getString("totalCount"));
                                }
                                responseJson = JSON.parseObject(jsonObject.getString("data"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (!StringUtil.isBlank(jsonObject.getString("data")) && !jsonObject.getString("data").equals("[]")) {
                                    onRefreshSuccess(jsonObject.getString("data"));
                                } else {
                                    onRefreshSuccess("[]");
                                }

                                if (dataList.isEmpty()) {
                                    handleNoData();
                                } else {
                                    if (isAddHeaderNoData) {
                                        adapter.getHeaderLayout().removeView(headerNoDataView);
                                        isAddHeaderNoData = false;
                                    }
                                    if (totalCount > currentPageIndex * 10) {
                                        adapter.setEnableLoadMore(true);
                                    } else {
                                        adapter.setEnableLoadMore(false);
                                        adapter.loadMoreEnd(true);
                                    }
                                }

                                if (action == ACTION_PULL_TO_REFRESH) {
                                    Observable.timer(ZnzConstants.LODING_TIME, TimeUnit.MILLISECONDS)
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .doOnCompleted(() -> mSwipeRefreshLayout.setRefreshing(false))
                                            .subscribe();
                                }
                                adapter.loadMoreComplete();

                                //页码自增
                                currentPageIndex++;
                            }
                        } else if (jsonObject.getString("status_code").equals("90000")) {
                            mDataManager.tokenTimeOut(context);
                        } else {
                            mDataManager.showToast(jsonObject.getString("msg"));
                            Observable.timer(ZnzConstants.LODING_TIME, TimeUnit.MILLISECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .doOnCompleted(() -> mSwipeRefreshLayout.setRefreshing(false))
                                    .subscribe();
                            handleNoData();
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            requestCustomeRefreshObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriberList);
        }
    }

    /**
     * 判断是否有头部
     */
    private void handleNoData() {
        if (adapter.getHeaderLayout() != null) {
            if (!isAddHeaderNoData) {
                if (!StringUtil.isBlank(noDataDes)) {
                    headerNoDataView = View.inflate(activity, R.layout.widget_pull_to_refresh_no_data, null);
                    TextView textView = headerNoDataView.findViewById(R.id.tvNoData);
                    textView.setText(noDataDes);
                } else {
                    headerNoDataView = View.inflate(activity, R.layout.widget_pull_to_refresh_no_data, null);
                }
                adapter.getHeaderLayout().addView(headerNoDataView);
                isAddHeaderNoData = true;
            }
        } else {
            if (!StringUtil.isBlank(noDataDes)) {
                adapter.setEmptyView(R.layout.widget_pull_to_refresh_no_data, noDataDes);
            } else {
                adapter.setEmptyView(R.layout.widget_pull_to_refresh_no_data);
            }
        }
        adapter.setEnableLoadMore(false);
    }
}
