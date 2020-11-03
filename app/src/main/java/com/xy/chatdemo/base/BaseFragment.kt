package com.xy.chatdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xy.chatdemo.R
import kotlinx.android.synthetic.main.basefragment.*

abstract class BaseFragment: Fragment() {
    lateinit var baseFragment: BaseFragment
    lateinit var mRootView:ViewGroup
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
        mRootView = rootView.findViewById(R.id.rootView)
        setContentLayout(setlayout())
        return rootView
    }

    private fun setContentLayout(viewId: Int){
        var view = LayoutInflater.from(activity).inflate(viewId,null)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        view.layoutParams = layoutParams
        mRootView.addView(view,layoutParams)
    }

    abstract fun setlayout():Int

    abstract fun initVariables(view: View, savedInstanceState: Bundle?)
}