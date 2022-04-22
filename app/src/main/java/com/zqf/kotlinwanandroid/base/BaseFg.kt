package com.zqf.kotlinwanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.classic.common.MultipleStatusView
import com.zqf.kotlinwanandroid.R

/**
 * Author: zqf
 * Date: 2021/10/08
 * 基类BaseFg
 */
abstract class BaseFg<VB : ViewDataBinding, P : BasePresenter<out IBaseView>> : Fragment() {

    lateinit var mContext: FragmentActivity
    lateinit var mPresenter: P
    lateinit var mDataBinding: ViewDataBinding
    lateinit var multipleStatusView: MultipleStatusView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = requireActivity()
        multipleStatusView = MultipleStatusView(mContext)
        mPresenter = getPresenter()
        lifecycle.addObserver(mPresenter)
        if (getLayout() > 0) {
            mDataBinding = DataBindingUtil.inflate(layoutInflater, getLayout(), container, false)
            val contentView = mDataBinding.root
            multipleStatusView.addView(contentView)
            return multipleStatusView
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initV()
    }

    abstract fun getLayout(): Int

    abstract fun getPresenter(): P

    abstract fun initV()

    open fun showLoading() {
        multipleStatusView.showLoading(
            R.layout.custom_loading_layout,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        )
    }

    open fun showContent() {
        multipleStatusView.showContent()
    }

    open fun showEmpty() {
        multipleStatusView.showEmpty()
    }

    open fun showNoNetWork() {
        multipleStatusView.showNoNetwork()
    }

    open fun showError() {
        multipleStatusView.showError()
    }
}