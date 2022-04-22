package com.zqf.kotlinwanandroid.ui.act.fg

import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.OfficialTabLayoutBinding
import com.zqf.kotlinwanandroid.entity.WxArticleEntity
import com.zqf.kotlinwanandroid.ui.adapter.OfficialTabRecycleAdapter
import com.zqf.kotlinwanandroid.ui.contact.OfficialTabContact
import com.zqf.kotlinwanandroid.ui.presenter.OfficialTabPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.official_tab_layout.*

/**
 * Author: zqf
 * Date: 2021/12/28
 * 公众号单个子类的fg
 */
class OfficialTabFragment(var listId: Int) :
    BaseFg<OfficialTabLayoutBinding, OfficialTabPresenter>(),
    OfficialTabContact.OfficialTabView, OnRefreshLoadMoreListener {

    private val officialTabRecycleAdapter by lazy {
        OfficialTabRecycleAdapter(R.layout.official_tab_recycle_item)
    }

    override fun getLayout(): Int {
        return R.layout.official_tab_layout
    }

    override fun getPresenter(): OfficialTabPresenter {
        return OfficialTabPresenter(this)
    }

    override fun initV() {
        official_tab_recycle.layoutManager = RvUtil.vertical(mContext)
        official_tab_recycle.adapter = officialTabRecycleAdapter
        mPresenter.getOfficialList(listId, AppConstant.refresh)
        official_tab_srf.setOnRefreshLoadMoreListener(this)
        officialTabRecycleAdapter.setOnItemClickListener { adapter, view, position ->
            ActRouter.ofWebViewX5Act(
                mContext,
                officialTabRecycleAdapter.getItem(position).link,
                officialTabRecycleAdapter.getItem(position).title
            )
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter.getOfficialList(listId, AppConstant.refresh)
        official_tab_srf.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPresenter.getOfficialList(listId, AppConstant.loadMore)
        official_tab_srf.finishLoadMore()
    }

    override fun wxArticleModel(type: String, bean: MutableList<WxArticleEntity>) {
        if (type == AppConstant.refresh) {
            officialTabRecycleAdapter.setList(bean)
        } else {
            officialTabRecycleAdapter.addData(bean)
        }
    }
}