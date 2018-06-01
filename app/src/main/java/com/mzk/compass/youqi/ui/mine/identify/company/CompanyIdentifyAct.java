package com.mzk.compass.youqi.ui.mine.identify.company;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.CityBean;
import com.mzk.compass.youqi.bean.CompanyBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.common.CityListAct;
import com.mzk.compass.youqi.view.city.bean.City;
import com.mzk.compass.youqi.view.city.bean.County;
import com.mzk.compass.youqi.view.city.bean.Province;
import com.mzk.compass.youqi.view.city.bean.Street;
import com.mzk.compass.youqi.view.city.utils.LogUtil;
import com.mzk.compass.youqi.view.city.widget.AddressSelector;
import com.mzk.compass.youqi.view.city.widget.BottomDialog;
import com.mzk.compass.youqi.view.city.widget.OnAddressSelectedListener;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.gallery.inter.IPhotoSelectCallback;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIAlertDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class CompanyIdentifyAct extends BaseAppActivity implements OnAddressSelectedListener, AddressSelector.OnDialogCloseListener, AddressSelector.onSelectorAreaPositionListener {
    @Bind(R.id.etCompany)
    EditText etCompany;
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.tvCity)
    TextView tvCity;
    @Bind(R.id.etAddress)
    EditText etAddress;
    @Bind(R.id.etCard)
    EditText etCard;
    @Bind(R.id.ivCard)
    HttpImageView ivCard;
    @Bind(R.id.cbSelect)
    CheckBox cbSelect;
    @Bind(R.id.llCity)
    LinearLayout llCity;
    @Bind(R.id.llSelect)
    LinearLayout llSelect;
    @Bind(R.id.tvDisagree)
    TextView tvDisagree;
    @Bind(R.id.tvAgree)
    TextView tvAgree;
    @Bind(R.id.llMenu)
    LinearLayout llMenu;

    List<CityBean> cityList = new ArrayList<>();
    private String licensePhoto;
    private String cityid;

    private BottomDialog dialog;

    private String provinceCode;
    private String cityCode;
    private String areaCode;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_company_identify, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("企业认证");
    }

    @Override
    protected void initializeView() {
        cbSelect.setEnabled(false);
        cbSelect.setClickable(false);
    }

    @Override
    protected void loadDataFromServer() {
        Map<String, String> params = new HashMap<>();
        mModel.requestCompanyIdentifyDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                    cityList.clear();
                    if (!StringUtil.isBlank(json.getString("provinceInfo"))) {
                        cityList.addAll(JSON.parseArray(json.getString("provinceInfo"), CityBean.class));
                    }
                    if (!StringUtil.isBlank(json.getString("companyData"))) {
                        CompanyBean bean = JSON.parseObject(json.getString("companyData"), CompanyBean.class);
                        mDataManager.setValueToView(etCompany, bean.getCname(), "");
                        mDataManager.setValueToView(etName, bean.getShorName(), "");
                        mDataManager.setValueToView(etAddress, bean.getAddress(), "");
                        mDataManager.setValueToView(etCard, bean.getIdCard(), "");
                        ivCard.loadHttpImage(bean.getLicensePhoto());
                        cbSelect.setChecked(true);
                    }
                    if (json.getString("canModify").equals("true")) {
                        etCompany.setFocusable(true);
                        etName.setFocusable(true);
                        etAddress.setFocusable(true);
                        etCard.setFocusable(true);
                        llCity.setClickable(true);
                        ivCard.setClickable(true);
                        llSelect.setClickable(true);
                        llMenu.setVisibility(View.VISIBLE);
                    } else {
                        etCompany.setFocusable(false);
                        etName.setFocusable(false);
                        etAddress.setFocusable(false);
                        etCard.setFocusable(false);
                        llCity.setClickable(false);
                        ivCard.setClickable(false);
                        llSelect.setClickable(false);
                        llMenu.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @OnClick({R.id.llCity, R.id.ivCard, R.id.llSelect, R.id.tvDisagree, R.id.tvAgree, R.id.tvAgreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCity:
                if (dialog != null) {
                    dialog.show();
                } else {
                    dialog = new BottomDialog(this);
                    dialog.setOnAddressSelectedListener(this);
                    dialog.setDialogDismisListener(this);
                    dialog.setTextSize(14);//设置字体的大小
                    dialog.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
                    dialog.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色
                    dialog.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色
                    dialog.setSelectorAreaPositionListener(this);
                    dialog.show();
                }
                break;
            case R.id.ivCard:
                mDataManager.openPhotoSelectSingle(activity, new IPhotoSelectCallback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(List<String> photoList) {
                        if (!photoList.isEmpty()) {
                            Map<String, String> params = new HashMap<>();
                            params.put("dirname", "company");
                            mModel.uploadImageSingle(params, photoList.get(0), new ZnzHttpListener() {
                                @Override
                                public void onSuccess(JSONObject responseOriginal) {
                                    super.onSuccess(responseOriginal);
                                    if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                                        licensePhoto = responseOriginal.getString("data");
                                        ivCard.loadHttpImage(licensePhoto);
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
                }, true);
                break;
            case R.id.llSelect:
                cbSelect.setChecked(!cbSelect.isChecked());
                break;
            case R.id.tvDisagree:
                new UIAlertDialog(activity)
                        .builder()
                        .setMsg("确认退出？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", v2 -> {
                            finish();
                        })
                        .show();
                break;
            case R.id.tvAgree:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etCompany))) {
                    mDataManager.showToast("请输入企业名称");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etName))) {
                    mDataManager.showToast("请输入企业简称");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etAddress))) {
                    mDataManager.showToast("请输入公司地址");
                    return;
                }
                if (StringUtil.isBlank(mDataManager.getValueFromView(etCard))) {
                    mDataManager.showToast("请输入机构代码或者信用代码");
                    return;
                }
                if (StringUtil.isBlank(provinceCode) | StringUtil.isBlank(cityCode) | StringUtil.isBlank(areaCode)) {
                    mDataManager.showToast("请选择企业注册地址");
                    return;
                }
                if (StringUtil.isBlank(licensePhoto)) {
                    mDataManager.showToast("请上传证件");
                    return;
                }
                if (!cbSelect.isChecked()) {
                    mDataManager.showToast("请同意用户协议");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("optype", "identify");
                params.put("cname", mDataManager.getValueFromView(etCompany));
                params.put("shorName", mDataManager.getValueFromView(etName));
                params.put("idCard", mDataManager.getValueFromView(etCard));
                params.put("address", mDataManager.getValueFromView(etAddress));
                params.put("provinceid", provinceCode);
                params.put("cityid", cityCode);
                params.put("areaid", areaCode);
                params.put("licensePhoto", licensePhoto);
                mModel.requestCompany(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        mDataManager.showToast("创建成功");
                        finish();
                    }
                });

                break;
            case R.id.tvAgreement:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventManager.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        switch (event.getFlag()) {
            case EventTags.REFRESH_CITY_IDENTIFY:
                CityBean bean = (CityBean) event.getBean();
                tvCity.setText(bean.getName());
                cityid = bean.getId();
                break;
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.id + "");
        cityCode = (city == null ? "" : city.id + "");
        areaCode = (county == null ? "" : county.id + "");
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + areaCode);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        tvCity.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void selectorAreaPosition(int provincePosition, int cityPosition, int countyPosition, int streetPosition) {

    }
}
