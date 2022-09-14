package com.zqf.kotlinwanandroid.ui.act.fg

import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.hjq.toast.ToastUtils
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.constant.AppConstant
import com.zqf.kotlinwanandroid.databinding.MefgLayoutBinding
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.interceptor.LoginInterceptChain
import com.zqf.kotlinwanandroid.interceptor.LoginNextInterceptor
import com.zqf.kotlinwanandroid.ui.act.AboutActivity
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
                //登录成功的执行收藏
                //...
                Log.e("TAG", "刷新用户中心数据")
            }).process()
        }
        meAdapter.setOnItemClickListener { adapter, view, position ->
            when (meAdapter.getItem(position).title) {
                "关于我们" -> ActRouter.ofAct(mContext, AboutActivity().javaClass)
                "系统设置" -> ActRouter.ofAct(mContext, SysSetActivity().javaClass)
                "退出登录" -> {
                    if (!KVUtil.decode(AppConstant.isLogin, false)) {
                        ToastUtils.show("当前未登录")
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
    }

    override fun meRecycleData(meRecycleEntity: MutableList<MeRecycleEntity>) {
        meAdapter.setList(meRecycleEntity)
    }

    //退出成功
    override fun outsuc() {
        KVUtil.encode(AppConstant.isLogin, false)
        Log.e("TAG", "退出成功")
    }
}