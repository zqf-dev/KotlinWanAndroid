package com.zqf.kotlinwanandroid.ui.act.fg

import com.google.android.material.tabs.TabLayout
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.databinding.OfficialfgLayoutBinding
import com.zqf.kotlinwanandroid.entity.ChaptersEntity
import com.zqf.kotlinwanandroid.ui.adapter.OfficialTabAdapter
import com.zqf.kotlinwanandroid.ui.contact.OfficialFgContact
import com.zqf.kotlinwanandroid.ui.presenter.OfficialFgPresenter
import kotlinx.android.synthetic.main.officialfg_layout.*

/**
 * Author: zqf
 * Date: 2021/10/09
 * 公众号
 */
class OfficialFragment : BaseFg<OfficialfgLayoutBinding, OfficialFgPresenter>(),
    OfficialFgContact.OfficialFgView {
    override fun getLayout(): Int {
        return R.layout.officialfg_layout
    }

    override fun getPresenter(): OfficialFgPresenter {
        return OfficialFgPresenter(this)
    }

    override fun initV() {
        mPresenter.getChaptersTitle()
    }

    override fun getChaptersData(entity: MutableList<ChaptersEntity>) {
        for (data in entity) {
            official_tab.addTab(official_tab.newTab().setText(data.name))
        }
        official_vp.adapter = OfficialTabAdapter(childFragmentManager, entity)
        official_tab.setupWithViewPager(official_vp)
        official_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}