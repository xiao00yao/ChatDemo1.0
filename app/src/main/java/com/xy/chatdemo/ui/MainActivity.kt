package com.xy.chatdemo.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xy.chatdemo.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity:AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {
    var mMessageFragment = MessageFrament()
    var mConstantFragment = ConstantFragment()
    var mSettingFragment = SettingFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        mMessageFragment.setConversationListItemClickListener {

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.messageFragment->{

            }

            R.id.constantFragment->{

            }

            R.id.settingFragment->{

            }
        }
        return true
    }
}