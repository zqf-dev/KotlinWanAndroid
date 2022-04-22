package com.zqf.kotlinwanandroid.ui.act.fg

import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.databinding.SystemfgLayoutBinding
import com.zqf.kotlinwanandroid.entity.SysTabNavEntity
import com.zqf.kotlinwanandroid.entity.SysTabTreeEntity
import com.zqf.kotlinwanandroid.ui.adapter.SysTabNavAdapter
import com.zqf.kotlinwanandroid.ui.adapter.SysTabTreeAdapter
import com.zqf.kotlinwanandroid.ui.contact.SysTabFgContact
import com.zqf.kotlinwanandroid.ui.presenter.SysTabFgPresenter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.systabfg_layout.*

/**
 * Author: zqf
 * Date: 2021/12/15
 */
class SysTabFragment(pos: Int) : BaseFg<SystemfgLayoutBinding, SysTabFgPresenter>(),
    SysTabFgContact.SysTabFgView {
    private val p = pos

    private val treeAdapter by lazy {
        SysTabTreeAdapter(R.layout.systabfg_recycle_item)
    }

    private val navAdapter by lazy {
        SysTabNavAdapter(R.layout.systabfg_recycle_item)
    }

    override fun getLayout(): Int {
        return R.layout.systabfg_layout
    }

    override fun getPresenter(): SysTabFgPresenter {
        return SysTabFgPresenter(this)
    }

    override fun initV() {
        systabrecycle.layoutManager = RvUtil.vertical(mContext)
        Log.e("TAG", "p---$p");
        if (p == 0) {
            systabrecycle.adapter = treeAdapter
            mPresenter.getSysData()
        } else if (p == 1) {
            systabrecycle.adapter = navAdapter
            mPresenter.getNavData()
        }
    }

    override fun treeJsonResult(list: MutableList<SysTabTreeEntity>) {
        treeAdapter.setList(list)
    }

    override fun navJsonResult(list: MutableList<SysTabNavEntity>) {
        navAdapter.setList(list)
    }
}