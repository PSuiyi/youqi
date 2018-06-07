package com.mzk.compass.youqi.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.mzk.compass.youqi.bean.BannerBean;
import com.mzk.compass.youqi.bean.UserBean;
import com.mzk.compass.youqi.common.Constants;
import com.mzk.compass.youqi.ui.common.WebViewAct;
import com.mzk.compass.youqi.ui.home.organ.OrganDetailAct;
import com.mzk.compass.youqi.ui.home.people.PeopleDetailAct;
import com.mzk.compass.youqi.ui.home.product.ProductDetailAct;
import com.mzk.compass.youqi.ui.home.project.ProjectDetailAct;
import com.mzk.compass.youqi.ui.news.NewsDetailAct;
import com.znz.compass.znzlibray.ZnzApplication;
import com.znz.compass.znzlibray.common.DataManager;
import com.znz.compass.znzlibray.utils.StringUtil;

import java.math.BigDecimal;

/**
 * Date： 2017/5/15 2017
 * User： PSuiyi
 * Description：
 */

public class AppUtils {

    private static AppUtils instance;
    private DataManager mDataManager;

    private AppUtils(Context context) {
        mDataManager = DataManager.getInstance(context);
    }

    public static AppUtils getInstance(Context context) {
        if (instance == null) {
            instance = new AppUtils(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * 保存用户信息
     *
     * @param bean
     */
    public void saveUserData(UserBean bean) {
        mDataManager.saveTempData(Constants.User.AVATAR, bean.getAvatar());
        mDataManager.saveTempData(Constants.User.ISVIP, bean.getIsVip());
        mDataManager.saveTempData(Constants.User.USERNAME, bean.getUsername());
        mDataManager.saveTempData(Constants.User.NICKNAME, bean.getNickName());
        mDataManager.saveTempData(Constants.User.YONGJIN, bean.getYongjin());
        mDataManager.saveTempData(Constants.User.USERTYPE, bean.getUsertype());
        mDataManager.saveTempData(Constants.User.BALANCE, bean.getBalance());
        mDataManager.saveTempData(Constants.User.COMPANYNAME, bean.getCompanyName());
        mDataManager.saveTempData(Constants.User.EMAIL, bean.getEmail());
        mDataManager.saveTempData(Constants.User.ADDRESS, bean.getAddress());
        mDataManager.saveTempData(Constants.User.INTRODUCE, bean.getIntroduce());
        mDataManager.saveTempData(Constants.User.CNAME, bean.getCname());
        mDataManager.saveTempData(Constants.User.VIPTIME, bean.getVipTime());
    }


    /**
     * 获取自己的昵称
     *
     * @return
     */
    public String getUserName() {
        String result = "";
        DataManager mDataManager = DataManager.getInstance(ZnzApplication.getContext());
        if (StringUtil.isBlank(mDataManager.readTempData(Constants.User.NICKNAME))) {
            if (StringUtil.isBlank(Constants.User.USERNAME)) {
                result = "暂无昵称";
            } else {
                result = mDataManager.readTempData(Constants.User.USERNAME);
            }
        } else {
            result = mDataManager.readTempData(Constants.User.NICKNAME);
        }
        return result;
    }

    public String getCompanyName() {
        String result = "";
        if (!StringUtil.isBlank(mDataManager.readTempData(Constants.User.CNAME))) {
            result = mDataManager.readTempData(Constants.User.CNAME);
        } else {
            if (!StringUtil.isBlank(mDataManager.readTempData(Constants.User.COMPANYNAME))) {
                result = mDataManager.readTempData(Constants.User.COMPANYNAME);
            } else {
                result = "暂无公司";
            }
        }
        return result;
    }

    /**
     * 获取某个用户的昵称
     *
     * @param bean
     * @return
     */
    public String getUserName(UserBean bean) {
        String result = "";
        if (!StringUtil.isBlank(bean.getUsername())) {
            result = bean.getUsername();
        } else {
//            if (!StringUtil.isBlank(bean.getUsername())) {
//                result = StringUtil.getSignPhone(bean.getPhone());
//            } else {
//                result = "暂无昵称";
//            }
        }
        return result;
    }

    public String getMoney(String price, String num) {
        if (StringUtil.isBlank(price)) {
            return "0";
        }
        if (StringUtil.isBlank(num)) {
            return "0";
        }

        BigDecimal pric = new BigDecimal(price);
        BigDecimal number = new BigDecimal(num);

        double money = pric.multiply(number).doubleValue();
        return "￥" + money;
    }

    /**
     * banner点击跳转
     *
     * @param activity
     * @param bean
     */
    public void doBannerClick(Activity activity, BannerBean bean) {
        Bundle bundle = new Bundle();
        switch (bean.getContentType()) {
            case "outside":
                bundle.putString("url", bean.getLink());
                mDataManager.gotoActivity(WebViewAct.class, bundle);
                break;
            case "project":
                bundle.putString("id", bean.getLink());
                mDataManager.gotoActivity(ProjectDetailAct.class, bundle);
                break;
            case "approveinvestors":
                bundle.putString("id", bean.getLink());
                mDataManager.gotoActivity(PeopleDetailAct.class, bundle);
                break;
            case "news":
                bundle.putString("id", bean.getLink());
                mDataManager.gotoActivity(NewsDetailAct.class, bundle);
                break;
            case "products":
                bundle.putString("id", bean.getLink());
                mDataManager.gotoActivity(ProductDetailAct.class, bundle);
                break;
            case "company":
                bundle.putString("id", bean.getLink());
                mDataManager.gotoActivity(OrganDetailAct.class, bundle);
                break;
        }
    }
}
