package com.zqf.kotlinwanandroid.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.WxArticleEntity

/**
 * Author: zqf
 * Date: 2021/12/28
 */
class OfficialTabRecycleAdapter(layoutResId: Int) :
    BaseQuickAdapter<WxArticleEntity, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: WxArticleEntity) {
        holder.setText(R.id.official_recycle_item_author, item.author)
        holder.setText(R.id.official_recycle_item_date, item.niceDate)
        holder.setText(R.id.official_recycle_item_title, item.title)
        holder.setText(
            R.id.official_recycle_item_type,
            item.superChapterName + "-" + item.chapterName
        )
    }
}