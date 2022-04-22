package com.zqf.kotlinwanandroid.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.zqf.kotlinwanandroid.ui.act.fg.SysTabFragment

/**
 * Author: zqf
 * Date: 2021/12/15
 */
class SysTabAdapter(fm: FragmentManager, listStr: MutableList<String>) :
    FragmentStatePagerAdapter(fm) {
    val list = listStr
    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return list[position]
    }

    override fun getItem(position: Int): Fragment {
        return SysTabFragment(position)
    }
}