package com.zqf.kotlinwanandroid.ui.web

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * Author: zqf
 * Date: 2021/12/31
 */
class BaseWebClient : WebViewClient() {

    val urlInterceptList = arrayListOf(
        "www.taobao.com",
        "www.jd.com",
    )

    @SuppressLint("SetJavaScriptEnabled")
    fun initWebConfig(context: Activity, tbWebview: WebView) {
        val settings: WebSettings = tbWebview.settings
        //视频为了避免闪屏和透明问题
        context.window.setFormat(PixelFormat.TRANSLUCENT)
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.displayZoomControls = false //显示原生的缩放控件
        settings.blockNetworkImage = false//解决图片不显示
        settings.loadsImagesAutomatically = true //支持自动加载图片
        settings.defaultTextEncodingName = "utf-8"//设置编码格式
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true    //将图片调整大小适合 webView 的大小
        settings.loadWithOverviewMode = true   //缩放至屏幕的大小
        settings.blockNetworkLoads = false
        tbWebview.overScrollMode = View.OVER_SCROLL_ALWAYS
        //文件权限
        settings.allowFileAccess = true
        settings.setAllowFileAccessFromFileURLs(true)
        settings.setAllowUniversalAccessFromFileURLs(true)
        settings.allowContentAccess = true
        //缓存相关
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        if (!isUseWebCache()) {
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }

    //是否启用缓存，默认缓存 true 不缓存 false
    private fun isUseWebCache(): Boolean {
        return true
    }

    private fun isInterceptHost(host: String): Boolean {
        if (TextUtils.isEmpty(host)) return false
        for (interceptUrl in urlInterceptList) {
            if (interceptUrl == host) {
                return true
            }
        }
        return false
    }

    override fun onLoadResource(p0: WebView?, p1: String?) {
        super.onLoadResource(p0, p1)
        Log.e("TAG", "onLoadResource---->>$p1")
    }

    override fun onPageFinished(p0: WebView?, p1: String?) {
        super.onPageFinished(p0, p1)
        Log.e("TAG", "onPageFinished---->>$p1")
    }

    override fun onReceivedSslError(p0: WebView?, p1: SslErrorHandler?, p2: SslError?) {
        //super.onReceivedSslError(p0, p1, p2)
        //忽略SSL证书错误
        p1?.proceed();
    }
}