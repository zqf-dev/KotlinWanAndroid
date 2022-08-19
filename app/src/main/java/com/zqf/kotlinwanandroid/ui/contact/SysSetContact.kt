package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.SysSettingEntity

/**
 * Author: zqf
 * Date: 2021/10/08
 */
interface SysSetContact {

    interface SysSetView : IBaseView {
        fun meRecycleData(meRecycleEntity: MutableList<SysSettingEntity>)
    }

    interface Presenter {
        fun getSysInitData()
    }
}