package com.mzk.compass.youqi.ui.common;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.socks.library.KLog;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.utils.ZnzLog;
import com.znz.compass.znzlibray.views.WebViewWithProgress;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import butterknife.Bind;


/**
 * Created by panqiming on 2016/10/29.
 */

public class WebViewAct extends BaseAppActivity {

    @Bind(R.id.wvHtml)
    WebViewWithProgress wvHtml;
    private String url;
    private String title;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_common_webview, 1};
    }


    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("url")) {
            url = getIntent().getStringExtra("url");
        }
        if (getIntent().hasExtra("title")) {
            title = getIntent().getStringExtra("title");
        }

        ZnzLog.e("receive url:" + url);

        if (!StringUtil.isBlank(url)) {
            if (!url.startsWith("http") && !url.startsWith("HTTP")) {
                url = "http://" + url;
            }
        }
    }

    @Override
    protected void initializeNavigation() {
        if (!StringUtil.isBlank(title)) {
            setTitleName(title);
        } else {
            setTitleName(getResources().getString(R.string.app_name));
        }
    }

    @Override
    protected void initializeView() {
        WebSettings settings = wvHtml.getSettings();
        settings.setSupportZoom(true);//是否支持缩放,默认为true
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //版本号控制，使图片能够适配
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion <= 19) {
        } else {
            settings.setUseWideViewPort(true);
        }

        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        wvHtml.loadUrl(url);
        wvHtml.setWebViewClient(new WebViewClient() {
            // 点击网页里面的链接还是在当前的webView内部跳转，不跳转外部浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                KLog.e("用户单击超连接", url);
                try {
                    String tag = "tel";
                    String mobile = url.split(":")[1];
                    if (StringUtil.isBlank(mobile)) {
                        return false;
                    }
                    if (url.contains(tag)) {
                        new UIAlertDialog(activity)
                                .builder()
                                .setMsg("是否拨打" + mobile)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", v2 -> {
                                    Uri uri = Uri.parse("tel:" + mobile);
                                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                                    startActivity(intent);
                                })
                                .show();
                        //这个超连接,java已经处理了，webview不要处理了
                        return true;
                    }


                    if (url.startsWith("weixin://wap/pay?")) {
                        try {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            mDataManager.showToast("请安装微信最新版！");
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            // 可以让webView处理https请求
            @Override
            public void onReceivedSslError(WebView view, android.webkit.SslErrorHandler handler, android.net.http.SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

        });

        wvHtml.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvHtml.canGoBack()) {  //表示按返回键
                        wvHtml.goBack();   //后退
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void loadDataFromServer() {

    }
}
