package com.zqf.kotlinwanandroid.interceptor

import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.util.KVUtil

/**
 * Author: zqf
 * Date: 2022/08/30
 * 登录下一步拦截器
 */
class LoginNextInterceptor(private val action: () -> Unit) : LoginInterceptImpl() {

    override fun intercept(chain: LoginInterceptChain) {
        super.intercept(chain)
        if (KVUtil.decode(AppConstant.isLogin,false)) {
            //如果已经登录执行当前的任务
            action()
        }
        mChain?.process()
    }
}