package com.zqf.kotlinwanandroid.ui.act

import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.AboutLayoutBinding
import com.zqf.kotlinwanandroid.entity.MeRecycleEntity
import com.zqf.kotlinwanandroid.ui.adapter.MeAdapter
import com.zqf.kotlinwanandroid.ui.contact.AboutContact
import com.zqf.kotlinwanandroid.ui.presenter.AboutActPresenter
import com.zqf.kotlinwanandroid.util.AppUtils
import com.zqf.kotlinwanandroid.util.RvUtil
import kotlinx.android.synthetic.main.about_layout.*

/**
 * Author: zqf
 * Date: 2022/02/10
 */
class AboutActivity : BaseAct<AboutLayoutBinding, AboutActPresenter>(), AboutContact.AboutView {

    private val meAdapter by lazy {
        MeAdapter(R.layout.me_recycle_item)
    }

    override fun getLayout(): Int {
        return R.layout.about_layout
    }

    override fun getPresenter(): AboutActPresenter {
        return AboutActPresenter(this)
    }

    override fun initV() {
        mTitleBar.setCentreTitle("关于我们")
        version_tv.text = AppUtils.getAppVersionName(mContext)
        mPresenter.getAboutRecycleData()
        about_recycle.layoutManager = RvUtil.vertical(mContext)
        about_recycle.adapter = meAdapter
        meAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> mPresenter.webRout(mContext, "https://www.wanandroid.com")
                1 -> mPresenter.webRout(mContext, "https://github.com/zqf-dev/KotlinWanAndroid")
            }
        }
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun meRecycleData(meRecycleEntity: MutableList<MeRecycleEntity>) {
        meAdapter.setList(meRecycleEntity)
    }
}