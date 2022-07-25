package com.zqf.kotlinwanandroid.ui.contact

import android.content.Context
import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity

/**
 * Author: zqf
 * Date: 2021/10/08
 */
interface SysSetContact {

    interface SysSetView : IBaseView {
        fun meRecycleData(meRecycleEntity: MutableList<String>)
    }

    interface Presenter {

    }
}