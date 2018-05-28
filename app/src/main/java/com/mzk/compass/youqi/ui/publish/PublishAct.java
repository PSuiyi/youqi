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
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

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
    @Bind(R.id.ivCompanyLogo)
    HttpImageView ivCompanyLogo;
    private String logoUrl;
    private String companyLogoUrl;

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

    @OnClick({R.id.llAddress, R.id.ivLogo, R.id.tvSubmit, R.id.ivCompanyLogo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llAddress:
                break;
            case R.id.ivCompanyLogo:
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
                                    companyLogoUrl = responseOriginal.getString("data");
                                    ivCompanyLogo.loadSquareImage(companyLogoUrl);
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
                                    ivLogo.loadSquareImage(logoUrl);
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
                if (StringUtil.isBlank(logoUrl)) {
                    mDataManager.showToast("请上传Logo");
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
                    mDataManager.showToast("请输入公司简称不超过6个字");
                    return;
                }

                Map<String, String> params = new HashMap<>();
                params.put("name", mDataManager.getValueFromView(etName));
                params.put("address", mDataManager.getValueFromView(etAddress));
                params.put("userName", mDataManager.getValueFromView(etUserName));
                params.put("tel", mDataManager.getValueFromView(etPhone));
                params.put("provinceid", "11");
                params.put("cityid", "11");
                params.put("areaid", "11");
                params.put("companyName", mDataManager.getValueFromView(etCompany));
                params.put("shortName", mDataManager.getValueFromView(etCompanySimple));
                if (!StringUtil.isBlank(companyLogoUrl)) {
                    params.put("companyLogo", companyLogoUrl);
                }
                params.put("logo", logoUrl);
                mModel.requestPublishProject(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        new UIAlertDialog(activity)
                                .builder()
                                .setCancelable(false)
                                .setTitle("请去PC端完善资料后提交审核")
                                .setMsg("点击PC端的个人中心“发布项目”即可")
                                .setPositiveButton("确定", v2 -> {
                                    finish();
                                })
                                .show();
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
