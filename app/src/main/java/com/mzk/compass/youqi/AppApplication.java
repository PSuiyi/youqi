package com.mzk.compass.youqi;

import com.bugtags.library.Bugtags;
import com.znz.compass.znzlibray.ZnzApplication;

public class AppApplication extends ZnzApplication {

    @Override
    public void onCreate() {
        super.onCreate();


        //初始化bugtags
        Bugtags.start("f8a8976eb07316f15ce7e91241c0afb1", this, Bugtags.BTGInvocationEventBubble);
    }
}
