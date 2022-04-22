package com.zqf.kotlinwanandroid.ui.presenter

import android.util.Log
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.entity.Article
import com.zqf.kotlinwanandroid.entity.BannerEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.http.PageList
import com.zqf.kotlinwanandroid.ui.contact.HomeFgContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class HomeFgPresenter(v: HomeFgContact.HomeFgView) : BasePresenter<HomeFgContact.HomeFgView>(),
    HomeFgContact.Presenter {
    var page = 0

    init {
        attachView(v)
    }

    override fun getHomeBanner() {
        mCoroutineScope.launch {
            RxHttp.get(API.bannerUrl).toResponse<List<BannerEntity>>()
                .awaitResult {
                    getView()!!.bannerModel(it)
                }
        }
    }

    override fun getHomeArticle(type: String) {
        if (type == AppConstant.refresh) page = 0
        mCoroutineScope.launch {
            RxHttp.get(API.articleUrl(page)).toResponse<PageList<Article>>()
                .awaitResult {
                    if (!it.over) page = it.curPage
                    getView()!!.articleModel(type, it.datas)
                }.onFailure {
                    Log.e("TAG", "it--" + it.message)
                }
        }
    }

    override fun getHomeTopArticle() {
        mCoroutineScope.launch {
            RxHttp.get(API.ARTICLETOPURL).toResponse<List<Article>>()
                .awaitResult {
                    getView()!!.articletopData(it)
                }.onFailure {
                    Log.e("TAG", "it--" + it.message)
                }
        }
    }


    override fun onDestroy() {

    }
}