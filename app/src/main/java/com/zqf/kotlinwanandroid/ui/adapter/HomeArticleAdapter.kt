package com.zqf.kotlinwanandroid.ui.adapter

import android.graphics.Color
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.Article

/**
 * Author: zqf
 * Date: 2021/11/08
 */
class HomeArticleAdapter(layoutResId: Int) :
    BaseQuickAdapter<Article, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: Article) {
        if (item.type == 1) {
            //置顶文章数据
            holder.setVisible(R.id.home_rv_item_top_tv, true)
            holder.setText(R.id.home_rv_item_top_tv, R.string.article_top_str)
            holder.setTextColor(R.id.home_rv_item_top_tv, Color.RED)
            holder.setBackgroundResource(R.id.home_rv_item_top_tv, R.drawable.shape_strick_top)
            if (item.isFresh) {
                //是否最新数据
                holder.setVisible(R.id.home_rv_item_tag_tv, true)
                holder.setBackgroundResource(R.id.home_rv_item_tag_tv, R.drawable.shape_strick_top)
                holder.setTextColor(R.id.home_rv_item_tag_tv, Color.RED)
                holder.setText(R.id.home_rv_item_tag_tv, R.string.article_new_str)
            } else {
                if (item.tags.size > 0) {
                    //标签数据
                    holder.setVisible(R.id.home_rv_item_tag_tv, true)
                    holder.setBackgroundResource(
                        R.id.home_rv_item_tag_tv,
                        R.drawable.shape_strick_green_top
                    )
                    holder.setTextColor(
                        R.id.home_rv_item_tag_tv,
                        ContextCompat.getColor(context, R.color.green)
                    )
                    holder.setText(R.id.home_rv_item_tag_tv, item.tags[0].name)
                } else {
                    holder.setGone(R.id.home_rv_item_tag_tv, true)
                }
            }
        } else {
            //文章数据
            holder.setGone(R.id.home_rv_item_top_tv, true)
            if (item.isFresh) {
                //是否最新数据
                holder.setVisible(R.id.home_rv_item_tag_tv, true)
                holder.setBackgroundResource(R.id.home_rv_item_tag_tv, R.drawable.shape_strick_top)
                holder.setTextColor(R.id.home_rv_item_tag_tv, Color.RED)
                holder.setText(R.id.home_rv_item_tag_tv, R.string.article_new_str)
            } else {
                if (item.tags.size > 0) {
                    //标签数据
                    holder.setVisible(R.id.home_rv_item_tag_tv, true)
                    holder.setBackgroundResource(
                        R.id.home_rv_item_tag_tv,
                        R.drawable.shape_strick_top
                    )
                    holder.setTextColor(R.id.home_rv_item_tag_tv, Color.RED)
                    holder.setText(R.id.home_rv_item_tag_tv, item.tags[0].name)
                } else {
                    holder.setGone(R.id.home_rv_item_tag_tv, true)
                }
            }
        }
        if (item.title.contains("<em")) {
            val titleName = item.title
                .replace("<em class='highlight'>", "")
                .replace("</em>", "")
            holder.setText(R.id.home_rv_item_title_tv, titleName)
        } else {
            holder.setText(R.id.home_rv_item_title_tv, item.title)
        }
        if (TextUtils.isEmpty(item.author)) {
            holder.setText(R.id.home_rv_item_author_tv, "分享者：" + item.shareUser)
        } else {
            holder.setText(R.id.home_rv_item_author_tv, "作者：" + item.author)
        }
        holder.setText(R.id.home_rv_item_date_tv, item.niceDate)
        holder.setText(
            R.id.home_rv_item_type_tv,
            "分类：" + item.superChapterName + " / " + item.chapterName
        )
        if (item.isCollect) {
            holder.setImageResource(R.id.home_rv_item_collect_iv, R.mipmap.like)
        } else {
            holder.setImageResource(R.id.home_rv_item_collect_iv, R.mipmap.not_like)
        }
    }
}