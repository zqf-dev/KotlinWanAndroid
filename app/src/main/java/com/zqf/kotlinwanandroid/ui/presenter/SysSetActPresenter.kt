package com.zqf.kotlinwanandroid.ui.presenter

import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.ui.contact.SysSetContact

/**
 * Author: zqf
 * Date: 2021/10/08
 * 启动页的presenter
 */
class SysSetActPresenter(v: SysSetContact.SysSetView) : BasePresenter<SysSetContact.SysSetView>(),
    SysSetContact.Presenter {

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }
}