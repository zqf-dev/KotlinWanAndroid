package com.zqf.kotlinwanandroid.widget;

import android.content.Context;

import com.zqf.kotlinwanandroid.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Author: zqf
 * Date: 2022/08/19
 */
public class OutLoginPopup extends BasePopupWindow {
    public OutLoginPopup(Context context) {
        super(context);
        setContentView(R.layout.outlogin_dialog_layout);
        findViewById(R.id.cancel_btn).setOnClickListener(v -> dismiss());
    }
}
