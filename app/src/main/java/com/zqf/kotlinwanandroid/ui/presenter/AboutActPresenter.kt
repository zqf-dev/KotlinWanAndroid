package com.zqf.kotlinwanandroid.ui.presenter

import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.ui.contact.AboutContact

/**
 * Author: zqf
 * Date: 2021/10/08
 * 启动页的presenter
 */
class AboutActPresenter(v: AboutContact.AboutView) : BasePresenter<AboutContact.AboutView>(),
    AboutContact.Presenter {

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }
}