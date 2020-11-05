package com.xy.chatdemo.ui

import android.content.Context
import android.view.View
import android.widget.BaseAdapter
import com.hyphenate.chat.EMMessage
import com.hyphenate.easeui.ui.EaseChatFragment
import com.hyphenate.easeui.widget.chatrow.EaseChatRow
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter

class ChatFragment : EaseChatFragment(), EaseChatFragment.EaseChatFragmentHelper {

    override fun setUpView() {
        super.setUpView()
        setChatFragmentHelper(this)
    }

    override fun onAvatarClick(username: String?) {

    }

    override fun onMessageBubbleClick(message: EMMessage?): Boolean {
//消息框点击事件，demo这里不做覆盖，如需覆盖，return true

        return false
    }

    override fun onAvatarLongClick(username: String?) {

    }

    override fun onMessageBubbleLongClick(message: EMMessage?) {

    }

    override fun onSetCustomChatRowProvider(): EaseCustomChatRowProvider {
        return CustomChatRowProvider()
    }

    override fun onSetMessageAttributes(message: EMMessage?) {
        message!!.setAttribute("HeadImg","http://jjw-personnel.oss-cn-shenzhen.aliyuncs.com/Image/Personnel/20191115/2WZhplWsZaMEZWYZctSQgo.png")
    }

    override fun onExtendMenuItemClick(itemId: Int, view: View?): Boolean {
        return false
    }

    override fun onEnterToChatDetails() {

    }


    class CustomChatRowProvider : EaseCustomChatRowProvider {
        override fun getCustomChatRowType(message: EMMessage?): Int {
            return 0
        }

        override fun getCustomChatRowTypeCount(): Int {
            return 14
        }

        override fun getCustomChatRow(
            message: EMMessage?,
            position: Int,
            adapter: BaseAdapter?
        ): EaseChatRowPresenter {
            return CustomerPresenter()
        }

    }

    class CustomerPresenter : EaseChatRowPresenter() {
        override fun onCreateChatRow(
            cxt: Context?,
            message: EMMessage?,
            position: Int,
            adapter: BaseAdapter?
        ): EaseChatRow {
            TODO("Not yet implemented")
        }

    }
}