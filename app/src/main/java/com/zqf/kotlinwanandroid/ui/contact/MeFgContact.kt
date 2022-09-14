package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.entity.PersonInfoEntity

/**
 * Author: zqf
 * Date: 2021/10/09
 */
interface MeFgContact {

    interface MeFgView : IBaseView {
        fun meRecycleData(meRecycleEntity: MutableList<MeRecycleEntity>)
        fun outsuc()
        fun personInfo(info: PersonInfoEntity)
    }

    interface Presenter {
        fun getMeRecycleData()
        fun outLogin()
        fun getPersonInfo()
    }
}