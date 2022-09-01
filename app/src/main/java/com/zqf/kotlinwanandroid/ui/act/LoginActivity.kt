package com.zqf.kotlinwanandroid.ui.act

import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.LoginLayoutBinding
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.ui.presenter.LoginPresenter
import kotlinx.android.synthetic.main.login_layout.*

/**
 * Author: zqf
 * Date: 2022/08/30
 */
class LoginActivity : BaseAct<LoginLayoutBinding, LoginPresenter>() {

    override fun getLayout(): Int {
        return R.layout.login_layout
    }

    override fun getPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun initV() {
        mTitleBar.setCentreTitle(getString(R.string.login_btn_str))
        login_top_tip_tv.text = getString(R.string.login_top_tip_str)
        login_btn.setOnClickListener {
            mPresenter.loginServer(
                login_ac_ed.text.toString(),
                login_psd_ed.text.toString()
            )
        }
        register_tv.setOnClickListener {

        }
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginInterceptChain.loginFinished()
    }
}
