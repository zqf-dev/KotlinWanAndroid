package com.zqf.kotlinwanandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zqf.kotlinwanandroid.R;
import com.zqf.kotlinwanandroid.util.DisplayUtil;

/**
 * @author xiongwenjie
 * @time 2021/4/9 16:01
 * @des
 * @updateAuthor $
 * @updateDate $
 * @updateDes 原生界面统一管理标题栏
 */
public class TitleBar extends ConstraintLayout {

    private TextView mCentreTitleTv;
    private ImageView mBackIv;
    private ImageView mHomeIv;
    private ImageView mRightIv;
    private TextView mRightTitle;
    private int mScreenWidthPixels;

    public TitleBar(Context context) {
        super(context);
        init(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mScreenWidthPixels = DisplayUtil.getScreenWidthPixels(context);
        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this, true);
        mBackIv = findViewById(R.id.headbar_lef_igv);
        mHomeIv = findViewById(R.id.headbar_home_page_igv);
        mCentreTitleTv = findViewById(R.id.headbar_title_tv);
        mRightIv = findViewById(R.id.headbar_right_igv);
        mRightTitle = findViewById(R.id.headbar_right_tv);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        //背景色
        int bgResourceId = typedArray.getResourceId(R.styleable.TitleBar_title_background, -1);
        if (bgResourceId != -1) {
            setBackgroundResource(bgResourceId);
        }
        //返回按钮
        int backImageId = typedArray.getResourceId(R.styleable.TitleBar_back_image, 0);
        if (backImageId != 0)
            mBackIv.setImageResource(backImageId);
        //主页按钮
        boolean showHomeIv = typedArray.getBoolean(R.styleable.TitleBar_show_home_image, false);
        mHomeIv.setVisibility(showHomeIv ? View.VISIBLE : View.GONE);
        int homeImageId = typedArray.getResourceId(R.styleable.TitleBar_home_image, 0);
        if (homeImageId != 0)
            mHomeIv.setImageResource(homeImageId);
        //中间标题
        boolean isShowCentreTitle = typedArray.getBoolean(R.styleable.TitleBar_show_centre_title, true);
        mCentreTitleTv.setVisibility(isShowCentreTitle ? View.VISIBLE : View.GONE);
        String centreTitle = typedArray.getString(R.styleable.TitleBar_centre_title);
        if (!TextUtils.isEmpty(centreTitle))
            mCentreTitleTv.setText(centreTitle);
        int titleColor = typedArray.getResourceId(R.styleable.TitleBar_centre_title_color, -1);
        if (titleColor != -1) {
            mCentreTitleTv.setTextColor(getResources().getColor(titleColor));
        }
        //右侧按钮
        boolean isShowRightImg = typedArray.getBoolean(R.styleable.TitleBar_show_right_image, false);
        mRightIv.setVisibility(isShowRightImg ? View.VISIBLE : View.GONE);
        int rightImageId = typedArray.getResourceId(R.styleable.TitleBar_right_image, 0);
        if (rightImageId != 0)
            mRightIv.setImageResource(rightImageId);

        boolean isShowRightTitle = typedArray.getBoolean(R.styleable.TitleBar_show_right_title, false);
        mRightTitle.setVisibility(isShowRightTitle ? View.VISIBLE : View.GONE);
        String rightTitle = typedArray.getString(R.styleable.TitleBar_right_title_txt);
        if (!TextUtils.isEmpty(rightTitle)) {
            mRightTitle.setText(rightTitle);
        }

        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TitleBar.class.getSimpleName(), "onLayout执行");
        adjustTitlePosition();
    }

    private void adjustTitlePosition() {
        // 标题自动缩进、居中
        int[] centreTitleLocation = new int[2];
        mCentreTitleTv.getLocationOnScreen(centreTitleLocation);

        int[] leftTitleLocation = new int[2];
        // 主页按钮显示，则左边距以主页按钮为基准，否则按返回按钮为基准
        int leftPaddging = 0;
        if (mHomeIv.getVisibility() == View.VISIBLE) {
            mHomeIv.getLocationOnScreen(leftTitleLocation);
            leftPaddging = leftTitleLocation[0] + mHomeIv.getMeasuredWidth();
        } else if (mBackIv.getVisibility() == View.VISIBLE) {
            mBackIv.getLocationOnScreen(leftTitleLocation);
            leftPaddging = leftTitleLocation[0] + mBackIv.getMeasuredWidth();
        }
        if (centreTitleLocation[0] > leftPaddging) {
            leftPaddging = 0;
        }

        // 右侧ImageView显示则右边距以右侧ImageView为基准，否则看右侧标题是否显示，是则以右标题为基准
        int[] rightTitleLocation = new int[2];
        if (mRightIv.getVisibility() == View.VISIBLE) {
            mRightIv.getLocationOnScreen(rightTitleLocation);
        } else if (mRightTitle.getVisibility() == View.VISIBLE) {
            mRightTitle.getLocationOnScreen(rightTitleLocation);
        }
        int rightPadding = rightTitleLocation[0];
        if ((centreTitleLocation[0] + mCentreTitleTv.getMeasuredWidth()) < rightPadding) {
            rightPadding = 0;
        }
        if (rightPadding > 0) {
            rightPadding = mScreenWidthPixels - rightPadding;
        }

        mCentreTitleTv.setPadding(leftPaddging, 0, rightPadding, 0);
    }

    //返回点击事件
    public void setBackIvClickListener(NoDoubleClickListener onClickListener) {
        mBackIv.setOnClickListener(onClickListener);
    }

    //主页点击事件
    public void setHomeIvClickListener(NoDoubleClickListener onClickListener) {
        mHomeIv.setOnClickListener(onClickListener);
    }

    //右侧按钮点击事件
    public void setRightIvClickListener(NoDoubleClickListener onClickListener) {
        mRightIv.setOnClickListener(onClickListener);
    }

    public void setBackIvImage(int resourceId) {
        mBackIv.setImageResource(resourceId);
    }

    public void setBackIvVisible(int resourceId) {
        mBackIv.setVisibility(resourceId);
    }

    public void setHomeIvImage(int resourceId) {
        mHomeIv.setImageResource(resourceId);
    }

    public void setHomeIvVisible(boolean visible) {
        mHomeIv.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightIvImage(int resourceId) {
        mRightIv.setImageResource(resourceId);
    }

    public void setRightIvVisible(boolean visible) {
        mRightIv.setVisibility(visible ? View.VISIBLE : View.GONE);
        requestLayout();
    }

    //中间标题
    public void setCentreTitle(String centreTitle) {
        if (!TextUtils.isEmpty(centreTitle)) {
            mCentreTitleTv.setText(centreTitle);
        }
    }

    public void setCentreTitleColor(String colorStr) {
        if (!TextUtils.isEmpty(colorStr)) {
            mCentreTitleTv.setTextColor(Color.parseColor(colorStr));
        }
    }


    public void setRightTitleVisible(boolean visible) {
        mRightTitle.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightTitle(String rightTitle) {
        if (!TextUtils.isEmpty(rightTitle)) {
            mRightTitle.setText(rightTitle);
            invalidate();
        }
    }

    public void setRightTitleClickListener(NoDoubleClickListener listener) {
        if (listener != null) {
            mRightTitle.setOnClickListener(listener);
        }
    }

}
