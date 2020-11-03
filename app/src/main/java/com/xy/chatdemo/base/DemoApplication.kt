package com.xy.chatdemo.base

import android.app.Application
import com.hyphenate.easeui.EaseUI

class DemoApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        EaseUI.getInstance().init(this,null)
    }
}