package com.zqf.kotlinwanandroid.ui.act.fg

import android.util.Log
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.HomefgLayoutBinding
import com.zqf.kotlinwanandroid.entity.Article
import com.zqf.kotlinwanandroid.entity.BannerEntity
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.interceptor.LoginNextInterceptor
import com.zqf.kotlinwanandroid.ui.adapter.HomeArticleAdapter
import com.zqf.kotlinwanandroid.ui.adapter.HomeBannerAdapter
import com.zqf.kotlinwanandroid.ui.contact.HomeFgContact
import com.zqf.kotlinwanandroid.ui.presenter.HomeFgPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.home_head_layout.*
import kotlinx.android.synthetic.main.homefg_layout.*

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class HomeFragment : BaseFg<HomefgLayoutBinding, HomeFgPresenter>(), HomeFgContact.HomeFgView,
    OnRefreshLoadMoreListener {

    private val homeRvAdapter by lazy {
        HomeArticleAdapter(R.layout.home_rv_item)
    }

    override fun getLayout(): Int {
        showLoading()
        return R.layout.homefg_layout
    }

    override fun getPresenter(): HomeFgPresenter {
        return HomeFgPresenter(this)
    }

    override fun initV() {
        initRecycle()
        mPresenter.getHomeBanner()
        mPresenter.getHomeArticle(AppConstant.refresh)
    }

    private fun initRecycle() {
        val headView = layoutInflater.inflate(R.layout.home_head_layout, null, false)
        val banner = headView.findViewById<Banner<BannerEntity, HomeBannerAdapter>>(R.id.banner)
        banner.setLoopTime(5000).setBannerRound(8f)
        banner.indicator = CircleIndicator(mContext)
        recycle.layoutManager = RvUtil.vertical(mContext)
        recycle.adapter = homeRvAdapter
        homeRvAdapter.addHeaderView(headView)
        refresh.setOnRefreshLoadMoreListener(this)
        homeRvAdapter.addChildClickViewIds(R.id.home_rv_item_collect_iv)
        homeRvAdapter.setOnItemClickListener { adapter, view, position ->
            ActRouter.ofWebViewX5Act(
                mContext,
                homeRvAdapter.getItem(position).link,
                homeRvAdapter.getItem(position).title
            )
        }
        homeRvAdapter.setOnItemChildClickListener { adapter, view, position ->
            Log.e("TAG", "执行了。。。")
            LoginInterceptChain.addInterceptor(LoginNextInterceptor {
                //登录成功的执行收藏
                //...
                Log.e("TAG", "收藏成功")
            }).process()
        }
    }


    override fun bannerModel(bean: List<BannerEntity>) {
        banner.adapter = HomeBannerAdapter(bean)
        banner.setOnBannerListener(object : OnBannerListener<BannerEntity> {
            override fun OnBannerClick(data: BannerEntity, position: Int) {
                ActRouter.ofWebViewX5Act(mContext, data.url, data.title)
            }
        })
    }

    override fun articleModel(type: String, bean: MutableList<Article>) {
        showContent()
        if (type == AppConstant.refresh) {
            homeRvAdapter.setList(bean)
            mPresenter.getHomeTopArticle()
        } else {
            homeRvAdapter.addData(bean)
        }
    }

    override fun articletopData(bean: List<Article>) {
        homeRvAdapter.addData(0, bean)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter.getHomeArticle(AppConstant.refresh)
        refresh.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPresenter.getHomeArticle(AppConstant.loadMore)
        refresh.finishLoadMore()
    }
}