package com.zqf.kotlinwanandroid.ui.act

import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.AboutLayoutBinding
import com.zqf.kotlinwanandroid.ui.contact.AboutContact
import com.zqf.kotlinwanandroid.ui.presenter.AboutActPresenter

/**
 * Author: zqf
 * Date: 2022/02/10
 */
class AboutActivity : BaseAct<AboutLayoutBinding, AboutActPresenter>(), AboutContact.AboutView {
    override fun getLayout(): Int {
        return R.layout.about_layout
    }

    override fun getPresenter(): AboutActPresenter {
        return AboutActPresenter(this)
    }

    override fun initV() {
        mTitleBar.setCentreTitle("关于我们")
    }

    override fun hasTitleBar(): Boolean {
        return true
    }
}