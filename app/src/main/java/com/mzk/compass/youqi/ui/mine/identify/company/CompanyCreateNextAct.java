package com.mzk.compass.youqi.ui.mine.identify.company;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.CompanyBean;
import com.mzk.compass.youqi.bean.IndustryBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
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

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/24.
 */

public class CompanyCreateNextAct extends BaseAppActivity implements OnAddressSelectedListener, AddressSelector.OnDialogCloseListener, AddressSelector.onSelectorAreaPositionListener {


    @Bind(R.id.tvHangye)
    TextView tvHangye;
    @Bind(R.id.ivCover)
    HttpImageView ivCover;
    @Bind(R.id.etCompany)
    EditText etCompany;
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etAddress)
    EditText etAddress;
    @Bind(R.id.etURL)
    EditText etURL;
    @Bind(R.id.etChuanzhen)
    EditText etChuanzhen;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.etIntro)
    EditText etIntro;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    private String provinceCode;
    private String cityCode;
    private String areaCode;

    private List<IndustryBean> hangyeList = new ArrayList<>();

    private String url;
    private String tradeid;

    private BottomDialog dialog;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_compant_create_next, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("创建企业");
        znzToolBar.setNavRightText("提交");
        znzToolBar.setOnNavRightClickListener(view -> {
            if (StringUtil.isBlank(mDataManager.getValueFromView(etCompany))) {
                mDataManager.showToast("请输入企业名称");
                return;
            }
            if (StringUtil.isBlank(mDataManager.getValueFromView(etName))) {
                mDataManager.showToast("请输入企业简称");
                return;
            }
            if (StringUtil.isBlank(areaCode)) {
                mDataManager.showToast("请选择地址");
                return;
            }
            if (StringUtil.isBlank(mDataManager.getValueFromView(etAddress))) {
                mDataManager.showToast("请输入公司地址");
                return;
            }
            if (StringUtil.isBlank(mDataManager.getValueFromView(etURL))) {
                mDataManager.showToast("请输入公司网址");
                return;
            }
            if (StringUtil.isBlank(mDataManager.getValueFromView(etChuanzhen))) {
                mDataManager.showToast("请输入传真");
                return;
            }
            if (StringUtil.isBlank(mDataManager.getValueFromView(etEmail))) {
                mDataManager.showToast("请输入邮箱");
                return;
            }
            if (StringUtil.isBlank(mDataManager.getValueFromView(etIntro))) {
                mDataManager.showToast("请输入简介");
                return;
            }
            if (StringUtil.isBlank(url)) {
                mDataManager.showToast("请上传公司logo");
                return;
            }
            if (StringUtil.isBlank(tradeid)) {
                mDataManager.showToast("请选择行业");
                return;
            }
            Map<String, String> params = new HashMap<>();
            params.put("optype", "create");
            params.put("cname", mDataManager.getValueFromView(etCompany));
            params.put("shorName", mDataManager.getValueFromView(etName));
            params.put("website", mDataManager.getValueFromView(etURL));
            params.put("address", mDataManager.getValueFromView(etAddress));
            params.put("email", mDataManager.getValueFromView(etEmail));
            params.put("fax", mDataManager.getValueFromView(etChuanzhen));
            params.put("summary", mDataManager.getValueFromView(etIntro));
            params.put("logo", url);
            params.put("tradeid", tradeid);
            params.put("provinceid", provinceCode);
            params.put("cityid", cityCode);
            params.put("areaid", areaCode);
            mModel.requestCompany(params, new ZnzHttpListener() {
                @Override
                public void onSuccess(JSONObject responseOriginal) {
                    super.onSuccess(responseOriginal);
                    mDataManager.showToast("创建成功");
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
        mModel.requestCompanyDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                    JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                    hangyeList.clear();
                    hangyeList.addAll(JSON.parseArray(json.getString("tradeInfo"), IndustryBean.class));

                    if (!StringUtil.isBlank(json.getString("companyData"))) {
                        CompanyBean bean = JSON.parseObject(json.getString("companyData"), CompanyBean.class);
                        mDataManager.setValueToView(etCompany, bean.getCname(), "");
                        mDataManager.setValueToView(etName, bean.getShorName(), "");
                        mDataManager.setValueToView(etAddress, bean.getAddress(), "");
                        mDataManager.setValueToView(etURL, bean.getWebsite(), "");
                        mDataManager.setValueToView(etChuanzhen, bean.getFax(), "");
                        mDataManager.setValueToView(etEmail, bean.getEmail(), "");
                        mDataManager.setValueToView(etIntro, bean.getSummary(), "");
                        if (bean.getTradeid() != null && !bean.getTradeid().isEmpty()) {
                            String str = "";
                            for (IndustryBean industryBean : bean.getTradeid()) {
                                str = industryBean.getName() + "," + str;
                            }
                            tvHangye.setText(str.substring(0, str.length() - 1));
                        }
                    }

                    if (json.getString("canModify").equals("true")) {
                        znzToolBar.setNavRightText("提交");
                        etCompany.setFocusable(true);
                        etName.setFocusable(true);
                        etAddress.setFocusable(true);
                        etURL.setFocusable(true);
                        etChuanzhen.setFocusable(true);
                        etEmail.setFocusable(true);
                        etIntro.setFocusable(true);
                    } else {
                        znzToolBar.setNavRightText("");
                        etCompany.setFocusable(false);
                        etName.setFocusable(false);
                        etAddress.setFocusable(false);
                        etURL.setFocusable(false);
                        etChuanzhen.setFocusable(false);
                        etEmail.setFocusable(false);
                        etIntro.setFocusable(false);
                    }
                }
            }
        });
    }

    @OnClick({R.id.llCover, R.id.llHangye, R.id.tvAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCover:
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
                                        url = responseOriginal.getString("data");
                                        ivCover.loadHttpImage(url);
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
            case R.id.llHangye:
                Bundle bundle = new Bundle();
                bundle.putString("from", "所属行业");
                bundle.putSerializable("list", (Serializable) hangyeList);
                gotoActivity(IndustryAct.class, bundle);
                break;
            case R.id.tvAddress:
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
            case EventTags.REFRESH_HANYE:
                List<IndustryBean> list = (List<IndustryBean>) event.getBean();
                String str = "";
                String ids = "";
                for (IndustryBean industryBean : list) {
                    str = industryBean.getName() + "," + str;
                    ids = industryBean.getId() + "," + ids;
                }
                tvHangye.setText(str.substring(0, str.length() - 1));
                tradeid = ids.substring(0, str.length() - 1);
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
        tvAddress.setText(s);
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
