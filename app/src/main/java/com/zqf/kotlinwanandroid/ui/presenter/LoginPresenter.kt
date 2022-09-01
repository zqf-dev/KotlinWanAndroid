package com.zqf.kotlinwanandroid.ui.presenter

import android.text.TextUtils
import android.util.Log
import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptor
import com.zqf.kotlinwanandroid.ui.contact.LoginContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2022/09/01
 */
class LoginPresenter : BasePresenter<LoginContact.ILoginView>(), LoginContact.Presenter {

    override fun onDestroy() {

    }

    fun loginServer(ac: String, psd: String) {
        if (TextUtils.isEmpty(ac)
            || TextUtils.isEmpty(psd)
        ) {
            ToastUtils.show("账号和密码不能为空")
            return
        }
        mCoroutineScope.launch {
            RxHttp.postForm(API.login)
                .add("username", ac).add("password", psd)
                .toResponse<String>()
                .awaitResult {
                    Log.e("TAG", "it----$it")
                }.onFailure {
                    ToastUtils.show(it.message)
                }
        }
    }
}