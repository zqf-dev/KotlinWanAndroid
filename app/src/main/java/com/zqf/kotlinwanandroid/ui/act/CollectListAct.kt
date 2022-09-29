package com.zqf.kotlinwanandroid.ui.act

import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.CollectListLayoutBinding
import com.zqf.kotlinwanandroid.entity.CollectEntity
import com.zqf.kotlinwanandroid.ui.adapter.CollectAdapter
import com.zqf.kotlinwanandroid.ui.contact.CollectListContact
import com.zqf.kotlinwanandroid.ui.presenter.CollectListActPresent
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.collect_list_layout.*

/**
 * Author: zqf
 * Date: 2022/09/23
 * 收藏列表
 */
class CollectListAct : BaseAct<CollectListLayoutBinding, CollectListActPresent>(),
    CollectListContact.ICollectListView, OnRefreshLoadMoreListener {

    private val collectAdapter by lazy {
        CollectAdapter(R.layout.collect_item_layout)
    }

    override fun getLayout(): Int {
        return R.layout.collect_list_layout
    }

    override fun getPresenter(): CollectListActPresent {
        return CollectListActPresent(this)
    }

    override fun initV() {
        mTitleBar.setCentreTitle(getString(R.string.me_collect_str))
        collect_srf.setOnRefreshLoadMoreListener(this)
        collect_recycle.layoutManager = RvUtil.vertical(this)
        collect_recycle.adapter = collectAdapter
        mPresenter.getCollectListData(AppConstant.refresh)
        collectAdapter.setOnItemClickListener { _, _, position ->
            ActRouter.ofWebViewX5Act(
                mContext,
                collectAdapter.getItem(position).link,
                collectAdapter.getItem(position).title
            )
        }
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun collectModel(type: String, bean: MutableList<CollectEntity>) {
        when (type) {
            AppConstant.refresh -> collectAdapter.setList(bean)
            AppConstant.loadMore -> collectAdapter.addData(bean)
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter.getCollectListData(AppConstant.refresh)
        collect_srf.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPresenter.getCollectListData(AppConstant.loadMore)
        collect_srf.finishLoadMore()
    }
}