package com.mzk.compass.youqi.ui.home.people;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.IdentifyBean;
import com.mzk.compass.youqi.bean.IndustryBean;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.mine.identify.company.IndustryAct;
import com.znz.compass.znzlibray.bean.TagBean;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.ZnzTagView;
import com.znz.compass.znzlibray.views.gallery.inter.IPhotoSelectCallback;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.ios.ActionSheetDialog.UIActionSheetDialog;

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
 * Date： 2018/5/1 2018
 * User： PSuiyi
 * Description：
 */
public class PeopleApproveAct extends BaseAppActivity {
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.llHangYe)
    LinearLayout llHangYe;
    @Bind(R.id.llLunci)
    LinearLayout llLunci;
    @Bind(R.id.tagHangye)
    ZnzTagView tagHangye;
    @Bind(R.id.tagLunci)
    ZnzTagView tagLunci;
    @Bind(R.id.tvShenFen)
    TextView tvShenFen;
    @Bind(R.id.llShenFen)
    LinearLayout llShenFen;
    @Bind(R.id.ivCard)
    HttpImageView ivCard;
    @Bind(R.id.cbSelect)
    CheckBox cbSelect;
    @Bind(R.id.tvRemind)
    TextView tvRemind;
    @Bind(R.id.tvYiRenZheng)
    TextView tvYiRenZheng;
    @Bind(R.id.tvSubmit)
    TextView tvSubmit;
    @Bind(R.id.ivClear)
    ImageView ivClear;
    @Bind(R.id.etGroupName)
    EditText etGroupName;

    private List<IndustryBean> hangyeList = new ArrayList<>();
    private List<IndustryBean> lunciList = new ArrayList<>();
    private List<IndustryBean> shenFenList = new ArrayList<>();
    private List<TagBean> selectHangye = new ArrayList<>();
    private List<TagBean> selectLunci = new ArrayList<>();

    private String tradeid;
    private String roundsid;
    private String roleid;
    private String nameCard;
    private String groupName;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_people_approve, 1};
    }

    @Override
    protected void initializeVariate() {

    }

    @Override
    protected void initializeNavigation() {
        setTitleName("认证投资人");
    }

    @Override
    protected void initializeView() {
        cbSelect.setChecked(true);
    }

    @Override
    protected void loadDataFromServer() {
        getRequestIdentifyDetail();
    }

    private void getRequestIdentifyDetail() {
        Map<String, String> params = new HashMap<>();
        mModel.requestIdentifyDetail(params, new ZnzHttpListener() {
            @Override
            public void onSuccess(JSONObject responseOriginal) {
                super.onSuccess(responseOriginal);
                JSONObject json = JSON.parseObject(responseOriginal.getString("data"));
                hangyeList.clear();
                lunciList.clear();
                shenFenList.clear();
                selectHangye.clear();
                selectLunci.clear();
                if (!StringUtil.isBlank(json.getString("tradesData"))) {
                    hangyeList.addAll(JSON.parseArray(json.getString("tradesData"), IndustryBean.class));
                }
                if (!StringUtil.isBlank(json.getString("roundsData"))) {
                    lunciList.addAll(JSON.parseArray(json.getString("roundsData"), IndustryBean.class));
                }
                if (!StringUtil.isBlank(json.getString("roleData"))) {
                    shenFenList.addAll(JSON.parseArray(json.getString("roleData"), IndustryBean.class));
                }

                if (!StringUtil.isBlank(json.getString("myApproveInfo"))) {
                    IdentifyBean bean = JSON.parseObject(json.getString("myApproveInfo"), IdentifyBean.class);
                    etName.setText(bean.getRealName());
                    if (bean.getTradeid() != null & !bean.getTradeid().isEmpty()) {
                        for (IndustryBean industryBean : bean.getTradeid()) {
                            TagBean tagBean = new TagBean();
                            tagBean.setId(industryBean.getId());
                            tagBean.setTitle(industryBean.getName());
                            selectHangye.add(tagBean);
                        }
                        tagHangye.setDataList(selectHangye);
                    }
                    if (bean.getRoundsid() != null & !bean.getRoundsid().isEmpty()) {
                        for (IndustryBean industryBean : bean.getRoundsid()) {
                            TagBean tagBean = new TagBean();
                            tagBean.setId(industryBean.getId());
                            tagBean.setTitle(industryBean.getName());
                            selectLunci.add(tagBean);
                        }
                        tagLunci.setDataList(selectLunci);
                    }
                    for (IndustryBean industryBean : shenFenList) {
                        if (industryBean.getId().equals(bean.getRoleid())) {
                            tvShenFen.setText(industryBean.getName());
                        }
                    }

                    mDataManager.setValueToView(etGroupName, bean.getGroupName());

                    getTraids();
                    getRoundids();
                    ivCard.loadRectImage(bean.getNameCard());
                    nameCard = bean.getNameCard();
                    roleid = bean.getRoleid();
                }

                if (json.getString("canSubmit").equals("true")) {
                    llHangYe.setClickable(true);
                    llLunci.setClickable(true);
                    llShenFen.setClickable(true);
                    cbSelect.setClickable(true);
                    ivCard.setClickable(true);
                    tvSubmit.setClickable(true);
                    llHangYe.setEnabled(true);
                    llLunci.setEnabled(true);
                    llShenFen.setEnabled(true);
                    cbSelect.setEnabled(true);
                    ivCard.setEnabled(true);
                    tvSubmit.setEnabled(true);
                    etName.setFocusable(true);
                    tvYiRenZheng.setVisibility(View.GONE);
                    tvSubmit.setVisibility(View.VISIBLE);
                } else {
                    llHangYe.setClickable(false);
                    llLunci.setClickable(false);
                    llShenFen.setClickable(false);
                    cbSelect.setClickable(false);
                    ivCard.setClickable(false);
                    tvSubmit.setClickable(false);
                    llHangYe.setEnabled(false);
                    llLunci.setEnabled(false);
                    llShenFen.setEnabled(false);
                    cbSelect.setEnabled(false);
                    ivCard.setEnabled(false);
                    tvSubmit.setEnabled(false);
                    etName.setFocusable(false);
                    tvYiRenZheng.setVisibility(View.VISIBLE);
                    tvSubmit.setVisibility(View.GONE);
                }

                if (!StringUtil.isBlank(json.getString("isInstitution"))) {
                    if (json.getString("isInstitution").equals("true")) {
                        for (int i = 0; i < shenFenList.size(); i++) {
                            if (shenFenList.get(i).getName().equals("独立投资人")) {
                                shenFenList.remove(i);
                            }
                        }
                        groupName = json.getString("institutionName");
                    }
                }
            }
        });
    }


    @OnClick({R.id.llHangYe, R.id.llLunci, R.id.ivClear, R.id.llShenFen, R.id.llXieyi, R.id.ivCard, R.id.tvSubmit})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.llHangYe:
                bundle.putString("from", "关注行业");
                bundle.putSerializable("list", (Serializable) hangyeList);
                gotoActivity(IndustryAct.class, bundle);
                break;
            case R.id.llLunci:
                bundle.putString("from", "投资轮次");
                bundle.putSerializable("list", (Serializable) lunciList);
                gotoActivity(IndustryAct.class, bundle);
                break;
            case R.id.llShenFen:
                List<String> items = new ArrayList<>();
                for (IndustryBean industryBean : shenFenList) {
                    items.add(industryBean.getName());
                }
                new UIActionSheetDialog(activity)
                        .builder()
                        .addSheetItemList(items, null, which -> {
                            tvShenFen.setText(shenFenList.get(which).getName());
                            roleid = shenFenList.get(which).getId();
                        })
                        .show();
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
                            params.put("dirname", "approveinvestors");
                            mModel.uploadImageSingle(params, photoList.get(0), new ZnzHttpListener() {
                                @Override
                                public void onSuccess(JSONObject responseOriginal) {
                                    super.onSuccess(responseOriginal);
                                    ivClear.setVisibility(View.VISIBLE);
                                    if (!StringUtil.isBlank(responseOriginal.getString("data"))) {
                                        nameCard = responseOriginal.getString("data");
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
            case R.id.llXieyi:
                cbSelect.setChecked(!cbSelect.isChecked());
                break;
            case R.id.tvSubmit:
                if (StringUtil.isBlank(mDataManager.getValueFromView(etName))) {
                    mDataManager.showToast("请输入真实姓名");
                    return;
                }
                if (StringUtil.isBlank(tradeid)) {
                    mDataManager.showToast("请选择行业");
                    return;
                }
                if (StringUtil.isBlank(roundsid)) {
                    mDataManager.showToast("请选择轮次");
                    return;
                }
                if (StringUtil.isBlank(roleid)) {
                    mDataManager.showToast("请选择投资人角色");
                    return;
                }
                if (StringUtil.isBlank(nameCard)) {
                    mDataManager.showToast("请上传名片");
                    return;
                }
                if (!cbSelect.isChecked()) {
                    mDataManager.showToast("请同意用户协议");
                    return;
                }

                Map<String, String> params = new HashMap<>();
                params.put("realName", mDataManager.getValueFromView(etName));
                params.put("tradeid", tradeid);
                params.put("roundsid", roundsid);
                params.put("roleid", roleid);
                params.put("nameCard", nameCard);
                params.put("groupName", mDataManager.getValueFromView(etGroupName));
                mModel.requestIdentify(params, new ZnzHttpListener() {
                    @Override
                    public void onSuccess(JSONObject responseOriginal) {
                        super.onSuccess(responseOriginal);
                        mDataManager.showToast("上传成功");
                        finish();
                    }
                });
                break;
            case R.id.ivClear:
                ivClear.setVisibility(View.GONE);
                ivCard.loadHttpImage("");
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
                selectHangye.clear();
                List<IndustryBean> list = (List<IndustryBean>) event.getBean();
                if (list != null & !list.isEmpty()) {
                    for (IndustryBean industryBean : list) {
                        TagBean tagBean = new TagBean();
                        tagBean.setId(industryBean.getId());
                        tagBean.setTitle(industryBean.getName());
                        selectHangye.add(tagBean);
                    }
                    tagHangye.setDataList(selectHangye);
                    getTraids();
                }

                break;
            case EventTags.REFRESH_LUNCI:
                selectLunci.clear();
                List<IndustryBean> list1 = (List<IndustryBean>) event.getBean();
                if (list1 != null & !list1.isEmpty()) {
                    for (IndustryBean industryBean : list1) {
                        TagBean tagBean = new TagBean();
                        tagBean.setId(industryBean.getId());
                        tagBean.setTitle(industryBean.getName());
                        selectLunci.add(tagBean);
                    }
                    tagHangye.setDataList(selectLunci);
                    getRoundids();
                }
                break;
        }
    }

    private void getTraids() {
        String ids = "";
        for (TagBean industryBean : selectHangye) {
            ids = industryBean.getId() + "," + ids;
        }
        tradeid = ids.substring(0, ids.length() - 1);
    }

    private void getRoundids() {
        String ids = "";
        for (TagBean industryBean : selectLunci) {
            ids = industryBean.getId() + "," + ids;
        }
        roundsid = ids.substring(0, ids.length() - 1);
    }
}
