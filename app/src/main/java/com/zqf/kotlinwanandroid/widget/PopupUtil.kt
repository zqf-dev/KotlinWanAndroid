package com.zqf.kotlinwanandroid.widget

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.constant.AppConstant
import kotlinx.android.synthetic.main.view_popupwindow.view.*

/**
 * Author: zqf
 * Date: 2022/01/14
 */
class PopupUtil(activity: Activity) : PopupWindow(activity) {

    private val act = activity

    fun showPop(view: View, title: String, linkurl: String) {
        val pw = LayoutInflater.from(act).inflate(R.layout.view_popupwindow, null, false)
        pw.tv_pop_share.text = "分享"
        pw.tv_pop_browser.text = "浏览器打开"
        pw.tv_pop_collect.text = "收藏"
        contentView = pw
        width = LinearLayout.LayoutParams.WRAP_CONTENT
        height = LinearLayout.LayoutParams.WRAP_CONTENT
        setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(act, R.color.white)))
        isFocusable = true
        pw.tv_pop_share.setOnClickListener {
            val intent = Intent()
            intent.run {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    act.getString(
                        R.string.share_article_url,
                        AppConstant.wanAndroid,
                        title,
                        linkurl
                    )
                )
                type = AppConstant.CONTENT_SHARE_TYPE
                act.startActivity(Intent.createChooser(intent, "分享"))
            }
            dismiss()
        }
        pw.tv_pop_browser.setOnClickListener {
            val intent = Intent()
            intent.run {
                action = "android.intent.action.VIEW"
                data = Uri.parse(linkurl)
                act.startActivity(intent)
            }
            dismiss()
        }
        pw.tv_pop_collect.setOnClickListener {

        }
        showAsDropDown(view, view.width, 10, Gravity.BOTTOM)
    }
}