package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView

/**
 * Author: zqf
 * Date: 2022/09/01
 */
interface LoginContact {

    interface ILoginView : IBaseView {
        fun loginsuc()
        fun registsuc()
    }

    interface Presenter {
        //登录、注册
        fun loginServer(vT: Int, ac: String, psd: String, repsd: String)
    }
}