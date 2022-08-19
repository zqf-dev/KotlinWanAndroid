package com.zqf.kotlinwanandroid.ui.presenter

import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.entity.SysSettingEntity
import com.zqf.kotlinwanandroid.ui.contact.SysSetContact
import kotlinx.coroutines.launch

/**
 * Author: zqf
 * Date: 2021/10/08
 * 启动页的presenter
 */
class SysSetActPresenter(v: SysSetContact.SysSetView) : BasePresenter<SysSetContact.SysSetView>(),
    SysSetContact.Presenter {

    init {
        attachView(v)
    }

    override fun onDestroy() {

    }

    override fun getSysInitData() {
        mCoroutineScope.launch {
            val meList: MutableList<SysSettingEntity> = mutableListOf()
            meList.add(SysSettingEntity("无图模式", "开启后在非WIFI模式下不加载图片", 0, false))
            meList.add(SysSettingEntity("显示置顶文章", "关闭后不显示首页置顶文章", 0, true))
            meList.add(SysSettingEntity("显示轮播", "关闭后不显示首页顶部轮播图", 0, true))
            meList.add(SysSettingEntity("显示角标", "关闭后不显示底部导航的角标", 0, false))
            meList.add(SysSettingEntity("设置首页", "设置进入App默认展示的页面", 1, "首页"))
            meList.add(SysSettingEntity("列表动画", "设置列表页面加载数据的动画", 1, "无"))
            meList.add(SysSettingEntity("清除缓存", "清除App缓存数据", 1, ""))
            meList.add(SysSettingEntity("关于我们", "了解App相关信息", 1, ""))
            getView()!!.meRecycleData(meList)
        }
    }
}