package com.zqf.kotlinwanandroid.http

/**
 * Author: zqf
 * Date: 2021/11/08
 */
class PageList<T> {

    var curPage: Int = 0
    var size: Int = 0
    var pageCount: Int = 0
    var total: Int = 0
    var over: Boolean = false
    lateinit var datas: MutableList<T>
    //lateinit var datas: List<T>
}