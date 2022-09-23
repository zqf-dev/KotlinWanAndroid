package com.zqf.kotlinwanandroid.ui.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.CollectEntity

/**
 * Author: zqf
 * Date: 2022/09/23
 */
class CollectAdapter(layoutId: Int) : BaseQuickAdapter<CollectEntity, BaseViewHolder>(layoutId) {
    override fun convert(holder: BaseViewHolder, item: CollectEntity) {
        if (TextUtils.isEmpty(item.author)) {
            holder.setText(R.id.collect_item_author_tv, "匿名")
        } else {
            holder.setText(R.id.collect_item_author_tv, item.author)
        }
        holder.setText(R.id.collect_item_date_tv, item.niceDate)
            .setText(R.id.collect_item_title_tv, item.title)
            .setText(R.id.collect_item_type_tv, item.chapterName)
    }
}