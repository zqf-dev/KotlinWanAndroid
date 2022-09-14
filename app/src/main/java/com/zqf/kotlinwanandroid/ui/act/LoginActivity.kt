package com.zqf.kotlinwanandroid.ui.act

import android.view.View
import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.LoginLayoutBinding
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.ui.contact.LoginContact
import com.zqf.kotlinwanandroid.ui.presenter.LoginPresenter
import com.zqf.kotlinwanandroid.util.KVUtil
import kotlinx.android.synthetic.main.login_layout.*

/**
 * Author: zqf
 * Date: 2022/08/30
 */
class LoginActivity : BaseAct<LoginLayoutBinding, LoginPresenter>(), LoginContact.ILoginView {
    //页面类型 0 登录（默认） 1 注册
    var vType = 0
    override fun getLayout(): Int {
        return R.layout.login_layout
    }

    override fun getPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun initV() {
        mTitleBar.setCentreTitle(getString(R.string.login_btn_str))
        login_top_tip_tv.text = getString(R.string.login_top_tip_str)
        login_btn.setOnClickListener {
            mPresenter.loginServer(
                vType,
                login_ac_ed.text.toString(),
                login_psd_ed.text.toString(),
                login_rpsd_ed.text.toString()
            )
        }
        register_tv.setOnClickListener {
            vTypeChange(vType)
        }
    }

    private fun vTypeChange(vT: Int) {
        when (vT) {
            0 -> {
                vType = 1
                login_rpsd_ed.visibility = View.VISIBLE
                register_tv.text = "已有账号，去登录"
                login_btn.text = "注册"
            }
            1 -> {
                vType = 0
                register_tv.text = "没有账号？去注册"
                login_rpsd_ed.visibility = View.GONE
                login_btn.text = "登录"
            }
        }
        login_ac_ed.setText("")
        login_psd_ed.setText("")
        login_rpsd_ed.setText("")
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun loginsuc() {
        KVUtil.encode(AppConstant.isLogin, true)
        finish()
    }

    override fun registsuc() {
        vType = 1
        vTypeChange(vType)
        ToastUtils.show("注册成功")
    }
}
