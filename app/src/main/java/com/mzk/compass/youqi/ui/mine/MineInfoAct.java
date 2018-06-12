package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.utils.AppUtils;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.network.znzhttp.ZnzHttpListener;
import com.znz.compass.znzlibray.views.gallery.inter.IPhotoSelectCallback;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Date： 2018/4/1 2018
 * User： PSuiyi
 * Description：
 */
public class MineInfoAct extends BaseAppActivity {

    @Bind(R.id.commonRowGroup)
    ZnzRowGroupView commonRowGroup;
    @Bind(R.id.ivHeader)
    HttpImageView ivHeader;
    @Bind(R.id.tvIntro)
    TextView tvIntro;
    private ArrayList<ZnzRowDescription> rowDescriptionList = new ArrayList<>();
    private AppUtils appUtils;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_row_view, 1};
    }

    @Override
    protected void initializeVariate() {
        appUtils = AppUtils.getInstance(context);
    }

    @Override
    protected void initializeNavigation() {
        setTitleName("基本信息");
    }

    @Override
    protected void initializeView() {
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("姓名")
                .withEnableArraw(true)
                .withValue(mDataManager.readTempData(Constants.User.USERNAME))
                .withTextSize(14)
                .withGravity(true)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "姓名");
                    bundle.putString("value", rowDescriptionList.get(0).getValue());
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("所属公司")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue(appUtils.getCompanyName())
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "所属公司");
                    bundle.putString("value", rowDescriptionList.get(1).getValue());
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("职务")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue(mDataManager.readTempData(Constants.User.DUTY))
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "职务");
                    bundle.putString("value", rowDescriptionList.get(2).getValue());
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("邮箱")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue(mDataManager.readTempData(Constants.User.EMAIL))
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "电子邮箱");
                    bundle.putString("value", rowDescriptionList.get(3).getValue());
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("地址")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue(mDataManager.readTempData(Constants.User.ADDRESS))
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "联系地址");
                    bundle.putString("value", rowDescriptionList.get(4).getValue());
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        commonRowGroup.notifyDataChanged(rowDescriptionList);
        mDataManager.setValueToView(tvIntro, mDataManager.readTempData(Constants.User.INTRODUCE),"");
        ivHeader.loadHeaderImage(mDataManager.readTempData(Constants.User.AVATAR));
    }

    @Override
    protected void loadDataFromServer() {

    }

    @OnClick({R.id.llHeader, R.id.llIntro})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llHeader:
                mDataManager.openPhotoSelectSingle(activity, new IPhotoSelectCallback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(List<String> photoList) {
                        if (!photoList.isEmpty()) {
                            ivHeader.loadHeaderImage(photoList.get(0));
                            Map<String, String> params = new HashMap<>();
                            params.put("dirname", "avatar");
                            params.put("value", photoList.get(0));
                            mModel.uploadImageSingle(params, photoList.get(0), new ZnzHttpListener() {
                                @Override
                                public void onSuccess(JSONObject responseOriginal) {
                                    super.onSuccess(responseOriginal);
                                    String url = responseOriginal.getString("data");
                                    Map<String, String> params1 = new HashMap<>();
                                    params1.put("key", "avatar");
                                    params1.put("value", url);
                                    mModel.requestUpdateUserInfo(params1, new ZnzHttpListener() {
                                        @Override
                                        public void onSuccess(JSONObject responseOriginal) {
                                            super.onSuccess(responseOriginal);
                                            ivHeader.loadHeaderImage(url);
                                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_USERINFO));
                                            finish();
                                        }
                                    });

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
            case R.id.llIntro:
                Bundle bundle = new Bundle();
                bundle.putString("value", mDataManager.getValueFromView(tvIntro));
                gotoActivity(UpdateIntroAct.class, bundle);
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
            case EventTags.REFRESH_USERINFO:
                rowDescriptionList.get(0).setValue(mDataManager.readTempData(Constants.User.USERNAME));
                rowDescriptionList.get(1).setValue(appUtils.getCompanyName());
                rowDescriptionList.get(2).setValue(mDataManager.readTempData(Constants.User.DUTY));
                rowDescriptionList.get(3).setValue(mDataManager.readTempData(Constants.User.EMAIL));
                rowDescriptionList.get(4).setValue(mDataManager.readTempData(Constants.User.ADDRESS));
                commonRowGroup.notifyDataChanged(rowDescriptionList);
                mDataManager.setValueToView(tvIntro, mDataManager.readTempData(Constants.User.INTRODUCE));
                break;
        }
    }

}
