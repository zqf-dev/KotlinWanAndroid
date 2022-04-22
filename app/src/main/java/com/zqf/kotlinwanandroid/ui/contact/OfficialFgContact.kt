package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.ChaptersEntity

/**
 * Author: zqf
 * Date: 2021/10/09
 */
interface OfficialFgContact {

    interface OfficialFgView : IBaseView {
        fun getChaptersData(entity: MutableList<ChaptersEntity>)
    }

    interface Presenter {
        fun getChaptersTitle()
    }
}