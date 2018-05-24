package com.mzk.compass.youqi.ui.publish;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.ZnzRemind;
import com.znz.compass.znzlibray.views.ZnzToolBar;
import com.znz.compass.znzlibray.views.gallery.inter.IPhotoSelectCallback;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date： 2018/4/9 2018
 * User： PSuiyi
 * Description：
 */
public class PublishAct extends BaseAppActivity {
    @Bind(R.id.znzToolBar)
    ZnzToolBar znzToolBar;
    @Bind(R.id.znzRemind)
    ZnzRemind znzRemind;
    @Bind(R.id.llNetworkStatus)
    LinearLayout llNetworkStatus;
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.llAddress)
    LinearLayout llAddress;
    @Bind(R.id.etAddress)
    EditText etAddress;
    @Bind(R.id.ivLogo)
    HttpImageView ivLogo;
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCompany)
    EditText etCompany;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.etCompanySimple)
    EditText etCompanySimple;
    private String logoUrl;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_publish_project, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("项目发布");
    }

    @Override
    protected void initializeView() {

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

    @OnClick({R.id.llAddress, R.id.ivLogo, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llAddress:
                break;
            case R.id.ivLogo:
                mDataManager.openPhotoSelectSingle(activity, new IPhotoSelectCallback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(List<String> photoList) {
                        if (!photoList.isEmpty()) {
                            Map<String, String> params = new HashMap<>();
                            params.put("dirname", "project");
                            mModel.uploadImageSingle(params, photoList.get(0), new ZnzHttpListener() {
                                @Override
                                public void onSuccess(JSONObject responseOriginal) {
                                    super.onSuccess(responseOriginal);
                                    logoUrl = responseOriginal.getString("data");
                                    ivLogo.loadHeaderImage(logoUrl);
                                }

                                @Override
                                public void onFail(String error) {
                                    super.onFail(error);
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError() {

                    }
                }, true);
                break;
            case R.id.tvSubmit:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etName))) {
                    mDataManager.showToast("请输入项目名称");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etAddress))) {
                    mDataManager.showToast("请输入详细地址");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etUserName))) {
                    mDataManager.showToast("请输入姓名");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etPhone))) {
                    mDataManager.showToast("请输入电话");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etCompany))) {
                    mDataManager.showToast("请输入项目名称");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etCompanySimple))) {
                    mDataManager.showToast("请输入项目名称");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("name", mDataManager.getValueFromView(etName));
                params.put("address", mDataManager.getValueFromView(etAddress));
                params.put("userName", mDataManager.getValueFromView(etUserName));
                params.put("etPhone", mDataManager.getValueFromView(etPhone));
                mModel.requestPublishProject(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                    }

                    @Override
                    public void onFail(String error) {
                        super.onFail(error);
                    }
                });
                break;
        }
    }
}
