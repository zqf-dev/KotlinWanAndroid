package com.zqf.kotlinwanandroid.ui.adapter

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.QaListEntity

/**
 * Author: zqf
 * Date: 2021/12/30
 */
class QaAdapter(layoutId: Int) : BaseQuickAdapter<QaListEntity, BaseViewHolder>(layoutId) {
    override fun convert(holder: BaseViewHolder, item: QaListEntity) {
        holder.setText(R.id.qa_rv_item_tags_tv, item.tags[0].name)
            .setBackgroundResource(R.id.qa_rv_item_tags_tv, R.drawable.shape_strick_top)
            .setTextColor(R.id.qa_rv_item_tags_tv, ContextCompat.getColor(context, R.color.red))
            .setText(R.id.qa_rv_item_author_tv, item.author)
            .setText(R.id.qa_rv_item_date_tv, item.niceDate)
            .setText(R.id.qa_rv_item_title_tv, item.title)
            .setText(R.id.qa_rv_item_type_tv, item.superChapterName + " - " + item.chapterName)
        Glide.with(context).load(if (item.isCollect) R.mipmap.like else R.mipmap.not_like)
            .into(holder.getView(R.id.qa_rv_item_collect_iv))
    }
}