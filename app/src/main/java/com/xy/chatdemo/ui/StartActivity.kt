package com.xy.chatdemo.ui

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.hyphenate.EMCallBack
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.xy.chatdemo.R
import com.xy.chatdemo.base.Constant
import com.xy.chatdemo.utils.Permissions
import kotlinx.android.synthetic.main.login_activity.*

/**
 * 登录界面
 */
class StartActivity : AppCompatActivity() {
    lateinit var mDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        EMClient.getInstance().logout(false, null)
        Permissions.requestPermisssion(
            this, object : Permissions.OnNextBase {
                override fun OnNextBase(aBoolean: Boolean) {
                    if (aBoolean){
                        ToastUtils.showShort("打开了相应的权限")
                    }else{
                        ToastUtils.showShort("请打开相应的权限")
                    }
                }

            }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        var strname = SPUtils.getInstance().getString(Constant.USERNAME)
        var strpassword = SPUtils.getInstance().getString(Constant.PASSWORD)

        if (SPUtils.getInstance().getBoolean(Constant.ISLOGIN,false)){
            if (strname.isNotEmpty()&&strpassword.isNotEmpty()){
                ll_view.visibility = View.GONE
                login(strname,strpassword)
            }else{
                username.setText(strname)
                password.setText(strpassword)
                ll_view.visibility = View.VISIBLE
            }
        }else{
            ll_view.visibility = View.VISIBLE
        }
    }


    fun login(username:String,password:String){
        EMClient.getInstance()
            .login(username, password, object : EMCallBack {
                override fun onSuccess() {
                    ToastUtils.showShort("登录成功")
                    SPUtils.getInstance().put(Constant.USERNAME, username)
                    SPUtils.getInstance().put(Constant.PASSWORD, password)
                    SPUtils.getInstance().put(Constant.ISLOGIN,true)
                    EMClient.getInstance().groupManager().loadAllGroups()
                    EMClient.getInstance().chatManager().loadAllConversations()
                    startActivityForResult(Intent(this@StartActivity, MainActivity::class.java),200)
                    finish()
                }

                override fun onProgress(p0: Int, p1: String?) {

                }

                override fun onError(p0: Int, msg: String?) {
                    ToastUtils.showShort(msg)
                }

            })
    }

    fun action(view: View) {
        when (view.id) {
            //注册
            R.id.regist -> {
                Thread(Runnable {
                    try {
                        EMClient.getInstance()
                            .createAccount(username.text.toString(), password.text.toString())//注册方法
                        SPUtils.getInstance().put(Constant.USERNAME, username.text.toString())
                    } catch (e: HyphenateException) {
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
            R.id.login -> {
                mDialog = ProgressDialog(this)
                mDialog.setMessage("登录中》》》")
                if (username.text.toString().isEmpty()) {
                    ToastUtils.showShort("用户名不能为空")
                    return
                }
                if (password.text.toString().isEmpty()) {
                    ToastUtils.showShort("密码不为空")
                    return
                }
                mDialog.show()

                login(username.text.toString(),password.text.toString())
                mDialog.hide()

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==200){
            if (resultCode==Activity.RESULT_OK){
                ll_view.visibility = View.VISIBLE
                var strname = SPUtils.getInstance().getString(Constant.USERNAME)
                var strpassword = SPUtils.getInstance().getString(Constant.PASSWORD)
                username.setText(strname)
                password.setText(strpassword)
            }
        }
    }
}