package com.zqf.kotlinwanandroid.ui.contact

import android.app.Activity
import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.HotKeyEntity

/**
 * Author: zqf
 * Date: 2022/01/17
 */
interface SearchContact {

    interface SearchIView : IBaseView {
        fun getHotKeyData(hotKeyEntity: MutableList<HotKeyEntity>)
    }

    interface Present {
        fun searchData(mContext: Activity, string: String)
        fun hotSearchData()
    }
}