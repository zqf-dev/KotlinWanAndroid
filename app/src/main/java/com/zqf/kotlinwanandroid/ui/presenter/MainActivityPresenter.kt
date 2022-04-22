package com.zqf.kotlinwanandroid.ui.presenter

import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.ui.contact.MainContact

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class MainActivityPresenter(v: MainContact.MainView) : BasePresenter<MainContact.MainView>(),
    MainContact.Presenter {

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }
}