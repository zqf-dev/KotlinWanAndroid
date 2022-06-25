package com.zqf.kotlinwanandroid.ui.act.fg

import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.MefgLayoutBinding
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.ui.act.AboutActivity
import com.zqf.kotlinwanandroid.ui.adapter.MeAdapter
import com.zqf.kotlinwanandroid.ui.contact.MeFgContact
import com.zqf.kotlinwanandroid.ui.presenter.MeFgPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.mefg_layout.*

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class MeFragment : BaseFg<MefgLayoutBinding, MeFgPresenter>(), MeFgContact.MeFgView {
    private val meAdapter by lazy {
        MeAdapter(R.layout.me_recycle_item)
    }

    override fun getLayout(): Int {
        return R.layout.mefg_layout
    }

    override fun getPresenter(): MeFgPresenter {
        return MeFgPresenter(this)
    }

    override fun initV() {
        me_ll.layoutParams.height = AppConstant.contentHeight
        mPresenter.getMeRecycleData()
        me_recycle.layoutManager = RvUtil.vertical(mContext)
        me_recycle.adapter = meAdapter
        me_srf.setEnableLoadMore(false)
        me_srf.setOnRefreshListener {
            me_srf.finishRefresh()
        }
        meAdapter.setOnItemClickListener { adapter, view, position ->
            if (meAdapter.getItem(position).title == "关于我们") {
                ActRouter.ofAct(mContext, AboutActivity().javaClass)
            }
        }
    }

    override fun meRecycleData(meRecycleEntity: MutableList<MeRecycleEntity>) {
        meAdapter.setList(meRecycleEntity)
    }
}