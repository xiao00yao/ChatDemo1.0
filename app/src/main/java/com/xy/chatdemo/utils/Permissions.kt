package com.xy.chatdemo.utils

import android.app.Activity
import com.luck.picture.lib.permissions.RxPermissions
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

object Permissions {
    fun requestPermisssion(activity: Activity, onNextBase: OnNextBase, vararg permissions:String){
        val rxPermissions = RxPermissions(activity)
        rxPermissions.request(*permissions).subscribe(object :BaseObserver<Boolean>(){
            override fun onNext(t: Boolean) {
                onNextBase.OnNextBase(t)
            }
        })
    }

    interface OnNextBase {
        fun OnNextBase(aBoolean: Boolean)
    }

    open class BaseObserver<T>: Observer<T>{
        override fun onComplete() {

        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: T) {

        }

        override fun onError(e: Throwable) {

        }

    }
}