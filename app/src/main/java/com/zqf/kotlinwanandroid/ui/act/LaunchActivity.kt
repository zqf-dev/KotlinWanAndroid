package com.zqf.kotlinwanandroid.ui.act

import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.LaunchLayoutBinding
import com.zqf.kotlinwanandroid.ui.contact.LaunchContact
import com.zqf.kotlinwanandroid.ui.presenter.LaunchPresenter

/**
 * Author: zqf
 * Date: 2021/10/08
 * 启动页
 */
class LaunchActivity : BaseAct<LaunchLayoutBinding, LaunchPresenter>(), LaunchContact.LaunchView {

    override fun getLayout(): Int {
        return R.layout.launch_layout
    }

    override fun getPresenter(): LaunchPresenter {
        return LaunchPresenter(this)
    }

    override fun initV() {
        mPresenter.handlerDelayed(mContext)
    }

    override fun hasTitleBar(): Boolean {
        return false
    }
}