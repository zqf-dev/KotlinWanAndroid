package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.Article

/**
 * Author: zqf
 * Date: 2022/01/19
 */
interface SearchDetailsContact {

    interface SearchDetailsView : IBaseView {
        fun querySuccess(type: String, bean: MutableList<Article>)
    }

    interface Presenter {
        fun searchQuery(k: String, type: String)
    }
}