package com.zqf.kotlinwanandroid.ui.adapter

import android.widget.Switch
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.SysSettingEntity

/**
 * Author: zqf
 * Date: 2022/08/19
 */
class SysSettingAdapter : BaseMultiItemQuickAdapter<SysSettingEntity, BaseViewHolder>() {
    init {
        addItemType(0, R.layout.sys_item1_layout)
        addItemType(1, R.layout.sys_item2_layout)
    }

    override fun convert(holder: BaseViewHolder, item: SysSettingEntity) {
        holder.setText(R.id.sys_item_title, item.title)
        holder.setText(R.id.sys_item_desc, item.desc)
        when (holder.itemViewType) {
            0 -> holder.getView<Switch>(R.id.sys_item_switch).isChecked = item.isSv
            1 -> holder.setText(R.id.sys_item_right_tip, item.tip)
        }
    }
}