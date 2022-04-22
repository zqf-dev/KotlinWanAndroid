package com.zqf.kotlinwanandroid.constant

import com.zqf.kotlinwanandroid.app.App
import com.zqf.kotlinwanandroid.util.DisplayUtil

/**
 * Author: zqf
 * Date: 2021/11/26
 */
object AppConstant {

    const val refresh = "refresh"
    const val loadMore = "loadMore"
    const val wanAndroid = "玩Android"
    var contentHeight =
        DisplayUtil.getScreenHeightPixels(App.getApp().applicationContext) - DisplayUtil.dip2px(
            App.getApp().applicationContext, 60F
        )

    const val USERNAME_KEY = "username"
    const val USERNAME_COIN_COUNT = "coinCount"
    const val HAS_NETWORK_KEY = "has_network"
    const val IS_LOGIN = "isLogin"
    const val CONTENT_URL_KEY = "url"
    const val CONTENT_TITLE_KEY = "title"
    const val CONTENT_ID_KEY = "id"
    const val CONTENT_CID_KEY = "cid"
    const val CONTENT_DATA_KEY = "content_data"
    const val CONTENT_SHARE_TYPE = "text/plain"

    // 拍照
    const val IMAGE_CAPTURE = 1

    // 从相册选择
    const val IMAGE_SELECT = 2

    // 照片缩小比例
    const val SCALE = 3

    // TODO页面返回
    const val FROM_TODO = 10
    const val HEAD_PIC_PATH = "image.jpg"
    const val MY_BG_PIC_PATH = "bgImage"
    const val SCORE_UNM = "score_num"
    const val TOOLBAR_HEIGHT = 44

    //阅读历史最大存储数
    const val READ_RECORD_MAX_COUNT = 1000
}