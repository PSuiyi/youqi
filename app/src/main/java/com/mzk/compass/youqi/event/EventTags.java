package com.mzk.compass.youqi.event;

/**
 * Created by xxc on 2017/2/14.
 */

public interface EventTags {
    int REFRESH_USERINFO = 0X100; //修改个人资料
    int REFRESH_SEARCH_VALUE = 0x101;
    int REFRESH_SEARCH_HISTORY = 0x102;//搜索历史记录刷新
    int REFRESH_SEARCH = 0x103;//搜索
    int REFRESH_SEARCH_POP = 0x104;//搜索条件
    int REFRESH_NICKNAME = 0x105;//修改名称后刷新界面

    int REFRESH_MESSAGE_EDIT = 0x200;//消息编辑
    int REFRESH_CITY = 0x300;//城市选择 刷新

    int REFRESH_PALTFORM_MESSAGE_NUM = 0X400;//刷新平台消息未读消息数目
    int REFRESH_SYSTEM_MESSAGE_NUM = 0X401;//刷新平台消息未读消息数目

    int REFRESH_POP = 0x600;//popwindow消失刷新当前界面
    int REFRESH_DELETE_MINE_MEETING = 0X601; //我的预约长按删除，刷新右上角图标

    int REFRESH_ORDER = 0X700; //订单取消或者确认服务，刷新订单列表

}
