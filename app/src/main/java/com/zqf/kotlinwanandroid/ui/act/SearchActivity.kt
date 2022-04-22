package com.zqf.kotlinwanandroid.ui.act

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.SearchLayoutBinding
import com.zqf.kotlinwanandroid.entity.HotKeyEntity
import com.zqf.kotlinwanandroid.ui.adapter.HotKeyFlexAdapter
import com.zqf.kotlinwanandroid.ui.adapter.SearchRecycleAdapter
import com.zqf.kotlinwanandroid.ui.contact.SearchContact
import com.zqf.kotlinwanandroid.ui.presenter.SearchPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.util.RvUtil
import com.zqf.kotlinwanandroid.widget.NoDoubleClickListener
import kotlinx.android.synthetic.main.search_layout.*

/**
 * Author: zqf
 * Date: 2022/01/17
 */
class SearchActivity : BaseAct<SearchLayoutBinding, SearchPresenter>(), SearchContact.SearchIView {
    var string = ""
    override fun getLayout(): Int {
        return R.layout.search_layout
    }

    private val searchRecycleAdapter by lazy {
        SearchRecycleAdapter(R.layout.item_search)
    }

    private val hotKeyFlexAdapter by lazy {
        HotKeyFlexAdapter(R.layout.flow_item)
    }

    override fun initV() {
        mTitleBar.visibility = View.GONE
        searchview.setLeftIvClick(object : NoDoubleClickListener() {
            override fun onNoDoubleClick(view: View?) {
                finish()
            }
        })
        searchview.sureSearch({
            string = it
        }, object : NoDoubleClickListener() {
            override fun onNoDoubleClick(view: View?) {
                if (string.isNotEmpty()) mPresenter.searchData(mContext, string)
            }
        })
        //搜索的历史
        search_recycle.layoutManager = RvUtil.vertical(this)
        search_recycle.adapter = searchRecycleAdapter
        //添加head的热门搜索词
        val headView = layoutInflater.inflate(R.layout.search_head_view, null, false)
        val mHotRecycle = headView.findViewById<RecyclerView>(R.id.hotkeyrecycle)
        mHotRecycle.layoutManager = FlexboxLayoutManager(mContext)
            .apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                alignItems = AlignItems.FLEX_START
            }
        mHotRecycle.adapter = hotKeyFlexAdapter
        hotKeyFlexAdapter.addChildClickViewIds(R.id.tv_test_content)
        hotKeyFlexAdapter.setOnItemChildClickListener { adapter, view, position ->
            //点击热门词跳转
            mPresenter.searchData(mContext, hotKeyFlexAdapter.getItem(position).name)
        }
        searchRecycleAdapter.addHeaderView(headView)
        mPresenter.hotSearchData()
    }

    override fun getPresenter(): SearchPresenter {
        return SearchPresenter(this)
    }

    override fun hasTitleBar(): Boolean {
        return true
    }

    override fun getHotKeyData(hotKeyEntity: MutableList<HotKeyEntity>) {
        hotKeyFlexAdapter.setList(hotKeyEntity)
    }
}