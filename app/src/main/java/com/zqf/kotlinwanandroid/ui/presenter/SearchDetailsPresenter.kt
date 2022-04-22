package com.zqf.kotlinwanandroid.ui.presenter

import android.util.Log
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.entity.Article
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.http.PageList
import com.zqf.kotlinwanandroid.ui.contact.SearchDetailsContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2022/01/19
 */
class SearchDetailsPresenter(v: SearchDetailsContact.SearchDetailsView) :
    BasePresenter<SearchDetailsContact.SearchDetailsView>(), SearchDetailsContact.Presenter {
    var page = 0

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }

    override fun searchQuery(k: String, type: String) {
        if (type == AppConstant.refresh) page = 0
        mCoroutineScope.launch {
            RxHttp.postForm(API.queryHotKey(page))
                .add("k", k).toResponse<PageList<Article>>()
                .awaitResult {
                    if (!it.over) page = it.curPage
                    getView()!!.querySuccess(type, it.datas)
                }
        }
    }
}