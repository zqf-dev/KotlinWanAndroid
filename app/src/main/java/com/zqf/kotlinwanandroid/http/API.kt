package com.zqf.kotlinwanandroid.http

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * Author: zqf
 * Date: 2021/11/05
 */
object API {
    /**
     * @JvmStatic 静态方法
     * @JvmField 静态变量
     * 效果是使其调用方式和java一样  类.变量  类.方法名
     */
    @JvmField
    @DefaultDomain
    val baseUrl = "https://www.wanandroid.com"

    //首页广告
    const val bannerUrl = "/banner/json"

    //首页文章
    fun articleUrl(page: Int): String {
        return "/article/list/${page}/json"
    }

    //置顶文章
    const val ARTICLETOPURL = "/article/top/json"

    //体系
    const val TREEJSON = "/tree/json"

    //导航
    const val NAVJSON = "/navi/json"

    //公众号
    const val WXARTICLE = "/wxarticle/chapters/json"

    //查看某个公众号历史数据
    fun wxArticleList(id: Int, page: Int): String {
        return "/wxarticle/list/${id}/${page}/json"
    }

    //查看问答数据
    fun wendaList(page_size: Int): String {
        return "/wenda/list/${page_size}/json"
    }

    //搜索的热词
    const val HOTKEY = "/hotkey/json"

    fun queryHotKey(page: Int): String {
        return "/article/query/${page}/json"
    }

    //登录
    const val login = "/user/login"

    //注册
    const val register = "/user/register"

    //退出登录
    const val outLogin = "/user/logout/json"

    //个人信息接口
    const val personInfo = "/user/lg/userinfo/json"

    //收藏列表
    fun collectList(page: Int): String {
        return "/lg/collect/list/${page}/json"
    }
}