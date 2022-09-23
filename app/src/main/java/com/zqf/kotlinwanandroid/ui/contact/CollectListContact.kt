package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.Article
import com.zqf.kotlinwanandroid.entity.CollectEntity

/**
 * Author: zqf
 * Date: 2022/09/23
 */
interface CollectListContact {

    interface ICollectListView : IBaseView {
        fun collectModel(type: String, bean: MutableList<CollectEntity>)
    }

    interface Presenter {
        fun getCollectListData(t: String)
    }
}