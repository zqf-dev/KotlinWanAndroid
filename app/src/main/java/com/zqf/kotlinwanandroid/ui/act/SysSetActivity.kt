package com.zqf.kotlinwanandroid.ui.act

import android.util.Log
import android.widget.Switch
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.SyssetLayoutBinding
import com.zqf.kotlinwanandroid.entity.SysSettingEntity
import com.zqf.kotlinwanandroid.ui.adapter.SysSettingAdapter
import com.zqf.kotlinwanandroid.ui.contact.SysSetContact
import com.zqf.kotlinwanandroid.ui.presenter.SysSetActPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.mefg_layout.*
import kotlinx.android.synthetic.main.sysset_layout.*

/**
 * Author: zqf
 * Date: 2022/07/25
 * 系统设置Activity kotlin
 */
class SysSetActivity : BaseAct<SyssetLayoutBinding, SysSetActPresenter>(),
    SysSetContact.SysSetView {

    private val mAdapter by lazy {
        SysSettingAdapter()
    }

    override fun getLayout(): Int {
        return R.layout.sysset_layout
    }

    override fun getPresenter(): SysSetActPresenter {
        return SysSetActPresenter(this)
    }

    override fun initV() {
        mTitleBar.setCentreTitle("系统设置")
        mPresenter.getSysInitData()
        setting_recycle.layoutManager = RvUtil.vertical(mContext)
        setting_recycle.adapter = mAdapter
        mAdapter.setOnItemClickListener { adapter, view, position ->
            when (mAdapter.getItem(position).title) {
                "关于我们" -> ActRouter.ofAct(mContext, AboutActivity().javaClass)
            }
        }
        mAdapter.addChildClickViewIds(R.id.sys_item_switch)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->

        }
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun meRecycleData(meRecycleEntity: MutableList<SysSettingEntity>) {
        mAdapter.setList(meRecycleEntity)
    }
}