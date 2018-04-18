package com.mzk.compass.youqi.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.znz.compass.znzlibray.views.imageloder.HttpImageView;
import com.znz.compass.znzlibray.views.rowview.ZnzRowDescription;
import com.znz.compass.znzlibray.views.rowview.ZnzRowGroupView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.common_row_view, 1};
    }

    @Override
    protected void initializeVariate() {

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
                .withValue("叶雨")
                .withTextSize(14)
                .withGravity(true)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "姓名");
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("所属公司")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue("南京美之科有限公司")
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "所属公司");
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("职务")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue("经理")
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "职务");
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("邮箱")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue("fhdghkajh@qq.com")
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "电子邮箱");
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        rowDescriptionList.add(new ZnzRowDescription.Builder()
                .withTitle("地址")
                .withEnableArraw(true)
                .withGravity(true)
                .withValue("江苏省南京市浦口区仙霞西路198号")
                .withTextSize(14)
                .withValueColor(mDataManager.getColor(R.color.text_gray))
                .withTitleColor(mDataManager.getColor(R.color.text_color))
                .withOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "联系地址");
                    gotoActivity(UpdateInfoAct.class, bundle);
                })
                .build());
        commonRowGroup.notifyDataChanged(rowDescriptionList);
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

    @OnClick({R.id.llHeader, R.id.llIntro})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llHeader:
                break;
            case R.id.llIntro:
                gotoActivity(UpdateIntroAct.class);
                break;
        }
    }
}
