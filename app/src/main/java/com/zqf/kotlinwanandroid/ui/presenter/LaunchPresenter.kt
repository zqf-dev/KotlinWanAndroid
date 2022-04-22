package com.zqf.kotlinwanandroid.ui.presenter

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.ui.act.MainActivity
import com.zqf.kotlinwanandroid.ui.contact.LaunchContact
import com.zqf.kotlinwanandroid.util.ActRouter

/**
 * Author: zqf
 * Date: 2021/10/08
 * 启动页的presenter
 */
class LaunchPresenter(v: LaunchContact.LaunchView) : BasePresenter<LaunchContact.LaunchView>(),
    LaunchContact.Presenter {

    private var handler: Handler? = Handler(Looper.getMainLooper())

    init {
        attachView(v)
    }

    override fun handlerDelayed(activity: Activity) {
        handler?.postDelayed(run {
            {
                ActRouter.ofAct(activity, MainActivity().javaClass)
                activity.finish()
            }
        }, 1200)
    }

    override fun onDestroy() {
        if (handler != null) {
            handler!!.removeCallbacksAndMessages(null)
            handler = null
        }
    }
}