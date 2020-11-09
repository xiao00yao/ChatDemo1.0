package com.xy.chatdemo.base;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.tencent.bugly.Bugly;
import com.xy.chatdemo.utils.DemoHelper;
import com.xy.mylibrary.base.BaseLibraryApplication;

public class DemoApp extends BaseLibraryApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
        // set if accept the invitation automatically
        options.setAcceptInvitationAlways(false) ;
        // set if you need read ack
        options.setRequireAck(true);
        // set if you need delivery ack
        options.setRequireDeliveryAck(false);
        EMClient.getInstance().init(this,options);
        EaseUI.getInstance().init(this,null);
        DemoHelper.INSTANCE.init(this);
//CrashReport
        Bugly.init(getApplicationContext(), "77aea7ef1c", false); //bugly内侧分发升级

        Bugly.setIsDevelopmentDevice(this,false);

    }
}
