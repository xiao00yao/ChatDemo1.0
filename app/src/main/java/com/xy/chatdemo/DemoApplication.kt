package com.xy.chatdemo

import android.app.Application
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
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
        val options = EMOptions()
        // set if accept the invitation automatically
        // set if accept the invitation automatically
        options.acceptInvitationAlways = false
        // set if you need read ack
        // set if you need read ack
        options.requireAck = true
        // set if you need delivery ack
        // set if you need delivery ack
        options.requireDeliveryAck = false
        EMClient.getInstance().init(this,options)
        EaseUI.getInstance().init(this,null)
    }
}