package com.zqf.kotlinwanandroid.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.zqf.kotlinwanandroid.entity.SysTabTreeEntity
import com.zqf.kotlinwanandroid.ui.act.LoginActivity
import com.zqf.kotlinwanandroid.ui.web.WebViewX5Activity

/**
 * Author: zqf
 * Date: 2021/10/09
 */
class ActRouter {

    companion object {
        //普通跳转至activity
        fun ofAct(activity: Activity?, cls: Class<Any>) {
            activity?.startActivity(Intent(activity, cls))
        }

        //携带序列化参数跳转【Parcelable】
        fun ofActParams(packageContext: Context, cls: Class<Any>, data: Any) {
            val intent = Intent(packageContext, cls)
            if (data is SysTabTreeEntity) {
                intent.putExtra("data", data)
            }
            packageContext.startActivity(Intent(packageContext, cls))
        }

        fun ofActParams(
            packageContext: Context,
            cls: Class<Any>,
            hashMap: HashMap<String, String>
        ) {
            packageContext.startActivity(Intent(packageContext, cls)
                .apply {
                    if (hashMap.isNotEmpty()) {
                        for ((key, value) in hashMap) {
                            putExtra(key, value)
                        }
                    }
                })
        }

        //统一跳转至WebView
        fun ofWebViewX5Act(packageContext: Context, linkUrl: String, linkUrlTitle: String) {
            val intent = Intent(packageContext, WebViewX5Activity::class.java)
            intent.putExtra("linkUrl", linkUrl)
            intent.putExtra("linkUrlTitle", linkUrlTitle)
            packageContext.startActivity(intent)
        }

        //统一跳转至登录
        fun ofLoginAct(packageContext: Context) {
            val intent = Intent(packageContext, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            packageContext.startActivity(intent)
        }
    }
}