package com.zqf.kotlinwanandroid.ui.contact

import android.content.Context
import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity

/**
 * Author: zqf
 * Date: 2021/10/08
 */
interface AboutContact {

    interface AboutView : IBaseView {
        fun meRecycleData(meRecycleEntity: MutableList<MeRecycleEntity>)
    }

    interface Presenter {
        fun getAboutRecycleData()
        fun webRout(context: Context, link: String)
    }
}