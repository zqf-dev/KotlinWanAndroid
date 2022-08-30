package com.zqf.kotlinwanandroid.ui.act

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptor
import com.zqf.kotlinwanandroid.interceptor.LoginNextInterceptor

/**
 * Author: zqf
 * Date: 2022/08/30
 */
class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginInterceptChain.loginFinished()
    }
}