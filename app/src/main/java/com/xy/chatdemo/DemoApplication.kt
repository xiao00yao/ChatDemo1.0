package com.xy.chatdemo

import android.app.Application
import com.hyphenate.chat.EMClient
import com.hyphenate.easeui.EaseUI

class DemoApplication:Application() {
    lateinit var mApplication:Application
    companion object{
        fun getIntance():Application{
            return Application()
        }
    }

    override fun onCreate() {
        super.onCreate()
        EMClient.getInstance().init(this,null)
        EaseUI.getInstance().init(this,null)
    }
}