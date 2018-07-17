package com.mzk.compass.youqi.ui.home.project;

import android.os.Bundle;

import com.baidu.bdocreader.BDocInfo;
import com.baidu.bdocreader.BDocView;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.DocBean;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date： 2018/7/17 2018
 * User： PSuiyi
 * Description：
 */
public class DocAct extends BaseAppActivity {
    @Bind(R.id.doc)
    BDocView mDocView;
    private DocBean bean;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_doc, 1};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("bean")) {
            bean = (DocBean) getIntent().getSerializableExtra("bean");
        }
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("查看文档");
    }

    @Override
    protected void initializeView() {
        String host = "BCEDOC"; // 开放云传回的host
        String docId = bean.getDocid(); // 开放云传回的docId
        String docType = "doc"; // 开放云传回的文档类型 doc/ppt/ppts等
        String token = "TOKEN"; // 开放云传回的token
        String thisDocDir = ""; // 指定为空串""表示在线浏览
        int totalPage = 100; // 总页数，必须准确填写 否则在离线浏览时会有问题
        String docTitle = "查看文档";
        int startPage = 1; // 起始浏览页，最小值为1，请不要填入小于1的值
        BDocInfo docInfo = new BDocInfo(host, docId, docType, token)
                .setLocalFileDir(thisDocDir)
                .setTotalPage(totalPage)
                .setDocTitle(docTitle)
                .setStartPage(startPage);

        mDocView.setOnDocLoadStateListener(new BDocView.OnDocLoadStateListener() {
            @Override
            public void onDocLoadComplete() {
                KLog.d("test", "onDocLoadComplete");
            }

            @Override
            public void onDocLoadFailed(String errorDesc) {
                // errorDesc format: ERROR_XXXX_DESC(code=xxxxx)
                KLog.d("test", "onDocLoadFailed errorDesc=" + errorDesc);
            }

            @Override
            public void onCurrentPageChanged(int currentPage) {
                // 记录当前页面
                KLog.i("test", "currentPage = " + currentPage);
            }
        });

        // 加载文档
        mDocView.loadDoc(docInfo);
    }

    @Override
    protected void loadDataFromServer() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
