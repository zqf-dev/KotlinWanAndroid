package com.zqf.kotlinwanandroid.ui.presenter

import android.util.Log
import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.entity.Article
import com.zqf.kotlinwanandroid.entity.CollectEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.http.PageList
import com.zqf.kotlinwanandroid.ui.contact.CollectListContact
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toFlowResponse
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2022/09/23
 */
class CollectListActPresent(v: CollectListContact.ICollectListView) :
    BasePresenter<CollectListContact.ICollectListView>(), CollectListContact.Presenter {
    private var page = 0

    init {
        attachView(v)
    }

    override fun onDestroy() {}

    override fun getCollectListData(t: String) {
        if (t == AppConstant.refresh) page = 0
        mCoroutineScope.launch {
            RxHttp.get(API.collectList(page)).toFlowResponse<PageList<CollectEntity>>()
                .catch {
                    ToastUtils.show(it.message)
                }
                .collect {
                    if (!it.over) page = it.curPage
                    getView()!!.collectModel(t, it.datas)
                }
        }
    }
}