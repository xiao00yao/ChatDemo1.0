package com.xy.chatdemo.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.SPUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation.EMConversationType
import com.hyphenate.easeui.EaseConstant
import com.hyphenate.easeui.domain.EaseUser
import com.xy.chatdemo.R
import com.xy.chatdemo.base.Constant
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var mMessageFragment = MessageFrament()
    var mConstantFragment = ConstantFragment()
    var mSettingFragment = SettingFragment()
    var mFragments = mutableListOf<Fragment>()
    var currenIndex = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mFragments.add(mMessageFragment)
        mFragments.add(mConstantFragment)
        mFragments.add(mSettingFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        replaceFragment(0)
        mMessageFragment.setConversationListItemClickListener {
            val username: String = it.conversationId()
            if (username == EMClient.getInstance().currentUser) Toast.makeText(
                this,
                R.string.Cant_chat_with_yourself,
                Toast.LENGTH_SHORT
            ).show() else {
                // start chat acitivity
                val intent = Intent(this, ChatActivity::class.java)
                if (it.isGroup()) {
                    if (it.getType() == EMConversationType.ChatRoom) {
                        // it's group chat
                        intent.putExtra(
                            EaseConstant.EXTRA_CHAT_TYPE,
                            EaseConstant.CHATTYPE_CHATROOM
                        )
                    } else {
                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP)
                    }
                }
                // it's single chat
                intent.putExtra(EaseConstant.EXTRA_USER_ID, username)
                startActivity(intent)
            }
        }
        mConstantFragment.setContactListItemClickListener {
            startActivity(
                Intent(this, ChatActivity::class.java)
                    .putExtra(EaseConstant.EXTRA_USER_ID, it.username)
            )
        }

        setContactList()
    }


    fun setContactList() {

        Thread(object :Runnable{
            override fun run() {
                var userMap = mutableMapOf<String,EaseUser>()
                val users =
                    EMClient.getInstance().contactManager().allContactsFromServer
                users.forEach {
                    userMap.put(it, EaseUser(it))
                }
                mConstantFragment.setContactsMap(userMap)
            }

        }).start()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.messageFragment -> {
                replaceFragment(0)
            }

            R.id.constantFragment -> {
                replaceFragment(1)
            }

            R.id.settingFragment -> {
                replaceFragment(2)
            }
        }
        return true
    }

    fun replaceFragment(position: Int) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (position != currenIndex) {
            if (mFragments[position].isAdded) { //是否添加了fragment
                if (currenIndex != -1) {
                    beginTransaction.show(mFragments[position]).hide(mFragments[currenIndex])
                } else {
                    beginTransaction.show(mFragments[position])
                }

            } else {
                if (currenIndex != -1) {
                    beginTransaction.add(framelayout.id, mFragments[position])
                        .hide(mFragments[currenIndex])
                } else {
                    beginTransaction.add(framelayout.id, mFragments[position])
                }

            }
        }
        currenIndex = position
        beginTransaction.commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().logout(false)
    }
}