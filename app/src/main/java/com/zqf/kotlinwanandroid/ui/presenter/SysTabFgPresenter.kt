package com.zqf.kotlinwanandroid.ui.presenter

import android.util.Log
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.entity.SysTabNavEntity
import com.zqf.kotlinwanandroid.entity.SysTabTreeEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.ui.contact.SysTabFgContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2021/12/15
 */
class SysTabFgPresenter(v: SysTabFgContact.SysTabFgView) :
    BasePresenter<SysTabFgContact.SysTabFgView>(), SysTabFgContact.Presenter {

    override fun onDestroy() {

    }

    init {
        attachView(v)
    }

    override fun getSysData() {
        mCoroutineScope.launch {
            RxHttp.get(API.TREEJSON).toResponse<MutableList<SysTabTreeEntity>>()
                .awaitResult {
                    Log.e("TAG", "tree---$it")
                    getView()!!.treeJsonResult(it)
                }
        }
    }

    override fun getNavData() {
        mCoroutineScope.launch {
            RxHttp.get(API.NAVJSON).toResponse<MutableList<SysTabNavEntity>>()
                .awaitResult {
                    Log.e("TAG", "nav---$it")
                    getView()!!.navJsonResult(it)
                }
        }
    }
}