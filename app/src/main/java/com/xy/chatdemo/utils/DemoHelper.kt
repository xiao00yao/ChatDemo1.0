package com.xy.chatdemo.utils

import android.content.Context
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMMessage.ChatType
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.easeui.EaseUI
import com.hyphenate.easeui.EaseUI.EaseUserProfileProvider
import com.hyphenate.easeui.domain.EaseAvatarOptions
import com.hyphenate.easeui.domain.EaseUser
import com.hyphenate.easeui.model.EaseAtMessageHelper
import com.hyphenate.easeui.utils.EaseCommonUtils
import com.xy.chatdemo.R
import com.xy.chatdemo.base.Constant
import com.xy.mylibrary.utils.UserDbUtils
import java.util.*

/**
 * 帮助类
 */
object DemoHelper {
    private var appContext: Context? = null
    private lateinit var easeUI: EaseUI
    fun init(context: Context) {
        val chatOptions = initChatOptions() //初始化环信选项
        if (EaseUI.getInstance().init(context, chatOptions)) {
            appContext = context
            EMClient.getInstance().setDebugMode(true)//设置debug 模式
            easeUI = EaseUI.getInstance()
            //set user avatar to circle shape

            //set user avatar to circle shape
            val avatarOptions = EaseAvatarOptions()
            avatarOptions.avatarShape = 1
            easeUI.setAvatarOptions(avatarOptions)//设置头像样式

            // set profile provider if you want easeUI to handle avatar and nickname
            easeUI.setUserProfileProvider(EaseUserProfileProvider { username -> getUserInfo(username) })
        }
        registerMessageListener()//注册监听
    }

    private fun registerMessageListener() {
        var mEMMessageListener =  object : EMMessageListener{
            override fun onMessageRecalled(messages: MutableList<EMMessage>) {
                for (msg in messages) {
                    if (msg.chatType == ChatType.GroupChat && EaseAtMessageHelper.get()
                            .isAtMeMsg(msg)
                    ) {
                        EaseAtMessageHelper.get().removeAtMeGroup(msg.to)
                    }
                    val msgNotification =
                        EMMessage.createReceiveMessage(EMMessage.Type.TXT)
                    val txtBody = EMTextMessageBody(
                        String.format(
                            appContext!!.getString(R.string.msg_recall_by_user),
                            msg.from
                        )
                    )
                    msgNotification.addBody(txtBody)
                    msgNotification.from = msg.from
                    msgNotification.to = msg.to
                    msgNotification.isUnread = false
                    msgNotification.msgTime = msg.msgTime
                    msgNotification.setLocalTime(msg.msgTime)
                    msgNotification.chatType = msg.chatType
                    msgNotification.setAttribute(Constant.MESSAGE_TYPE_RECALL, true)
                    msgNotification.setStatus(EMMessage.Status.SUCCESS)
                    EMClient.getInstance().chatManager().saveMessage(msgNotification)
                }
            }

            override fun onMessageChanged(p0: EMMessage?, p1: Any?) {

            }

            override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {

            }

            override fun onMessageReceived(p0: MutableList<EMMessage>?) {

            }

            override fun onMessageDelivered(p0: MutableList<EMMessage>?) {

            }

            override fun onMessageRead(p0: MutableList<EMMessage>?) {

            }

        }

        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListener)
    }

    /***
     * 初始化环信选项
     */
    fun initChatOptions(): EMOptions {
        var mEMOptions = EMOptions()
// set if accept the invitation automatically
        mEMOptions.acceptInvitationAlways = false
        // set if you need read ack
        mEMOptions.requireAck = true
        // set if you need delivery ack
        mEMOptions.requireDeliveryAck = false
        return mEMOptions
    }

    private fun getUserInfo(username: String): EaseUser? {
        // To get instance of EaseUser, here we get it from the user list in memory
        // You'd better cache it if you get it from your server
        var user: EaseUser? = null
        if (username == EMClient.getInstance().currentUser) return getCurrentUserInfo()
        user = getContactList().get(username)
//        if (user == null && getRobotList() != null) { //远程用户
//            user = getRobotList().get(username)
//        }

        // if user is not in your contacts, set inital letter for him/her
        if (user == null) {
            user = EaseUser(username)
            EaseCommonUtils.setUserInitialLetter(user)
        }
        return user
    }

    private var currentUser: EaseUser? = null
    private var contactList: Map<String, EaseUser> = mutableMapOf()
    /**
     * 获取当前用户信息
     */
    @Synchronized
    fun getCurrentUserInfo(): EaseUser? {
        if (currentUser == null) {
            val username = EMClient.getInstance().currentUser
            currentUser = EaseUser(username)
            val nick: String = UserProfileManager.getCurrentUserNick() //绰号、昵称
            currentUser!!.setNickname(nick ?: username)
            currentUser!!.setAvatar(UserProfileManager.getCurrentUserAvatar())//头像
        }
        return currentUser
    }


    /**
     * get contact list
     *获取联系人列表
     * @return
     */
    fun getContactList(): Map<String, EaseUser> {
        if (isLoggedIn() && contactList == null) {
            contactList = UserDbUtils.getIntance().selectAlluser()
        }

        // return a empty non-null object to avoid app crash
        return if (contactList == null) {
            Hashtable()
        } else {
            contactList
        }

    }


    /**
     * if ever logged in
     *
     * @return
     */
    fun isLoggedIn(): Boolean {
        return EMClient.getInstance().isLoggedInBefore
    }


}