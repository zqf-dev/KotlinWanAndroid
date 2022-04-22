package com.zqf.kotlinwanandroid.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import java.lang.ref.WeakReference

/**
 * Author: zqf
 * Date: 2021/10/08
 * 主管
 */
abstract class BasePresenter<V : IBaseView> : LifecycleObserver {

    private var mView: WeakReference<V>? = null

    var mCoroutineScope = CoroutineScope(Dispatchers.Main)

    open fun attachView(view: V) {
        mView = WeakReference<V>(view)
    }

    open fun getView(): V? {
        if (mView != null) {
            return mView!!.get()
        }
        return null
    }

    open fun isViewAttached(): Boolean {
        return mView != null && mView!!.get() != null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun detachView() {
        onDestroy()
        mView?.clear()
        mCoroutineScope.cancel()
    }

    abstract fun onDestroy()
}