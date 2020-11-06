package com.xy.chatdemo.utils

import com.blankj.utilcode.util.SPUtils
import com.xy.chatdemo.base.Constant

/**
 * 用户配置文件
 */
object UserProfileManager {
    /**
     * 获取用户昵称
     */
    fun getCurrentUserNick():String{
        return SPUtils.getInstance().getString(Constant.SHARED_KEY_CURRENTUSER_NICK,"")
    }

    /**
     * 获取头像
     */
    fun getCurrentUserAvatar():String{
        return SPUtils.getInstance().getString(Constant.SHARED_KEY_CURRENTUSER_AVATAR,"")
    }
}