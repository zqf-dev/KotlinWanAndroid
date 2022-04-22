package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.QaListEntity

/**
 * Author: zqf
 * Date: 2021/10/09
 */
interface QaFgContact {

    interface QaFgView : IBaseView {
        fun getQaListSuccess(type: String, qaListEntity: MutableList<QaListEntity>)
    }

    interface Presenter {
        fun getQaListData(type: String)
    }
}