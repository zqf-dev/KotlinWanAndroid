package com.zqf.kotlinwanandroid.interceptor

import android.util.Log

/**
 * Author: zqf
 * Date: 2022/08/30
 * 登录拦截器管理类
 */
object LoginInterceptChain {

    private var index: Int = 0

    private val interceptors by lazy(LazyThreadSafetyMode.NONE) {
        ArrayList<Interceptor>(2)
    }

    //默认初始化Login的拦截器
    private val loginIntercept = LoginInterceptor()

    // 执行拦截器。
    fun process() {
        Log.e("TAG", "当前的index---$index")
        Log.e("TAG", "ArrayList的indices---" + interceptors.indices)
        Log.e("TAG", "ArrayList的size---" + interceptors.size)
        if (interceptors.isEmpty()) return
        when (index) {
            in interceptors.indices -> {
                val interceptor = interceptors[index]
                index++
                interceptor.intercept(this)
            }
            interceptors.size -> {
                Log.e("TAG", "clear")
                clearAllInterceptors()
            }
        }
    }

    // 添加一个拦截器
    fun addInterceptor(interceptor: Interceptor): LoginInterceptChain {
        // 默认添加Login判断的拦截器
        if (!interceptors.contains(loginIntercept)) {
            interceptors.add(loginIntercept)
        }
        // 外面也可调用添加多个拦截器
        if (!interceptors.contains(interceptor)) {
            interceptors.add(interceptor)
        }
        return this
    }

    //通过登录放行判断拦截器
    fun loginFinished() {
        if (interceptors.contains(loginIntercept) && interceptors.size > 1) {
            loginIntercept.loginfinished()
        }
    }

    //清除全部的拦截器
    private fun clearAllInterceptors() {
        index = 0
        interceptors.clear()
    }
}