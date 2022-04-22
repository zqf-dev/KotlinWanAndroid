package com.zqf.kotlinwanandroid.ui.presenter

import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.ui.contact.SysDetailsContact

/**
 * Author: zqf
 * Date: 2021/12/31
 */
class SystemDetailsPresenter(v: SysDetailsContact.SysDetailsView) :
    BasePresenter<SysDetailsContact.SysDetailsView>() {
    override fun onDestroy() {

    }

    init {
        attachView(v)
    }
}