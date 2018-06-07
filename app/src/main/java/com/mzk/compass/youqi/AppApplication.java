package com.mzk.compass.youqi;

import com.bugtags.library.Bugtags;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.znz.compass.znzlibray.ZnzApplication;

public class AppApplication extends ZnzApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        UMShareAPI.get(this);
        Config.DEBUG = true;//友盟相关的log日志

        //初始化bugtags
        Bugtags.start("f8a8976eb07316f15ce7e91241c0afb1", this, Bugtags.BTGInvocationEventBubble);
    }

    {
        PlatformConfig.setWeixin("wxdb784b787f2287b7", "b6113318fd23498446df6888a70a5124");
        PlatformConfig.setQQZone("1106869025", "M9xMCuvgddLUHALf");
        PlatformConfig.setSinaWeibo("2183464183", "3e0740d2798140a8a83f7eae53ef4cd9", "http://sns.whalecloud.com");
    }
}
