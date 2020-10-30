package com.xy.chatdemo

import android.app.ActionBar
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.basefragment.*

abstract class BaseFragment: Fragment() {
    lateinit var baseFragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseFragment = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables(view, savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.basefragment,container,false)
        setContentLayout(setlayout())
        return rootView
    }

    private fun setContentLayout(viewId: Int){
        var view = LayoutInflater.from(activity).inflate(viewId,null)
        val layoutParams = view.layoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        view.layoutParams = layoutParams
        rootView.addView(view,layoutParams)
    }

    abstract fun setlayout():Int

    abstract fun initVariables(view: View, savedInstanceState: Bundle?)
}