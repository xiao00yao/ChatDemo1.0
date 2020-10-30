package com.xy.chatdemo.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.hyphenate.EMCallBack
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.xy.chatdemo.R
import kotlinx.android.synthetic.main.login_activity.*

/**
 * 登录界面
 */
class StartActivity:AppCompatActivity() {
    lateinit var mDialog :ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        mDialog = ProgressDialog(this)
    }

    fun action(view: View) {
        when(view.id){
            //注册
            R.id.regist->{
                Thread(Runnable {
                    try {
                        EMClient.getInstance().createAccount(username.text.toString(),password.text.toString());//注册方法
                    }catch (e: HyphenateException){
                        val errorCode = e.errorCode
                        when (errorCode) {
                            EMError.NETWORK_ERROR -> {
                                ToastUtils.showShort(resources.getString(R.string.network_anomalies))
                            }
                            EMError.USER_ALREADY_EXIST -> {
                                ToastUtils.showShort(R.string.User_already_exists)
                            }
                            EMError.USER_AUTHENTICATION_FAILED -> {
                                ToastUtils.showShort("未经许可登记失败")
                            }
                            EMError.USER_ILLEGAL_ARGUMENT -> {
                                ToastUtils.showShort(R.string.illegal_user_name)
                            }
                            EMError.EXCEED_SERVICE_LIMIT -> {
                                ToastUtils.showShort("您的App用户注册数量已达上限,请升级至企业版")
                            }
                            else -> {
                                ToastUtils.showShort("注册失败")
                            }
                        }
                    }
                }).start()

            }

            //登录
            R.id.login->{
                mDialog.setMessage("登录中》》》")
                if (username.text.toString().isEmpty()){
                    ToastUtils.showShort("用户名不能为空")
                    return
                }
                if (password.text.toString().isEmpty()){
                    ToastUtils.showShort("密码不为空")
                    return
                }
                mDialog.show()

                EMClient.getInstance().login(username.text.toString(),password.text.toString(),object :EMCallBack{
                    override fun onSuccess() {
                        ToastUtils.showShort("登录成功")
                        startActivity(Intent(this@StartActivity,MainActivity::class.java))
                        mDialog.hide()
                    }

                    override fun onProgress(p0: Int, p1: String?) {

                    }

                    override fun onError(p0: Int, msg: String?) {
                        ToastUtils.showShort(msg)
                        mDialog.hide()
                    }

                })

            }
        }
    }
}