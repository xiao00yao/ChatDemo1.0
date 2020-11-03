package com.xy.chatdemo.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.xy.chatdemo.base.BaseFragment
import com.xy.chatdemo.R
import com.xy.chatdemo.base.Constant
import kotlinx.android.synthetic.main.setting_fragment.*

class SettingFragment: BaseFragment() {
    override fun setlayout(): Int {
        return R.layout.setting_fragment
    }

    override fun initVariables(view: View, savedInstanceState: Bundle?) {
        loginOut.setOnClickListener {
            EMClient.getInstance().logout(false,object :EMCallBack{
                override fun onSuccess() {
//                    activity!!.setResult(Activity.RESULT_OK)
                    SPUtils.getInstance().put(Constant.PASSWORD,"")
                    startActivity(Intent(activity,StartActivity::class.java))
                    activity!!.finish()
                }

                override fun onProgress(p0: Int, p1: String?) {

                }

                override fun onError(p0: Int, p1: String?) {

                }

            })
        }
    }
}