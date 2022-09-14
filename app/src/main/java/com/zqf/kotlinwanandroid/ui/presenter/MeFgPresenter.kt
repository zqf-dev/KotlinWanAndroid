package com.zqf.kotlinwanandroid.ui.presenter

import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.ui.contact.HomeFgContact
import com.zqf.kotlinwanandroid.ui.contact.MeFgContact
import com.zqf.kotlinwanandroid.ui.contact.SystemFgContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.await
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class MeFgPresenter(v: MeFgContact.MeFgView) : BasePresenter<MeFgContact.MeFgView>(),
    MeFgContact.Presenter {

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }

    override fun getMeRecycleData() {
        mCoroutineScope.launch {
            var meList: MutableList<MeRecycleEntity> = mutableListOf()
            meList.add(MeRecycleEntity(R.mipmap.score, "我的积分"))
            meList.add(MeRecycleEntity(R.mipmap.collection, "我的收藏"))
            meList.add(MeRecycleEntity(R.mipmap.share, "我的分享"))
            meList.add(MeRecycleEntity(R.mipmap.later_read, "稍后阅读"))
            meList.add(MeRecycleEntity(R.mipmap.read_record, "阅读历史"))
            meList.add(MeRecycleEntity(R.mipmap.system, "系统设置"))
            meList.add(MeRecycleEntity(R.mipmap.about, "关于我们"))
            meList.add(MeRecycleEntity(R.mipmap.logout, "退出登录"))
            getView()!!.meRecycleData(meList)
        }
    }

    override fun outLogin() {
        mCoroutineScope.launch {
            RxHttp.get(API.outLogin)
                .toResponse<String>()
                .awaitResult {
                    getView()!!.outsuc()
                }.onFailure {
                    ToastUtils.show(it.message)
                }
        }
    }
}