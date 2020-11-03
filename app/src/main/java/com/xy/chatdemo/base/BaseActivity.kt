package com.xy.chatdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.xy.chatdemo.R
import kotlinx.android.synthetic.main.base_activity.*

abstract class BaseActivity : AppCompatActivity() {
    lateinit var mRootView: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        mRootView = findViewById<ConstraintLayout>(R.id.rootView)
        setContantLayout(setLayout())
        initVariables(savedInstanceState)
    }

    private fun setContantLayout(viewId: Int) {
        var view = LayoutInflater.from(this).inflate(viewId, null)
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        mRootView.addView(view, layoutParams)
    }

    abstract fun setLayout(): Int

    abstract fun initVariables(savedInstanceState: Bundle?)
}