package com.zqf.kotlinwanandroid.ui.presenter

import android.app.Activity
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.entity.HotKeyEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.ui.act.SearchDetailsActivity
import com.zqf.kotlinwanandroid.ui.contact.SearchContact
import com.zqf.kotlinwanandroid.util.ActRouter
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2022/01/17
 */
class SearchPresenter(v: SearchContact.SearchIView) : BasePresenter<SearchContact.SearchIView>(),
    SearchContact.Present {

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }

    override fun searchData(mContext: Activity, string: String) {
        val hashMap = HashMap<String, String>().apply {
            put("k", string)
        }
        ActRouter.ofActParams(mContext, SearchDetailsActivity().javaClass, hashMap)
    }

    override fun hotSearchData() {
        mCoroutineScope.launch {
            RxHttp.get(API.HOTKEY).toResponse<MutableList<HotKeyEntity>>()
                .awaitResult {
                    getView()!!.getHotKeyData(it)
                }
        }
    }
}