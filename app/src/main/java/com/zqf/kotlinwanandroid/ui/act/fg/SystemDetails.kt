package com.zqf.kotlinwanandroid.ui.act.fg

import android.util.Log
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.OfficialfgLayoutBinding
import com.zqf.kotlinwanandroid.entity.SysTabTreeEntity
import com.zqf.kotlinwanandroid.ui.contact.SysDetailsContact
import com.zqf.kotlinwanandroid.ui.presenter.SystemDetailsPresenter

/**
 * Author: zqf
 * Date: 2021/12/31
 */
class SystemDetails : BaseAct<OfficialfgLayoutBinding, SystemDetailsPresenter>(),
    SysDetailsContact.SysDetailsView {
    override fun getLayout(): Int {
        return R.layout.officialfg_layout
    }

    override fun getPresenter(): SystemDetailsPresenter {
        return SystemDetailsPresenter(this)
    }

    override fun initV() {
        val bean = intent.getParcelableExtra<SysTabTreeEntity>("data")
        Log.e("TAG", "---" + bean?.name)
    }

    override fun hasTitleBar(): Boolean {
        return true
    }
}