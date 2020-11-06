package com.xy.chatdemo.base

import com.hyphenate.easeui.EaseConstant

object Constant : EaseConstant() {
    var USERNAME = "user_name" //存储登录名
    var PASSWORD = "password" //存储密码
    var ISLOGIN = "is_login"//是否登录过
    const val NEW_FRIENDS_USERNAME = "item_new_friends"
    const val GROUP_USERNAME = "item_groups"
    const val CHAT_ROOM = "item_chatroom"
    const val ACCOUNT_REMOVED = "account_removed"
    const val ACCOUNT_CONFLICT = "conflict"
    const val ACCOUNT_FORBIDDEN = "user_forbidden"
    const val ACCOUNT_KICKED_BY_CHANGE_PASSWORD = "kicked_by_change_password"
    const val ACCOUNT_KICKED_BY_OTHER_DEVICE = "kicked_by_another_device"
    const val CHAT_ROBOT = "item_robots"
    const val MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype"
    const val ACTION_GROUP_CHANAGED = "action_group_changed"
    const val ACTION_CONTACT_CHANAGED = "action_contact_changed"

    const val EXTRA_CONFERENCE_ID = "confId"
    const val EXTRA_CONFERENCE_PASS = "password"
    const val EXTRA_CONFERENCE_INVITER = "inviter"
    const val EXTRA_CONFERENCE_IS_CREATOR = "is_creator"
    const val EXTRA_CONFERENCE_GROUP_ID = "group_id"

    const val MSG_ATTR_CONF_ID = "conferenceId"
    const val MSG_ATTR_CONF_PASS =
        EXTRA_CONFERENCE_PASS
    const val MSG_ATTR_EXTENSION = "msg_extension"

    const val EM_CONFERENCE_OP = "em_conference_op"
    const val EM_CONFERENCE_ID = "em_conference_id"
    const val EM_CONFERENCE_PASSWORD = "em_conference_password"
    const val EM_CONFERENCE_TYPE = "em_conference_type"
    const val EM_MEMBER_NAME = "em_member_name"

    const val OP_INVITE = "invite"
    const val OP_REQUEST_TOBE_SPEAKER = "request_tobe_speaker"
    const val OP_REQUEST_TOBE_AUDIENCE = "request_tobe_audience"

    const val SHARED_KEY_CURRENTUSER_NICK = "SHARED_KEY_CURRENTUSER_NICK" //昵称
    const val SHARED_KEY_CURRENTUSER_AVATAR = "SHARED_KEY_CURRENTUSER_AVATAR"//头像
}