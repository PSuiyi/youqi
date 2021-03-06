package com.mzk.compass.youqi.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mzk.compass.youqi.R;
import com.mzk.compass.youqi.base.BaseAppActivity;
import com.mzk.compass.youqi.bean.SearchHistoryBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.db.DbManagerSearch;
import com.mzk.compass.youqi.event.EventRefresh;
import com.mzk.compass.youqi.event.EventTags;
import com.mzk.compass.youqi.ui.home.organ.OrganListFrag;
import com.mzk.compass.youqi.ui.home.people.PeopleListFrag;
import com.mzk.compass.youqi.ui.home.product.ProductListFrag;
import com.mzk.compass.youqi.ui.home.project.ProjectListFrag;
import com.mzk.compass.youqi.ui.news.NewsListFrag;
import com.znz.compass.znzlibray.eventbus.EventManager;
import com.znz.compass.znzlibray.utils.FragmentUtil;
import com.znz.compass.znzlibray.utils.StringUtil;
import com.znz.compass.znzlibray.views.EditTextWithDel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Date： 2017/5/23 2017
 * User： PSuiyi
 * Description：
 */

public class SearchCommonAct extends BaseAppActivity implements TextWatcher {

    @Bind(R.id.etSerach)
    EditTextWithDel etSerach;
    @Bind(R.id.tvSearchCancel)
    TextView tvSearchCancel;
    @Bind(R.id.container)
    FrameLayout container;
    private SearchHistoryFrag fragment;
    private ProjectListFrag projectListFrag;
    private PeopleListFrag peopleListFrag;
    private OrganListFrag organListFrag;
    private ProductListFrag productListFrag;
    private NewsListFrag newsListFrag;

    private FragmentUtil fragmentUtil;
    private FragmentManager fragmentManager;

    private SearchHistoryBean bean;
    private DbManagerSearch dbManager;
    private String searchContent="";

    private String from;

    @Override
    protected int[] getLayoutResource() {
        return new int[]{R.layout.act_search_common};
    }

    @Override
    protected void initializeVariate() {
        if (getIntent().hasExtra("from")) {
            from = getIntent().getStringExtra("from");
        }
        fragment = new SearchHistoryFrag();
//        projectListFrag = ProjectListFrag.newInstance("搜索", searchContent);
//        peopleListFrag = PeopleListFrag.newInstance("搜索", searchContent);
//        organListFrag = OrganListFrag.newInstance("搜索", searchContent);
//        productListFrag = ProductListFrag.newInstance("搜索", searchContent);
//        newsListFrag = NewsListFrag.newInstance("搜索", searchContent);

        fragmentUtil = new FragmentUtil();
        fragmentManager = getSupportFragmentManager();

        dbManager = DbManagerSearch.getInstance(activity);
    }

    @Override
    protected void initializeNavigation() {
    }

