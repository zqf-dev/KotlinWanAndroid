package com.zqf.kotlinwanandroid.ui.act.fg

import android.widget.TextView
import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.app.App
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.MefgLayoutBinding
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.entity.PersonInfoEntity
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.interceptor.LoginNextInterceptor
import com.zqf.kotlinwanandroid.ui.act.AboutActivity
import com.zqf.kotlinwanandroid.ui.act.CollectListAct
import com.zqf.kotlinwanandroid.ui.act.SysSetActivity
import com.zqf.kotlinwanandroid.ui.adapter.MeAdapter
import com.zqf.kotlinwanandroid.ui.contact.MeFgContact
import com.zqf.kotlinwanandroid.ui.presenter.MeFgPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.KVUtil
import com.zqf.kotlinwanandroid.util.RvUtil
import com.zqf.kotlinwanandroid.widget.OutLoginPopup
import kotlinx.android.synthetic.main.mefg_layout.*

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class MeFragment : BaseFg<MefgLayoutBinding, MeFgPresenter>(), MeFgContact.MeFgView {
    private val meAdapter by lazy {
        MeAdapter(R.layout.me_recycle_item)
    }

    override fun getLayout(): Int {
        return R.layout.mefg_layout
    }

    override fun getPresenter(): MeFgPresenter {
        return MeFgPresenter(this)
    }

    override fun initV() {
        me_ll.layoutParams.height = AppConstant.contentHeight
        mPresenter.getMeRecycleData()
        me_recycle.layoutManager = RvUtil.vertical(mContext)
        me_recycle.adapter = meAdapter
        me_srf.setEnableLoadMore(false)
        me_srf.setOnRefreshListener {
            me_srf.finishRefresh()
        }
        me_hportrait_iv.setOnClickListener {
            LoginInterceptChain.addInterceptor(LoginNextInterceptor {
                mPresenter.getPersonInfo()
            }).process()
        }
        meAdapter.setOnItemClickListener { adapter, view, position ->
            when (meAdapter.getItem(position).title) {
                "我的收藏" -> ActRouter.ofAct(mContext, CollectListAct().javaClass)
                "关于我们" -> ActRouter.ofAct(mContext, AboutActivity().javaClass)
                "系统设置" -> ActRouter.ofAct(mContext, SysSetActivity().javaClass)
                "退出登录" -> {
                    if (!KVUtil.decode(AppConstant.isLogin, false)) {
                        ToastUtils.show("当前还未登录!")
                    } else {
                        val out = OutLoginPopup(mContext)
                        out.findViewById<TextView>(R.id.sure_btn)
                            .setOnClickListener {
                                out.dismiss()
                                mPresenter.outLogin()
                            }
                        out.showPopupWindow()
                    }
                }
            }
        }
        me_nickname_tv.text = getString(R.string.me_please_login_str)
        if (KVUtil.decode(AppConstant.isLogin, false)) mPresenter.getPersonInfo()
    }

    override fun meRecycleData(meRecycleEntity: MutableList<MeRecycleEntity>) {
        meAdapter.setList(meRecycleEntity)
    }

    override fun outSuccess() {
        KVUtil.encode(AppConstant.isLogin, false)
        loginOutStatus(getString(R.string.me_please_login_str), -1)
    }

    override fun personInfo(info: PersonInfoEntity) {
        loginOutStatus(info.userInfo.nickname, info.coinInfo.coinCount)
    }

    private fun loginOutStatus(nickname: String, coinCount: Int) {
        me_nickname_tv.text = nickname
        val itemData = meAdapter.getItem(0)
        if (KVUtil.decode(AppConstant.isLogin) && coinCount != -1) {
            itemData.title = "我的积分（$coinCount）"
        } else {
            itemData.title = "我的积分"
        }
        meAdapter.setData(0, itemData)
    }
}