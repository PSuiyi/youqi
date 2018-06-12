package com.mzk.compass.youqi.ui.mine.account;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.gallery.inter.IPhotoSelectCallback;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/26.
 */

public class IdentifyAct extends BaseAppActivity {
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etCard)
    EditText etCard;
    @Bind(R.id.ivFrontCard)
    HttpImageView ivFrontCard;
    @Bind(R.id.ivBackCard)
    HttpImageView ivBackCard;

    private String frontPhoto;
    private String backPhoto;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_identify, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("身份认证");
        znzToolBar.setNavRightText("提交",mDataManager.getColor(R.color.red));
        znzToolBar.setOnNavRightClickListener(view -> {
            if (StringUtil.isBlank(mDataManager.getValueFromView(etName))) {
                mDataManager.showToast("请输入姓名");
                return;
            }
            if (StringUtil.isBlank(mDataManager.getValueFromView(etCard))) {
                mDataManager.showToast("请输入证件号");
                return;
            }
            if (StringUtil.isBlank(frontPhoto)) {
                mDataManager.showToast("请上传证件正面照");
                return;
            }
            if (StringUtil.isBlank(backPhoto)) {
                mDataManager.showToast("请上传证件反面照");
                return;
            }
            Map<String, String> params = new HashMap<>();
            params.put("cardNum", mDataManager.getValueFromView(etCard));
            params.put("name", mDataManager.getValueFromView(etName));
            params.put("frontPhoto", frontPhoto);
            params.put("backPhoto", backPhoto);
            mModel.requestIdentifyPersonal(params, new ZnzHttpListener() {
                @Override
                public void onSuccess(JSONObject responseOriginal) {
                    super.onSuccess(responseOriginal);
                    mDataManager.showToast("提交成功");
                    finish();
                }
            });
        });
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestIdentifyPersonal(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                    etName.setText(json.getString("name"));
                    etCard.setText(json.getString("cardNum"));
                    frontPhoto = json.getString("frontPhoto");
                    backPhoto = json.getString("backPhoto");
                    ivFrontCard.loadHttpImage(frontPhoto);
                    ivBackCard.loadHttpImage(backPhoto);
                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivFrontCard, R.id.ivBackCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivFrontCard:
                mDataManager.openPhotoSelectSingle(activity, new IPhotoSelectCallback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(List<String> photoList) {
                        if (!photoList.isEmpty()) {
                            Map<String, String> params = new HashMap<>();
                            params.put("dirname", "approvepersonal");
                            mModel.uploadImageSingle(params, photoList.get(0), new ZnzHttpListener() {
                                @Override
                                public void onSuccess(JSONObject responseOriginal) {
                                    super.onSuccess(responseOriginal);
                                    if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                                        frontPhoto = responseOriginal.getString("data");
                                        ivFrontCard.loadHttpImage(frontPhoto);
                                    }
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
                }, false);
                break;
            case R.id.ivBackCard:
                mDataManager.openPhotoSelectSingle(activity, new IPhotoSelectCallback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(List<String> photoList) {
                        if (!photoList.isEmpty()) {
                            Map<String, String> params = new HashMap<>();
                            params.put("dirname", "approvepersonal");
                            mModel.uploadImageSingle(params, photoList.get(0), new ZnzHttpListener() {
                                @Override
                                public void onSuccess(JSONObject responseOriginal) {
                                    super.onSuccess(responseOriginal);
                                    if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                                        backPhoto = responseOriginal.getString("data");
                                        ivBackCard.loadHttpImage(backPhoto);
                                    }
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
                }, false);
                break;
        }
    }
}
