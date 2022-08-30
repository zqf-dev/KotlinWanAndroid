package com.zqf.kotlinwanandroid.interceptor

import android.util.Log
import com.zqf.kotlinwanandroid.app.App
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.util.ActRouter

/**
 * Author: zqf
 * Date: 2022/08/30
 */
class LoginInterceptor : LoginInterceptImpl() {

    override fun intercept(chain: LoginInterceptChain) {
        super.intercept(chain)
        if (AppConstant.isLogin) {
            //如果已经登录 -> 放行, 转交给下一个拦截器
            LoginInterceptChain.process()
        } else {
            //如果未登录 -> 去登录页面
            Log.e("TAG", "拦截器。。。")
            ActRouter.ofLoginAct(App.instance.applicationContext)
        }
    }

    fun loginfinished() {
        //如果登录完成，调用方法放行到下一个拦截器
        LoginInterceptChain.process()
    }
}