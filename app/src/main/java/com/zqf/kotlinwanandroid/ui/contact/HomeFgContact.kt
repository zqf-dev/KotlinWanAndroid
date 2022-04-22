package com.zqf.kotlinwanandroid.ui.contact

import com.zqf.kotlinwanandroid.base.IBaseView
import com.zqf.kotlinwanandroid.entity.Article
import com.zqf.kotlinwanandroid.entity.BannerEntity
import com.zqf.kotlinwanandroid.entity.WxArticleEntity

/**
 * Author: zqf
 * Date: 2021/10/09
 */
interface HomeFgContact {

    interface HomeFgView : IBaseView {
        fun bannerModel(bean: List<BannerEntity>)
        fun articleModel(type: String, bean: MutableList<Article>)
        fun articletopData(bean: List<Article>)
    }

    interface Presenter {

        //获取首页广告
        fun getHomeBanner()

        //首页的文章列表
        fun getHomeArticle(type: String)

        //获取置顶文章
        fun getHomeTopArticle()
    }
}