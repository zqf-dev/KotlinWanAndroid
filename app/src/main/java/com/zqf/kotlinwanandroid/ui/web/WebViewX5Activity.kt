package com.zqf.kotlinwanandroid.ui.web

import android.view.KeyEvent
import android.view.View
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.WebviewLayoutBinding
import com.zqf.kotlinwanandroid.widget.NoDoubleClickListener
import com.zqf.kotlinwanandroid.widget.PopupUtil
import kotlinx.android.synthetic.main.webview_layout.*

/**
 * Author: zqf
 * Date: 2021/12/30
 */
class WebViewX5Activity : BaseAct<WebviewLayoutBinding, WebPresenter>(),
    WebContact.IWebView {
    lateinit var linkUrl: String
    lateinit var linkUrlTitle: String
    override fun getLayout(): Int {
        return R.layout.webview_layout
    }

    override fun getPresenter(): WebPresenter {
        return WebPresenter(this)
    }

    override fun initV() {
        linkUrl = intent.getStringExtra("linkUrl").toString()
        linkUrlTitle = intent.getStringExtra("linkUrlTitle").toString()
        mTitleBar.setRightIvVisible(true)
        mTitleBar.setRightIvImage(R.mipmap.points)
        val webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                super.onReceivedTitle(p0, p1)
                mTitleBar.setCentreTitle(p1)
            }

            override fun onProgressChanged(p0: WebView?, p1: Int) {
                super.onProgressChanged(p0, p1)
                tblp.progress = p1
            }
        }
        val baseWebClient = BaseWebClient()
        baseWebClient.initWebConfig(this, tb_webview)
        tb_webview.webViewClient = baseWebClient
        tb_webview.webChromeClient = webChromeClient
        tb_webview.loadUrl(linkUrl)
        mTitleBar.setRightIvClickListener(object : NoDoubleClickListener() {
            override fun onNoDoubleClick(view: View?) {
                //分享
                PopupUtil(mContext).showPop(mTitleBar, linkUrlTitle, linkUrl)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        tb_webview.onResume()
    }

    override fun onPause() {
        super.onPause()
        tb_webview.onPause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && tb_webview != null && tb_webview.canGoBack()) {
            tb_webview.goBack()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun configWebTitleBar(): Boolean {
        return true
    }

    override fun hasTitleBar(): Boolean {
        return true
    }
}