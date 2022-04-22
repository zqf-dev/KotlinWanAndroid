package com.zqf.kotlinwanandroid.ui.act

import android.text.TextUtils
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.SearchDetailsLayoutBinding
import com.zqf.kotlinwanandroid.entity.Article
import com.zqf.kotlinwanandroid.ui.adapter.HomeArticleAdapter
import com.zqf.kotlinwanandroid.ui.contact.SearchDetailsContact
import com.zqf.kotlinwanandroid.ui.presenter.SearchDetailsPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.search_details_layout.*


/**
 * Author: zqf
 * Date: 2022/01/19
 */
class SearchDetailsActivity : BaseAct<SearchDetailsLayoutBinding, SearchDetailsPresenter>(),
    SearchDetailsContact.SearchDetailsView, OnRefreshLoadMoreListener {
    private var k = ""
    private val detailsAdapter by lazy {
        HomeArticleAdapter(R.layout.home_rv_item)
    }

    override fun getLayout(): Int {
        return R.layout.search_details_layout
    }

    override fun getPresenter(): SearchDetailsPresenter {
        return SearchDetailsPresenter(this)
    }

    override fun initV() {
        k = intent.getStringExtra("k").toString()
        if (!TextUtils.isEmpty(k)) {
            mTitleBar.setCentreTitle(k)
            mPresenter.searchQuery(k, AppConstant.refresh)
        }
        detailsrecycle.layoutManager = RvUtil.vertical(mContext)
        detailsrecycle.adapter = detailsAdapter
        detailssrf.setOnRefreshLoadMoreListener(this)
        detailsAdapter.setOnItemClickListener { adapter, view, position ->
            ActRouter.ofWebViewX5Act(
                mContext, detailsAdapter.getItem(position).link,
                detailsAdapter.getItem(position).title
            )
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter.searchQuery(k, AppConstant.refresh)
        detailssrf.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPresenter.searchQuery(k, AppConstant.loadMore)
        detailssrf.finishLoadMore()
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun querySuccess(type: String, bean: MutableList<Article>) {
        if (type == AppConstant.refresh) {
            detailsAdapter.setList(bean)
        } else {
            detailsAdapter.addData(bean)
        }
    }
}