    @Override
    protected void initializeView() {
        switch (from) {
            case "搜索项目":
                etSerach.setHint("搜索项目");
                break;
            case "找服务":
                etSerach.setHint("找服务");
                break;
            case "搜索投资人":
                etSerach.setHint("搜索投资人");
                break;
            case "搜索机构":
            case "搜索投资机构":
                etSerach.setHint("搜索投资机构");
                break;
            case "找商品":
                etSerach.setHint("找服务");
                break;
            case "搜索优报道":
                etSerach.setHint("搜索优报道");
                break;
        }
        fragmentManager.beginTransaction().add(R.id.container, fragment).commit();
        fragmentUtil.mContent = fragment;

        etSerach.addTextChangedListener(this);
        /**
         * 设置回车键的文案
         * 去往：IME_ACTION_GO
         * 搜索：IME_ACTION_SEARCH
         * 发送：IME_ACTION_SEND
         * 下一步：IME_ACTION_NEXT
         * 完成：IME_ACTION_DONE
         */
        etSerach.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSerach.setOnEditorActionListener((textView, actionId, event) -> {
            //当actionId == XX_SEND 或者 XX_DONE时都触发
            //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                //本地保存历史记录
                if (!StringUtil.isBlank(textView.getText().toString().trim())) {
                    bean = new SearchHistoryBean();
                    bean.setName(textView.getText().toString().trim());
                    bean.setType(mDataManager.readTempData(Constants.SearchType.SEARCHTYPE));
                    dbManager.addSingleToDB(bean);
                }
                //收起软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                searchContent = textView.getText().toString().trim();
                switch (from) {
                    case "搜索项目":
                        if (projectListFrag == null) {
                            projectListFrag = ProjectListFrag.newInstance("搜索", searchContent);
                        } else {
                            projectListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PROJECT, searchContent));
                        }
                        fragmentUtil.switchContent(projectListFrag, R.id.container, fragmentManager);
                        break;
                    case "找服务":
                        if (productListFrag == null) {
                            productListFrag = ProductListFrag.newInstance("搜索", searchContent);
                        } else {
                            productListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PRODUCT, searchContent));
                        }
                        fragmentUtil.switchContent(productListFrag, R.id.container, fragmentManager);
                        break;
                    case "搜索投资人":
                        if (peopleListFrag == null) {
                            peopleListFrag = PeopleListFrag.newInstance("搜索", searchContent);
                        } else {
                            peopleListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PEOPLE, searchContent));
                        }
                        fragmentUtil.switchContent(peopleListFrag, R.id.container, fragmentManager);
                        break;
                    case "搜索投资机构":
                        if (organListFrag == null) {
                            organListFrag = OrganListFrag.newInstance("搜索", searchContent);
                        } else {
                            organListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_ORGAN, searchContent));
                        }
                        fragmentUtil.switchContent(organListFrag, R.id.container, fragmentManager);
                        break;
                    case "找商品":
                        if (productListFrag == null) {
                            productListFrag = ProductListFrag.newInstance("搜索", searchContent);
                        } else {
                            productListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PRODUCT, searchContent));
                        }
                        fragmentUtil.switchContent(productListFrag, R.id.container, fragmentManager);
                        break;
                    case "搜索优报道":
                        if (newsListFrag == null) {
                            newsListFrag = NewsListFrag.newInstance("搜索", searchContent);
                        } else {
                            newsListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_NEWS, searchContent));
                        }
                        fragmentUtil.switchContent(newsListFrag, R.id.container, fragmentManager);
                        break;
                }

            }
            return false;
        });
    }

    @Override
    protected void loadDataFromServer() {

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
            case EventTags.REFRESH_POP:
                break;
            case EventTags.REFRESH_SEARCH_VALUE:
                etSerach.setText(event.getValue());
                searchContent=event.getValue();
                switch (from) {
                    case "搜索项目":
                        if (projectListFrag == null) {
                            projectListFrag = ProjectListFrag.newInstance("搜索", searchContent);
                        } else {
                            projectListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PROJECT, searchContent));
                        }
                        fragmentUtil.switchContent(projectListFrag, R.id.container, fragmentManager);
                        break;
                    case "找服务":
                        if (productListFrag == null) {
                            productListFrag = ProductListFrag.newInstance("搜索", searchContent);
                        } else {
                            productListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PRODUCT, searchContent));
                        }
                        fragmentUtil.switchContent(productListFrag, R.id.container, fragmentManager);
                        break;
                    case "搜索投资人":
                        if (peopleListFrag == null) {
                            peopleListFrag = PeopleListFrag.newInstance("搜索", searchContent);
                        } else {
                            peopleListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PEOPLE, searchContent));
                        }
                        fragmentUtil.switchContent(peopleListFrag, R.id.container, fragmentManager);
                        break;
                    case "搜索投资机构":
                        if (organListFrag == null) {
                            organListFrag = OrganListFrag.newInstance("搜索", searchContent);
                        } else {
                            organListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_ORGAN, searchContent));
                        }
                        fragmentUtil.switchContent(organListFrag, R.id.container, fragmentManager);
                        break;
                    case "找商品":
                        if (productListFrag == null) {
                            productListFrag = ProductListFrag.newInstance("搜索", searchContent);
                        } else {
                            productListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_PRODUCT, searchContent));
                        }
                        fragmentUtil.switchContent(productListFrag, R.id.container, fragmentManager);
                        break;
                    case "搜索优报道":
                        if (newsListFrag == null) {
                            newsListFrag = NewsListFrag.newInstance("搜索", searchContent);
                        } else {
                            newsListFrag.setKeywords(searchContent);
                            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH_NEWS, searchContent));
                        }
                        fragmentUtil.switchContent(newsListFrag, R.id.container, fragmentManager);
                        break;
                }

                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (StringUtil.isBlank(s.toString().trim())) {
            fragmentUtil.switchContent(fragment, R.id.container, fragmentManager);
            EventBus.getDefault().post(new EventRefresh(EventTags.REFRESH_SEARCH_HISTORY));
        } else {
            EventBus.getDefault().postSticky(new EventRefresh(EventTags.REFRESH_SEARCH, s.toString().trim()));
        }
    }

    @OnClick({R.id.tvSearchCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSearchCancel:
                onBackPressed();
                break;
        }
    }
}
