package com.znz.compass.umeng.share;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

/**
 * Date： 2017/4/7 2017
 * User： PSuiyi
 * Description：
 */

public class ShareManager {

    private static ShareManager instance;
    private Context mContext;

    public ShareManager(Context context) {
        this.mContext = context;
    }

    public static ShareManager getInstance(Context context) {
        if (instance == null) {
            instance = new ShareManager(context.getApplicationContext());
        }
        return instance;
    }


    public void shareWeb(Activity activity, ShareBean bean, SHARE_MEDIA share_media, UMShareListener shareListener) {
        UMWeb web = new UMWeb(bean.getUrl());
        web.setTitle(bean.getTitle());//标题
        if (!TextUtils.isEmpty(bean.getImageUrl())) {
            web.setThumb(new UMImage(activity, bean.getImageUrl()));
        } else {
            web.setThumb(new UMImage(activity, bean.getIconRes()));
        }

        web.setDescription(bean.getDescription());//描述

        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(shareListener)
                .share();
    }

    public void shareWeb(Activity activity, ShareBean bean, SHARE_MEDIA share_media) {
        UMWeb web = new UMWeb(bean.getUrl());
        web.setTitle(bean.getTitle());//标题
//        if (!TextUtils.isEmpty(bean.getImageUrl())) {
//            web.setThumb(new UMImage(activity, bean.getImageUrl()));
//        } else {
//            web.setThumb(new UMImage(activity, bean.getIconRes()));
//        }
        web.setThumb(new UMImage(activity, bean.getIconRes()));
        web.setDescription(bean.getDescription());//描述

        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(shareListener)
                .share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.e(t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    public void shareWeb(Activity activity, ShareBean bean, SHARE_MEDIA share_media, final OnZnzShareListener onShareClickListener, UMShareListener shareListener) {
        UMWeb web = new UMWeb(bean.getUrl());
        web.setTitle(bean.getTitle());//标题
        web.setThumb(new UMImage(activity, bean.getIconRes()));
        web.setDescription(bean.getDescription());//描述

        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        //分享开始的回调
                        if (onShareClickListener != null) {
                            onShareClickListener.onZnzShareStart();
                        }
                    }

                    @Override
                    public void onResult(SHARE_MEDIA platform) {
//                        Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                        if (onShareClickListener != null) {
                            onShareClickListener.onShareSuccess();
                        }
                    }

                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
//                        Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                        if (t != null) {
                            Log.e(t.getMessage());
                        }
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
//                        Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
                        if (onShareClickListener != null) {
                            onShareClickListener.onZnzShareCancel();
                        }
                    }
                })
                .share();
    }


    public interface OnZnzShareListener {
        void onShareSuccess();

        void onZnzShareStart();

        void onZnzShareCancel();
    }

}
