package com.zqf.kotlinwanandroid.ui.act.fg

import android.util.Log
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.QafgLayoutBinding
import com.zqf.kotlinwanandroid.entity.QaListEntity
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.interceptor.LoginNextInterceptor
import com.zqf.kotlinwanandroid.ui.adapter.QaAdapter
import com.zqf.kotlinwanandroid.ui.contact.QaFgContact
import com.zqf.kotlinwanandroid.ui.presenter.QaFgPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.qafg_layout.*

/**
 * Author: zqf
 * Date: 2021/10/09
 * 问答
 */
class QaFragment : BaseFg<QafgLayoutBinding, QaFgPresenter>(), QaFgContact.QaFgView,
    OnRefreshLoadMoreListener {

    private val qaAdapter by lazy {
        QaAdapter(R.layout.qa_recycle_item)
    }

    override fun getLayout(): Int {
        showLoading()
        return R.layout.qafg_layout
    }

    override fun getPresenter(): QaFgPresenter {
        return QaFgPresenter(this)
    }

    override fun initV() {
        qa_recycle.layoutManager = RvUtil.vertical(mContext)
        qa_recycle.adapter = qaAdapter
        qa_srf.setOnRefreshLoadMoreListener(this)
        mPresenter.getQaListData(AppConstant.refresh)
        qaAdapter.addChildClickViewIds(R.id.qa_rv_item_collect_iv)
        qaAdapter.setOnItemClickListener { adapter, view, position ->
            ActRouter.ofWebViewX5Act(
                mContext,
                qaAdapter.getItem(position).link,
                qaAdapter.getItem(position).title
            )
        }
        qaAdapter.setOnItemChildClickListener { adapter, view, position ->
            LoginInterceptChain.addInterceptor(LoginNextInterceptor {
                //登录成功的执行收藏
                //...
                Log.e("TAG", "收藏成功")
            }).process()
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter.getQaListData(AppConstant.refresh)
        qa_srf.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPresenter.getQaListData(AppConstant.loadMore)
        qa_srf.finishLoadMore()
    }

    override fun getQaListSuccess(type: String, qaListEntity: MutableList<QaListEntity>) {
        showContent()
        if (type == AppConstant.refresh) {
            qaAdapter.setList(qaListEntity)
        } else {
            qaAdapter.addData(qaListEntity)
        }
    }
}