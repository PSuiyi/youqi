package com.mzk.compass.youqi.common;

/**
 * Created by Administrator on 2017/12/13.
 */


public class Constants {

    public static final String IMAGE_CONNER = "?roundPic/radius/!50p";
    /**
     * 客服电话号码
     */
    public static final String PHONE_NUMBER = "客服电话 4008868";

    /**
     * SharePreference key
     */
    public interface User {
        String BIRTHDAY = "birthday";
        String QQ = "qq";
        String F_PINYIN = "f_pinyin";
        String HEAD_IMG_PATH = "head_img_path";
        String ALIPAY = "alipay";
        String LAST_LOGIN_TIME = "last_login_time";
        String CREATE_TIME = "create_time";
        String LNG = "lng";
        String SIGNATURE = "signature";
        String SEX = "sex";
        String WECHAT = "wechat";
        String CITY_CODE = "city_code";
        String PASSWORD = "password";
        String CITY_NAME = "city_name";
        String UPDATE_TIME = "update_time";
        String LOGIN_NAME = "login_name";
        String PHONE = "phone";
        String USER_ID = "user_id";
        String NICK_NAME = "nick_name";
        String ID = "id";
        String MICROBLOG = "microblog";
        String LAT = "lat";
        String COMMUNITY_ID = "quarters_id";
        String COMMUNITY_NAME = "community_name";
        String PNAME = "pname";
        String HOUSE_HUHAO_ID = "HOUSE_HUHAO_ID";
        String HOUSE_NO = "HOUSE_NO";//房号编码
        String AREA_ID = "AREA_ID";//第三方小区id
        String BUILDING_NO_ID = "BUILDING_NO_ID";
        String UNIT_ID = "UNIT_ID";
        String USER_TYPE = "USER_TYPE";
        String USER_STATUS = "USER_STATUS";
        String USER_LEVEL = "USER_LEVEL";
    }

    /**
     * 输入框输入类型
     */
    public interface EditInputType {
        int NORMAL = 0x00001;
        int PHONE = 0x00002;
        int MULTI = 0x00003;
        int NUMBER = 0x00004;
    }

    public interface BooleanValue {
    }

    public interface AppInfo {
        String CACHE_SIZE = "CACHE_SIZE";//缓存大小
        String SPLASH_IMG_URL = "SPLASH_IMG_URL";//闪屏广告地址
    }

    public interface MultiType {
        int Section = 0;
        int Project = 1;
        int People = 2;
        int Organ = 3;

        int ProjectIntro = 10;
        int ProjectTeam = 11;
        int ProjectProduct = 12;
        int ProjectMarket = 13;
        int ProjectSolution = 14;
        int ProjectMoney = 15;
        int ProjectFinancing = 16;
        int ProjectData = 17;

        int PeopleState = 20;
        int PeopleIntro = 21;
    }


    public interface TreeType {
        int TreeDetail = 0;
        int FeedbackList = 1;

    }

    public interface SearchType {
        String SEARCHTYPE = "SearchType";
    }
}
