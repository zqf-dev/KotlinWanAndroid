package com.zqf.kotlinwanandroid.ui.contact

import android.app.Activity
import com.zqf.kotlinwanandroid.base.IBaseView

/**
 * Author: zqf
 * Date: 2021/10/08
 */
interface LaunchContact {

    interface LaunchView : IBaseView {

    }

    interface Presenter {
        fun handlerDelayed(activity: Activity)
    }
}