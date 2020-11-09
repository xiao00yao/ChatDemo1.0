package com.xy.chatdemo.base

import android.app.Application
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.hyphenate.easeui.EaseUI
import com.tencent.bugly.Bugly
import com.xy.chatdemo.utils.DemoHelper


class DemoApplication: Application() {
    lateinit var mApplication:Application
    companion object{
        fun getIntance():Application{
            return Application()
        }
    }

    override fun onCreate() {
        super.onCreate()
        val options = EMOptions()
        // set if accept the invitation automatically
        options.acceptInvitationAlways = false
        // set if you need read ack
        options.requireAck = true
        // set if you need delivery ack
        options.requireDeliveryAck = false
        EMClient.getInstance().init(this,options)
        EaseUI.getInstance().init(this,null)
        DemoHelper.init(this)
//CrashReport
        Bugly.init(applicationContext, "77aea7ef1c", false) //bugly内侧分发升级

        Bugly.setIsDevelopmentDevice(this,false)
//        Beta.autoInit = true
//
//        /**
//         * true表示初始化时自动检查升级
//         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
//         */
//        Beta.autoCheckUpgrade = true
//
//        /**
//         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
//         */
//        Beta.initDelay = 1000
//
//        /**
//         * 设置通知栏大图标，largeIconId为项目中的图片资源；
//         */
//        Beta.largeIconId = R.mipmap.sym_def_app_icon
//
//        /**
//         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
//         */
//        Beta.smallIconId = R.mipmap.sym_def_app_icon
//
//        /**
//         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
//         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
//         */
//        Beta.defaultBannerId = R.mipmap.sym_def_app_icon
//
//        /**
//         * 设置sd卡的Download为更新资源保存目录;
//         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
//         */
//        Beta.storageDir =
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//
//        /**
//         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
//         */
//        Beta.showInterruptedStrategy = false
//
//        /**
//         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
//         * 不设置会默认所有activity都可以显示弹窗;
//         */
//        Beta.canShowUpgradeActs.add(MainActivity::class.java)

    }

}