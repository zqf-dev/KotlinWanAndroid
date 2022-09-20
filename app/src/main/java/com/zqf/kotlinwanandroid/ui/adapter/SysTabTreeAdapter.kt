package com.zqf.kotlinwanandroid.ui.adapter

import android.graphics.Color
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.SysTabTreeEntity
import com.zqf.kotlinwanandroid.ui.act.fg.SystemDetails
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.CommTools


/**
 * Author: zqf
 * Date: 2021/12/31
 */
class SysTabTreeAdapter(layoutResId: Int) :
    BaseQuickAdapter<SysTabTreeEntity, BaseViewHolder>(layoutResId) {
    override fun convert(holder: BaseViewHolder, item: SysTabTreeEntity) {
        holder.setText(R.id.item_title_tv, item.name)
        val recyclerView = holder.getView<RecyclerView>(R.id.flex_recycle)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.alignItems = AlignItems.FLEX_START
        recyclerView.layoutManager = layoutManager
        val flexBoxAdapter = FlexBoxAdapter(R.layout.flow_item, item.children)
        recyclerView.adapter = flexBoxAdapter
        flexBoxAdapter.addChildClickViewIds(R.id.tv_test_content)
        flexBoxAdapter.setOnItemChildClickListener { adapter, view, position ->
            //子元素跳转至详情页
            ActRouter.ofActParams(context, SystemDetails().javaClass, item)
        }
    }

    internal class FlexBoxAdapter(
        layoutResId: Int,
        data: MutableList<SysTabTreeEntity.ChildrenBean>
    ) :
        BaseQuickAdapter<SysTabTreeEntity.ChildrenBean, BaseViewHolder>(layoutResId, data) {
        override fun convert(holder: BaseViewHolder, item: SysTabTreeEntity.ChildrenBean) {
            val tv = holder.getView<TextView>(R.id.tv_test_content)
            tv.text = item.name
            tv.setTextColor(Color.parseColor(CommTools.colorValueNative()))
            val params = tv.layoutParams
            if (params is FlexboxLayoutManager.LayoutParams) {
                val flexboxLp = tv.layoutParams as FlexboxLayoutManager.LayoutParams
                flexboxLp.flexBasisPercent = -1f
            }
        }
    }
}