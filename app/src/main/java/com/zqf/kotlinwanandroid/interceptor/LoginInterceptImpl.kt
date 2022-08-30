package com.zqf.kotlinwanandroid.interceptor

import androidx.annotation.CallSuper

/**
 * Author: zqf
 * Date: 2022/08/30
 * 基类抽象登录拦截器
 */
abstract class LoginInterceptImpl : Interceptor {

    protected var mChain: LoginInterceptChain? = null

    @CallSuper
    override fun intercept(chain: LoginInterceptChain) {
        mChain = chain
    }
}