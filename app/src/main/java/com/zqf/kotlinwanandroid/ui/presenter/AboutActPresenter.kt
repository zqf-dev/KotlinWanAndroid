package com.zqf.kotlinwanandroid.ui.presenter

import android.content.Context
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.ui.contact.AboutContact
import com.zqf.kotlinwanandroid.util.ActRouter
import kotlinx.coroutines.launch

/**
 * Author: zqf
 * Date: 2021/10/08
 * 启动页的presenter
 */
class AboutActPresenter(v: AboutContact.AboutView) : BasePresenter<AboutContact.AboutView>(),
    AboutContact.Presenter {

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }

    override fun getAboutRecycleData() {
        mCoroutineScope.launch {
            val meList: MutableList<MeRecycleEntity> = mutableListOf()
            meList.add(MeRecycleEntity(R.mipmap.about, "玩Android官网"))
            meList.add(MeRecycleEntity(R.mipmap.later_read, "App源码"))
            getView()!!.meRecycleData(meList)
        }
    }

    override fun webRout(context: Context, link: String) {
        ActRouter.ofWebViewX5Act(context, link, "")
    }
}