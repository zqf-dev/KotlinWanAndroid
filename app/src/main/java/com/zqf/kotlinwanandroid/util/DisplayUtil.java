package com.zqf.kotlinwanandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 *
 */
public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param scale   （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param scale    （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenWidthDip(Context context) {
        return px2dip(context, getScreenWidthPixels(context));
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenHeightDip(Context context) {
        return px2dip(context, getScreenHeightPixels(context));
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenWidthPixels(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenHeightPixels(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        window.addFlags(2);
        window.setAttributes(lp);
    }

    /**
     * 获得屏幕尺寸
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenMeasure(Context context) {
        if (context == null) {
            return null;
        }
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    /**
     * 计算状态栏高度高度
     * getStatusBarHeight
     *
     * @return
     */
    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public static int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取手机状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatuBarHeight(Context context) {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 作者：拉丁吴
     * 链接：https://juejin.im/post/5bb5c4e75188255c72285b54
     * 来源：掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param activity
     * @param onNavigationStateListener
     */
    public static void isNavigationBarExist(Activity activity,
                                            final OnNavigationStateListener onNavigationStateListener) {
        if (activity == null) {
            return;
        }
        final int height = getNavigationHeight(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            activity.getWindow().getDecorView().setOnApplyWindowInsetsListener((v, windowInsets) -> {
                boolean isShowing = false;
                int b = 0;
                if (windowInsets != null) {
                    b = windowInsets.getSystemWindowInsetBottom();
                    isShowing = (b == height);
                }
                if (onNavigationStateListener != null && b <= height) {
                    onNavigationStateListener.onNavigationState(isShowing, b);
                }
                return windowInsets;
            });
        }
    }

    /**
     * 底部状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getNavigationHeight(Context activity) {
        if (activity == null) {
            return 0;
        }
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        int height = 0;
        if (resourceId > 0) {
            //获取NavigationBar的高度
            height = resources.getDimensionPixelSize(resourceId);
        }
        return height;
    }

    public interface OnNavigationStateListener {
        void onNavigationState(boolean isShowing, int height);
    }

    /**
     * 华为是否有刘海屏
     *
     * @param context
     * @return
     */
    public static boolean hasNotchAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util" +
                    ".HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtHuawei Exception");
        } finally {
            return ret;
        }
    }

    /**
     * 华为刘海屏高度
     *
     * @param context //获取刘海尺寸：width、height
     *                //int[0]值为刘海宽度 int[1]值为刘海高度
     * @return
     */
    public static int[] getNotchSizeAtHuawei(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "getNotchSizeAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "getNotchSizeAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "getNotchSizeAtHuawei Exception");
        } finally {
            return ret;
        }
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角
    public static final int VIVO_NOTCH_WIDTH_DP = 100;
    public static final int VIVO_NOTCH_HEIGHT_DP = 27;

    /**
     * vivo是否有刘海屏
     * vivo不提供接口获取刘海尺寸，目前vivo的刘海宽为100dp,高为27dp。
     *
     * @param context
     * @return
     */
    public static boolean hasNotchAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }


    public static final int OPPO_NOTCH_WIDTH_PX = 324;
    public static final int OPPO_NOTCH_HEIGHT_PX = 80;

    /**
     * 判断oppo是否有刘海屏
     * <p>
     * OPPO不提供接口获取刘海尺寸，目前其有刘海屏的机型尺寸规格都是统一的。不排除以后机型会有变化。
     * 其显示屏宽度为1080px，高度为2280px。刘海区域则都是宽度为324px, 高度为80px。
     *
     * @param context
     * @return
     */
    public static boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen" +
                ".heteromorphism");
    }

    /**
     * 小米是否有刘海屏
     * <p>
     */
    public static boolean hasNotchAtXiaoMi(Context context) {
        //        return SystemProperties.getInt("ro.miui.notch", 0) == 1;
        Integer ret;
        try {
            ClassLoader cl = context.getClassLoader();
            @SuppressWarnings("rawtypes")
            Class SystemProperties = cl.loadClass("android.os.SystemProperties");
            //参数类型
            @SuppressWarnings("rawtypes")
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = int.class;
            Method getInt = SystemProperties.getMethod("getInt", paramTypes);
            //参数
            Object[] params = new Object[2];
            params[0] = "ro.miui.notch";
            params[1] = new Integer(0);
            ret = (Integer) getInt.invoke(SystemProperties, params);
        } catch (IllegalArgumentException iAE) {
            ret = 0;
        } catch (Exception e) {
            ret = 0;
            //TODO
        }
        return ret == 1;
    }

    /**
     * MIUI 10 新增了获取刘海宽和高的方法，需升级至8.6.26开发版及以上版本。
     *
     * @param context
     * @return
     */
    public static int getNotchHeightAtXiaoMiPx(Context context) {
        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return getStatuBarHeight(context);
    }

    /**
     * MIUI 10 新增了获取刘海宽和高的方法，需升级至8.6.26开发版及以上版本。
     *
     * @param context
     * @return
     */
    public static int getNotchWidthAtXiaoMiPx(Context context) {
        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 获取刘海屏高度
     *
     * @param context
     * @return
     */
    public static int getNotchHeight(Context context) {
        int notchHeight = 0;
        if (BackgrounderUtil.isHuawei()) {
            //华为
            boolean hasNotchAtHuawei = DisplayUtil.hasNotchAtHuawei(context);
            if (hasNotchAtHuawei) {
                int[] notchSizeAtHuawei = DisplayUtil.getNotchSizeAtHuawei(context);
                notchHeight = notchSizeAtHuawei[1];
            }
        } else if (BackgrounderUtil.isXiaomi()) {
            //小米
            if (DisplayUtil.hasNotchAtXiaoMi(context)) {
                notchHeight = DisplayUtil.getNotchHeightAtXiaoMiPx(context);
            }
        } else if (BackgrounderUtil.isVIVO()) {
            //vivo
            boolean hasNotchAtVivo = DisplayUtil.hasNotchAtVivo(context);
            if (hasNotchAtVivo) {
                notchHeight = DisplayUtil.dip2px(context, DisplayUtil.VIVO_NOTCH_HEIGHT_DP);
            }
        } else if (BackgrounderUtil.isOPPO()) {
            //oppo
            if (DisplayUtil.hasNotchAtOPPO(context)) {
                notchHeight = DisplayUtil.OPPO_NOTCH_HEIGHT_PX;
            }
        }

        return notchHeight;
    }

}