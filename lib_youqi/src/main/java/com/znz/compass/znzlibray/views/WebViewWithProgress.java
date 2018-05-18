package com.znz.compass.znzlibray.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.znz.compass.znzlibray.R;

/**
 * @see com.znz.compass.znzlibray.views
 * Project_Name： builder_master
 * Date： 2017/2/7 2017
 * User： PSuiyi
 * Description：
 */

public class WebViewWithProgress extends WebView {
    private ProgressBar progressbar;
    private WebSettings settings;

    public WebViewWithProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 8, 0, 0));

        Drawable drawable = context.getResources().getDrawable(R.drawable.webview_progeress);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        setWebChromeClient(new WebChromeClient());
        initSetting();

    }

    private void initSetting() {
        settings = this.getSettings();
        settings.setSupportZoom(true);//是否支持缩放,默认为true
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        this.setWebViewClient(new WebViewClient() {
            // 点击网页里面的链接还是在当前的webView内部跳转，不跳转外部浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
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

    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void loadContent(String content) {
        progressbar.setVisibility(GONE);
        this.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        //版本号控制，使图片能够适配
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion <= 19) {
        } else {
            settings.setUseWideViewPort(true);
            settings.setTextZoom(250);
        }
    }


//    /**
//     * 处理html文本
//     *
//     * @param htmltext
//     * @return
//     */
//    private String getNewContent(String htmltext) {
//        Document doc = Jsoup.parse(htmltext);
//        Elements elements = doc.getElementsByTag("img");
//        for (Element element : elements) {
//            element.attr("width", "100%").attr("height", "auto");
//        }
//
//        KLog.e("doc.toString()---->" + doc.toString());
//
//        return doc.toString();
//    }
}
