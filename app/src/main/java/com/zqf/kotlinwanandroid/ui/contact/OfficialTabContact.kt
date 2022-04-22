package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.WxArticleEntity

/**
 * Author: zqf
 * Date: 2021/12/28
 */
interface OfficialTabContact {

    interface OfficialTabView : IBaseView {
        fun wxArticleModel(type: String, bean: MutableList<WxArticleEntity>)
    }

    interface Presenter {
        fun getOfficialList(id: Int, type: String)
    }
}