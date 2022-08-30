package com.zqf.kotlinwanandroid.interceptor

/**
 * Author: zqf
 * Date: 2022/08/30
 * 登录拦截器接口
 */
interface Interceptor {

    fun intercept(chain: LoginInterceptChain)
}