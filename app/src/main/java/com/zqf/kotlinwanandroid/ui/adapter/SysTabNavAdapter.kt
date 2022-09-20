package com.zqf.kotlinwanandroid.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.SysTabNavEntity
import com.zqf.kotlinwanandroid.ui.web.WebViewX5Activity
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.CommTools

/**
 * Author: zqf
 * Date: 2021/12/24
 * 导航
 */
class SysTabNavAdapter(layoutResId: Int) :
    BaseQuickAdapter<SysTabNavEntity, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: SysTabNavEntity) {
        holder.setText(R.id.item_title_tv, item.name)
        val recyclerView = holder.getView<RecyclerView>(R.id.flex_recycle)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.alignItems = AlignItems.FLEX_START
        recyclerView.layoutManager = layoutManager
        val flexBoxAdapter = FlexBoxAdapter(R.layout.flow_item, item.articles)
        recyclerView.adapter = flexBoxAdapter
        flexBoxAdapter.addChildClickViewIds(R.id.tv_test_content)
        flexBoxAdapter.setOnItemChildClickListener { adapter, view, position ->
            //子元素跳转至公共的网页去
            ActRouter.ofWebViewX5Act(
                context,
                item.articles[position].link,
                item.articles[position].title
            )
        }
    }

    internal class FlexBoxAdapter(
        layoutResId: Int,
        data: MutableList<SysTabNavEntity.ArticlesBean>
    ) :
        BaseQuickAdapter<SysTabNavEntity.ArticlesBean, BaseViewHolder>(layoutResId, data) {
        override fun convert(holder: BaseViewHolder, item: SysTabNavEntity.ArticlesBean) {
            holder.setText(R.id.tv_test_content, item.title)
                .setTextColor(R.id.tv_test_content, Color.parseColor(CommTools.colorValueNative()))
        }
    }
}