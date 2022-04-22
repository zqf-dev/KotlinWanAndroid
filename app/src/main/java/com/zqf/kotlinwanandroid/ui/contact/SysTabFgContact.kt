package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.SysTabNavEntity
import com.zqf.kotlinwanandroid.entity.SysTabTreeEntity

/**
 * Author: zqf
 * Date: 2021/12/15
 */
interface SysTabFgContact {

    interface SysTabFgView : IBaseView {

        fun treeJsonResult(list: MutableList<SysTabTreeEntity>)

        fun navJsonResult(list: MutableList<SysTabNavEntity>)
    }

    interface Presenter {

        fun getSysData()

        fun getNavData()

    }
}