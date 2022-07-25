package com.zqf.kotlinwanandroid.ui.act

import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.SyssetLayoutBinding
import com.zqf.kotlinwanandroid.ui.contact.SysSetContact
import com.zqf.kotlinwanandroid.ui.presenter.SysSetActPresenter

/**
 * Author: zqf
 * Date: 2022/07/25
 * 系统设置
 */
class SysSetActivity : BaseAct<SyssetLayoutBinding, SysSetActPresenter>(),
    SysSetContact.SysSetView {
    override fun getLayout(): Int {
        return R.layout.sysset_layout
    }

    override fun getPresenter(): SysSetActPresenter {
        return SysSetActPresenter(this)
    }

    override fun initV() {
        mTitleBar.setCentreTitle("系统设置")
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun meRecycleData(meRecycleEntity: MutableList<String>) {

    }
}