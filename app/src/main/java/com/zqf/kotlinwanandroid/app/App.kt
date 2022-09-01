package com.zqf.kotlinwanandroid.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.hjq.toast.ToastUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import com.zqf.kotlinwanandroid.R
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.cookie.CookieStore
import rxhttp.wrapper.ssl.HttpsUtils
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession


/**
 * Author: zqf
 * Date: 2021/10/09
 */
@SuppressLint("ResourceAsColor")
class App : Application() {


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        netHttpConfig()
        initX5WebView()
        initOther()
    }

    private fun initX5WebView() {
        // 在调用TBS初始化、创建WebView之前进行如下配置
        // 在调用TBS初始化、创建WebView之前进行如下配置
        val map = hashMapOf<String, Any>()
        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(map)
        val callback = object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {

            }

            override fun onViewInitFinished(p0: Boolean) {
                Log.e("TAG", "内核加载成功p0$p0")
            }
        }
        QbSdk.initX5Environment(this, callback)
    }

    private fun netHttpConfig() {
        //默认的配置
        //RxHttpPlugins.init(RxHttpPlugins.getOkHttpClient()).setDebug(true)
        //自定义配置
        val sslParams = HttpsUtils.getSslSocketFactory()
        val client = OkHttpClient.Builder()
            .cookieJar(CookieStore())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            .hostnameVerifier { _, _ -> true }
            .build()
        RxHttpPlugins.init(client)
    }

    private fun initOther() {
        ToastUtils.init(this);
    }

    //静态代码块
    companion object {
        lateinit var instance: App

        fun getApp(): App {
            return instance
        }

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(DefaultRefreshHeaderCreator { context, layout ->
                //设置刷新头的背景色
                layout.setPrimaryColorsId(R.color.white)
                return@DefaultRefreshHeaderCreator ClassicsHeader(context)
            })
            SmartRefreshLayout.setDefaultRefreshFooterCreator(DefaultRefreshFooterCreator { context, layout ->
                //设置滑动到底部自动加载更多
                layout.setEnableAutoLoadMore(false)
                return@DefaultRefreshFooterCreator ClassicsFooter(context).setDrawableSize(20f)
            })
        }
    }
}