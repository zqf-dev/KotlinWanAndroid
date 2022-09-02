package com.zqf.kotlinwanandroid.ui.presenter

import android.text.TextUtils
import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.base.BasePresenter
import com.zqf.kotlinwanandroid.entity.LoginInfoEntity
import com.zqf.kotlinwanandroid.http.API
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.ui.contact.LoginContact
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult
import rxhttp.toResponse

/**
 * Author: zqf
 * Date: 2022/09/01
 */
class LoginPresenter(v: LoginContact.ILoginView) : BasePresenter<LoginContact.ILoginView>(),
    LoginContact.Presenter {

    init {
        attachView(v)
    }

    override fun onDestroy() {
        LoginInterceptChain.loginFinished()
    }

    override fun loginServer(vT: Int, ac: String, psd: String, repsd: String) {
        if (TextUtils.isEmpty(ac) || TextUtils.isEmpty(psd)) {
            ToastUtils.show("账号和密码不能为空")
            return
        }
        when (vT) {
            0 -> {
                mCoroutineScope.launch {
                    RxHttp.postForm(API.login)
                        .add("username", ac).add("password", psd)
                        .toResponse<LoginInfoEntity>()
                        .awaitResult {
                            getView()!!.loginsuc()
                        }.onFailure {
                            ToastUtils.show(it.message)
                        }
                }
            }
            1 -> {
                if (TextUtils.isEmpty(repsd) || psd != repsd) {
                    ToastUtils.show("两次输入的密码不对")
                    return
                }
                mCoroutineScope.launch {
                    RxHttp.postForm(API.register)
                        .add("username", ac)
                        .add("password", psd)
                        .add("repassword", repsd)
                        .toResponse<String>()
                        .awaitResult {
                            getView()!!.registsuc()
                        }.onFailure {
                            ToastUtils.show(it.message)
                        }
                }
            }
        }
    }
}