package com.zqf.kotlinwanandroid.http


/**
 * Author: zqf
 * Date: 2021/11/05
 * wanAndroid 返回的数据格式
 */
class Response<T> {
    val errorCode = 0
    val errorMsg: String = ""
    val data: T? = null
}