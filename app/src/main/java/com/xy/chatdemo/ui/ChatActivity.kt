package com.xy.chatdemo.ui

import android.os.Bundle
import com.hyphenate.easeui.EaseConstant.EXTRA_USER_ID
import com.hyphenate.easeui.ui.EaseChatFragment
import com.xy.chatdemo.base.BaseActivity
import com.xy.chatdemo.R
import kotlinx.android.synthetic.main.chat_activity.*

/**
 * 聊天界面
 */
class ChatActivity(): BaseActivity() {
    var toChatUsername: String? = null
    lateinit var chatFragment : EaseChatFragment
    override fun setLayout(): Int {
        return R.layout.chat_activity
    }

    override fun initVariables(savedInstanceState: Bundle?) {
        var chatFragment = ChatFragment()
        //pass parameters to chat fragment
        chatFragment.arguments = intent.extras
        //get user id or group id
        toChatUsername = intent.extras!!.getString(EXTRA_USER_ID)
       supportFragmentManager.beginTransaction().add(chat_framelayout.id,chatFragment).commit()
    }

}