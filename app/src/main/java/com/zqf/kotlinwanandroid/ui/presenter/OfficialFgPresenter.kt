package com.zqf.kotlinwanandroid.ui.presenter

import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.entity.ChaptersEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.ui.contact.HomeFgContact
import com.zqf.kotlinwanandroid.ui.contact.OfficialFgContact
import com.zqf.kotlinwanandroid.ui.contact.SystemFgContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class OfficialFgPresenter(v: OfficialFgContact.OfficialFgView) :
    BasePresenter<OfficialFgContact.OfficialFgView>(), OfficialFgContact.Presenter {
    override fun onDestroy() {

    }

    init {
        attachView(v)
    }

    override fun getChaptersTitle() {
        mCoroutineScope.launch {
            RxHttp.get(API.WXARTICLE).toResponse<MutableList<ChaptersEntity>>()
                .awaitResult {
                    getView()!!.getChaptersData(it)
                }
        }
    }
}