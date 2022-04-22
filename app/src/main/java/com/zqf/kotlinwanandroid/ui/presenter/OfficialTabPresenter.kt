package com.zqf.kotlinwanandroid.ui.presenter

import android.util.Log
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.entity.WxArticleEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.http.PageList
import com.zqf.kotlinwanandroid.ui.contact.OfficialTabContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class OfficialTabPresenter(v: OfficialTabContact.OfficialTabView) :
    BasePresenter<OfficialTabContact.OfficialTabView>(), OfficialTabContact.Presenter {
    var page = 0

    init {
        attachView(v)
    }

    override fun getOfficialList(id: Int, type: String) {
        if (type == AppConstant.refresh) page = 1
        mCoroutineScope.launch {
            RxHttp.get(API.wxArticleList(id, page))
                .toResponse<PageList<WxArticleEntity>>()
                .awaitResult {
                    if (!it.over) page = ++it.curPage
                    getView()!!.wxArticleModel(type, it.datas)
                }.onFailure {
                    Log.e("TAG", "it--" + it.message)
                }
        }
    }

    override fun onDestroy() {

    }
}