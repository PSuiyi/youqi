package com.mzk.compass.youqi.event;

/**
 * Created by xxc on 2017/2/14.
 */

public interface EventTags {
    int REFRESH_USERINFO = 0X100; //修改个人资料
    int REFRESH_SEARCH_VALUE = 0x101;
    int REFRESH_SEARCH_HISTORY = 0x102;//搜索历史记录刷新
    int REFRESH_SEARCH = 0x103;//搜索
    int REFRESH_SEARCH_PROJECT = 0x104;//搜索项目
    int REFRESH_SEARCH_PRODUCT = 0x105;//搜索服务
    int REFRESH_SEARCH_PEOPLE = 0x106;//搜索投资人
    int REFRESH_SEARCH_ORGAN = 0x107;//搜索机构
    int REFRESH_SEARCH_NEWS = 0x108;//搜索报到

    int REFRESH_MESSAGE_EDIT = 0x200;//消息编辑
    int REFRESH_CITY = 0x300;//城市选择 刷新
    int REFRESH_CITY_IDENTIFY = 0x301;//城市选择 刷新

    int REFRESH_PALTFORM_MESSAGE_NUM = 0X400;//刷新平台消息未读消息数目
    int REFRESH_SYSTEM_MESSAGE_NUM = 0X401;//刷新平台消息未读消息数目

    int REFRESH_MEMBER = 0X500;//添加成员或者修改成员刷新列表

    int REFRESH_POP = 0x600;//popwindow消失刷新当前界面
    int REFRESH_DELETE_MINE_MEETING = 0X601; //我的预约长按删除，刷新右上角图标

    int REFRESH_ORDER = 0X700; //订单取消或者确认服务，刷新订单列表
    int REFRESH_HANYE = 0X800; //行业
    int REFRESH_LUNCI = 0X801; //轮次
    int REFRESH_COLLECT_PROJECT = 0X900; //收藏项目刷新列表
    int REFRESH_COLLECT_PEOPLE = 0X901; //收藏投资人刷新列表
    int REFRESH_COLLECT_ORGAN = 0X902; //收藏机构刷新列表
    int REFRESH_COLLECT_PRODUCT = 0X903; //收藏商品刷新列表
    int REFRESH_COLLECT_NEWS = 0X904; //收藏消息刷新列表

}
