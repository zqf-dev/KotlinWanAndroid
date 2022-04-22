package com.zqf.kotlinwanandroid.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.util.StatusBarUtil
import com.zqf.kotlinwanandroid.widget.NoDoubleClickListener
import com.zqf.kotlinwanandroid.widget.TitleBar

/**
 * Author: zqf
 * Date: 2021/10/08
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseAct<VB : ViewDataBinding, P : BasePresenter<out IBaseView>> :
    AppCompatActivity() {

    lateinit var mPresenter: P
    lateinit var mContext: Activity
    lateinit var mDataBinding: VB
    lateinit var mTitleBar: TitleBar
    var mNavigationBarHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mPresenter = getPresenter()
        lifecycle.addObserver(mPresenter)
        if (isFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        if (getLayout() > 0) {
            mDataBinding = DataBindingUtil.inflate(layoutInflater, getLayout(), null, false)
            val contentView = mDataBinding.root
            if (!isFullScreen() && hasTitleBar()) {
                val rootLy = LinearLayout(mContext)
                rootLy.orientation = LinearLayout.VERTICAL
                rootLy.addView(contentView)
                mTitleBar = TitleBar(mContext)
                if (configWebTitleBar()) {
                    mTitleBar.setRightIvVisible(true)
                    mTitleBar.setRightIvImage(R.mipmap.points)
                    mTitleBar.setRightIvClickListener(object : NoDoubleClickListener() {
                        override fun onNoDoubleClick(view: View?) {
                            showShareView()
                        }
                    })
                }
                mTitleBar.setBackIvClickListener(object : NoDoubleClickListener() {
                    override fun onNoDoubleClick(view: View?) {
                        finish()
                    }
                })
                rootLy.addView(mTitleBar, 0)
                setContentView(rootLy)
            } else {
                if (!isFullScreen()) {
                    StatusBarUtil.setTransparent(mContext)
                }
                setContentView(contentView)
            }
        }
        initV()
    }


    //设置title
    fun setTitleText(itemId: Int) {
        if (itemId == R.id.navigation_home) mTitleBar.setCentreTitle(getString(R.string.main_fg_home_str))
        if (itemId == R.id.navigation_system) mTitleBar.setCentreTitle(getString(R.string.main_fg_system_str))
        if (itemId == R.id.navigation_official) mTitleBar.setCentreTitle(getString(R.string.main_fg_official_str))
        if (itemId == R.id.navigation_qa) mTitleBar.setCentreTitle(getString(R.string.main_fg_qa_str))
        if (itemId == R.id.navigation_me) {
            mTitleBar.setCentreTitle(getString(R.string.main_fg_me_str))
            mTitleBar.setRightIvImage(R.mipmap.new_message)
        } else {
            mTitleBar.setRightIvImage(R.mipmap.search)
        }
    }

    //子类的xml布局
    abstract fun getLayout(): Int

    //P层
    abstract fun getPresenter(): P

    //初始化UI
    abstract fun initV()

    //是否满屏
    open fun isFullScreen(): Boolean {
        return false
    }

    //分享UI
    open fun showShareView() {}

    //WebView页面是否配置显示TitleBar元素
    open fun configWebTitleBar(): Boolean {
        return false
    }

    //是否加入TitleBar
    abstract fun hasTitleBar(): Boolean
}