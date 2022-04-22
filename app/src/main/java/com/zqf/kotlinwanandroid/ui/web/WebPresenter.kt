package com.zqf.kotlinwanandroid.ui.web

import com.zqf.kotlinwanandroid.base.BasePresenter

/**
 * Author: zqf
 * Date: 2021/12/30
 */
class WebPresenter(v: WebContact.IWebView) : BasePresenter<WebContact.IWebView>(),
    WebContact.Presenter {
    override fun onDestroy() {

    }

    init {
        attachView(v)
    }
}