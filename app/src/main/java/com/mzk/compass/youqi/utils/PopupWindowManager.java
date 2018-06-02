package com.mzk.compass.youqi.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.adapter.FiltAdapter;
import com.mzk.compass.youqi.bean.FiltBean;
import com.mzk.compass.youqi.ui.publish.PublishAct;
import com.mzk.compass.youqi.ui.publish.PublishStateAct;
import com.znz.compass.znzlibray.common.DataManager;

import java.util.List;

import static com.znz.compass.znzlibray.utils.ViewHolder.init;


/**
 * Created by wcm on 2017/9/7.
 */

public class PopupWindowManager {
    private static PopupWindowManager instance;
    private Context mContext;
    private DataManager mDataManager;
    private PopupWindow popupWindow;
    private OnPopupWindowClickListener onPopupWindowClickListener;

    public PopupWindowManager(Context context) {
        mContext = context.getApplicationContext();
        mDataManager = DataManager.getInstance(context);
    }

    public static synchronized PopupWindowManager getInstance(Context mContext) {
        if (instance == null) {
            instance = new PopupWindowManager(mContext);
        }
        return instance;
    }

    public boolean isPopupExist() {
        if (popupWindow != null) {
            return popupWindow.isShowing();
        }
        return false;
    }

    public void hidePopupWindow() {
        try {
            if (null != popupWindow && popupWindow.isShowing()) {
                popupWindow.dismiss();
                popupWindow = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View initPopupWindow(int resId) {
        popupWindow = new PopupWindow(mContext);
        View view = LayoutInflater.from(mContext).inflate(resId, null);
        popupWindow.setContentView(view);
        ColorDrawable dw = new ColorDrawable(mDataManager.getColor(R.color.trans));
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setFocusable(true);
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        // 重写onKeyListener,返回只关闭pop
        view.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                hidePopupWindow();
                return true;
            }
            return false;
        });
        return view;
    }

    public View initPopupWindowNoBack(Activity mActivity, int resId) {
        popupWindow = new PopupWindow(mActivity);
        View view = LayoutInflater.from(mActivity).inflate(resId, null);
        popupWindow.setContentView(view);
        ColorDrawable dw = new ColorDrawable(mDataManager.getColor(R.color.trans));
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        return view;
    }

    public void showPayWays(View parent, OnPopupWindowClickListener listener) {
        hidePopupWindow();
        View view = initPopupWindow(R.layout.pop_pay_vip);
        LinearLayout llParent = init(view, R.id.llParent);
        llParent.setOnClickListener(view1 -> hidePopupWindow());
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }


    public void showPublish(View parent, OnPopupWindowClickListener listener) {
        hidePopupWindow();
        View view = initPopupWindow(R.layout.pop_publish);
        LinearLayout llParent = init(view, R.id.llParent);
        llParent.setOnClickListener(view1 -> hidePopupWindow());

        init(view, R.id.tvPublish1).setOnClickListener(v -> {
            mDataManager.gotoActivity(PublishAct.class);
            hidePopupWindow();
        });

        init(view, R.id.tvPublish2).setOnClickListener(v -> {
            mDataManager.gotoActivity(PublishStateAct.class);
            hidePopupWindow();
        });

        init(view, R.id.tvCancel).setOnClickListener(v -> {
            hidePopupWindow();
        });

        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    public void showChatProject(View parent, OnPopupWindowClickListener listener) {
        hidePopupWindow();
        View view = initPopupWindow(R.layout.pop_chat_project);
        LinearLayout llParent = init(view, R.id.llParent);
        llParent.setOnClickListener(view1 -> hidePopupWindow());

        init(view, R.id.tvPublish1).setOnClickListener(v -> {
            if (listener != null) {
                listener.onPopupWindowClick("站内信", null);
            }
            hidePopupWindow();
        });

        init(view, R.id.tvPublish2).setOnClickListener(v -> {
            if (listener != null) {
                listener.onPopupWindowClick("电话", null);
            }
            hidePopupWindow();
        });

        init(view, R.id.tvCancel).setOnClickListener(v -> {
            hidePopupWindow();
        });

        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    public void showFilt(View parent, List<FiltBean> dataList, OnPopupWindowClickListener onPopupWindowClickListener) {
        hidePopupWindow();
        View view = initPopupWindow(R.layout.pop_filt);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        RecyclerView rvRecycler = init(view, R.id.rvRecycler);
        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        FiltAdapter adapter = new FiltAdapter(dataList);
        rvRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view1, position) -> {
            if (onPopupWindowClickListener != null) {
                for (FiltBean filtBean : dataList) {
                    filtBean.setChecked(false);
                }
                dataList.get(position).setChecked(true);
                onPopupWindowClickListener.onPopupWindowClick(null, new String[]{dataList.get(position).getId()});
                hidePopupWindow();
            }
        });

        LinearLayout llParent = init(view, R.id.llParent);
        llParent.setOnClickListener(v -> {
            hidePopupWindow();
        });

        init(view, R.id.line).setOnClickListener(v -> {
            hidePopupWindow();
        });
        popupWindow.showAsDropDown(parent, 0, 0);
    }

    public interface OnPopupWindowClickListener {
        void onPopupWindowClick(String type, String[] values);
    }
}
