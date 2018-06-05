package com.znz.compass.umeng.login;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Date： 2017/3/31 2017
 * User： PSuiyi
 * Description：
 */

public class LoginAuthManager {
    private static LoginAuthManager instance;
    private Context mContext;

    public LoginAuthManager(Context context) {
        this.mContext = context;
    }

    public static LoginAuthManager getInstance(Context context) {
        if (instance == null) {
            instance = new LoginAuthManager(context.getApplicationContext());
        }
        return instance;
    }


    public void loginQQ(Activity mActivity, UMAuthListener authListener) {
        UMShareAPI.get(mContext).getPlatformInfo(mActivity, SHARE_MEDIA.QQ, authListener);
    }

    public void logoutQQ(Activity mActivity) {
        UMShareAPI.get(mContext).deleteOauth(mActivity, SHARE_MEDIA.QQ, authListener);
    }

    public void loginWeChat(Activity mActivity, UMAuthListener mListener) {
        UMShareAPI.get(mContext).getPlatformInfo(mActivity, SHARE_MEDIA.WEIXIN, mListener);
    }

    public void logoutWeChat(Activity mActivity) {
        UMShareAPI.get(mContext).deleteOauth(mActivity, SHARE_MEDIA.WEIXIN, authListener);
    }

    public void loginWeibo(Activity mActivity, UMAuthListener mListener) {
        UMShareAPI.get(mContext).getPlatformInfo(mActivity, SHARE_MEDIA.SINA, mListener);
    }

    public void logoutWeibo(Activity mActivity) {
        UMShareAPI.get(mContext).deleteOauth(mActivity, SHARE_MEDIA.SINA, authListener);
    }

    public void logoutAll(Activity mActivity) {
        try {
            logoutQQ(mActivity);
            logoutWeibo(mActivity);
            logoutWeChat(mActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
