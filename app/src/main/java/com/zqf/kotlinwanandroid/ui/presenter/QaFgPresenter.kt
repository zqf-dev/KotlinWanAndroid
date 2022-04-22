package com.zqf.kotlinwanandroid.ui.presenter

import android.util.Log
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.entity.QaListEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.http.PageList
import com.zqf.kotlinwanandroid.ui.act.fg.QaFragment
import com.zqf.kotlinwanandroid.ui.contact.HomeFgContact
import com.zqf.kotlinwanandroid.ui.contact.MeFgContact
import com.zqf.kotlinwanandroid.ui.contact.QaFgContact
import com.zqf.kotlinwanandroid.ui.contact.SystemFgContact
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.RxHttpPlugins
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class QaFgPresenter(v: QaFgContact.QaFgView) : BasePresenter<QaFgContact.QaFgView>(),
    QaFgContact.Presenter {
    var page_size = 0

    init {
        attachView(v)
    }

    override fun onDestroy() {
        RxHttpPlugins.cancelAll(QaFragment::class.java.canonicalName)
    }

    override fun getQaListData(type: String) {
        if (type == AppConstant.refresh) page_size = 0
        mCoroutineScope.launch {
            RxHttp.get(API.wendaList(page_size))
                .tag(QaFragment::class.java.canonicalName)
                .toResponse<PageList<QaListEntity>>()
                .awaitResult {
                    if (!it.over) ++page_size
                    getView()!!.getQaListSuccess(type, it.datas)
                }.onFailure {
                    Log.e("TAG", it.stackTraceToString())
                }
        }
    }
}