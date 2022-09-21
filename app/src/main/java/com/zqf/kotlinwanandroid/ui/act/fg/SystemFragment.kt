package com.zqf.kotlinwanandroid.ui.act.fg

import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseFg
import com.zqf.kotlinwanandroid.databinding.SystemfgLayoutBinding
import com.zqf.kotlinwanandroid.ui.adapter.SysTabAdapter
import com.zqf.kotlinwanandroid.ui.contact.SystemFgContact
import com.zqf.kotlinwanandroid.ui.presenter.SystemFgPresenter
import kotlinx.android.synthetic.main.systemfg_layout.*

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class SystemFragment : BaseFg<SystemfgLayoutBinding, SystemFgPresenter>(),
    SystemFgContact.SystemFgView {

    private val tabTitle = arrayListOf("体系", "导航")

    override fun getLayout(): Int {
        return R.layout.systemfg_layout
    }

    override fun getPresenter(): SystemFgPresenter {
        return SystemFgPresenter(this)
    }

    override fun initV() {
        for (data in tabTitle.iterator()) {
            sys_tab.addTab(sys_tab.newTab().setText(data))
        }
        sys_vp.adapter = SysTabAdapter(childFragmentManager, tabTitle)
        sys_tab.setupWithViewPager(sys_vp)
        sys_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}