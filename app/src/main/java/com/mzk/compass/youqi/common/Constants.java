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
    public static final String share_url = "http://api.ukee.com/";

    /**
     * SharePreference key
     */
    public interface User {
        String AVATAR = "avatar";
        String ISVIP = "isVip";
        String USERNAME = "username";
        String NICKNAME = "nickname";
        String YONGJIN = "yongjin";
        String USERTYPE = "usertype";
        String BALANCE = "balance";
        String COMPANYNAME = "companyName";
        String EMAIL = "email";
        String ADDRESS = "address";
        String INTRODUCE = "introduce";
        String CNAME = "cname";
        String VIPTIME = "vipTime";
        String DUTY = "duty";
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
        int ProjectComment = 18;
        int ProjectCommentSection = 19;
        int ProjectCommentNoData = 20;

        int PeopleState = 30;
        int PeopleIntro = 31;
    }


    public interface TreeType {
        int TreeDetail = 0;
        int FeedbackList = 1;

    }

    public interface SearchType {
        String SEARCHTYPE = "SearchType";
    }
}
