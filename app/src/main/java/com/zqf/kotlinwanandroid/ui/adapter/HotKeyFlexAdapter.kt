package com.zqf.kotlinwanandroid.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.HotKeyEntity

/**
 * Author: zqf
 * Date: 2022/01/19
 */
class HotKeyFlexAdapter(resLayoutId: Int) :
    BaseQuickAdapter<HotKeyEntity, BaseViewHolder>(resLayoutId) {
    override fun convert(holder: BaseViewHolder, item: HotKeyEntity) {
        val tv = holder.getView<TextView>(R.id.tv_test_content)
        tv.text = item.name
        val params = tv.layoutParams
        if (params is FlexboxLayoutManager.LayoutParams) {
            val flexboxLp = tv.layoutParams as FlexboxLayoutManager.LayoutParams
            flexboxLp.flexBasisPercent = -1f
        }
    }
}