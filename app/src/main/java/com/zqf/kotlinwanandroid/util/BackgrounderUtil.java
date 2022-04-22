package com.zqf.kotlinwanandroid.util;

import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import java.lang.reflect.Method;

/**
 * @author Administrator
 * @time 2020/2/14 11:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 * 电池免优化、自启动权限工具类
 * <p>
 * https://juejin.im/post/5dfaeccbf265da33910a441d
 */
public class BackgrounderUtil {
    /**
     * 是否开启了电池免优化
     *
     * @param context
     * @return
     */
    public static boolean isIgnoringBatteryOptimizations(Context context) {
        if (context == null) {
            return false;
        }
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                isIgnoring = powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
            }
        }
        return isIgnoring;
    }

    /**
     * 弹框请求电池免优化白名单
     *
     * @param context
     */
    public static void requestIgnoreBatteryOptimizations(FragmentActivity context,
                                                         int requestCode) {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自启动设置
     *
     * @param context
     */
    public static void autoStartSetting(Context context) {
        if (context == null) {
            return;
        }
        if (isHuawei()) {
            goHuaweiSetting(context);
        } else if (isXiaomi()) {
            goXiaomiSetting(context);
        } else if (isOPPO()) {
            goOPPOSetting(context);
        } else if (isVIVO()) {
            goVIVOSetting(context);
        } else if (isMeizu()) {
            goMeizuSetting(context);
        } else if (isLeTV()) {
            goLetvSetting(context);
        } else if (isSamsung()) {
            goSamsungSetting(context);
        } else if (isSmartisan()) {
            goSmartisanSetting(context);
        } else {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= 9) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setClassName("com.android.settings",
                        "com.android.settings.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName",
                        context.getPackageName());
            }
            try {
                context.startActivity(intent);
            } catch (Exception e) {//抛出异常就直接打开设置页面
                Intent intent1 = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent1);
            }
        }
    }

    /**
     * 跳转到指定应用的首页
     */
    private static void showActivity(Context context, @NonNull String packageName) {
        if (context == null) {
            return;
        }
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    private static void showActivity(Context context, @NonNull String packageName,
                                     @NonNull String activityDir) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean isHuawei() {
        if (Build.BRAND == null) {
            return false;
        } else {
            return Build.BRAND.toLowerCase().equals("huawei") || Build.BRAND.toLowerCase().equals("honor");
        }
    }

    /**
     * 跳转华为手机管家的启动管理页：操作步骤：应用启动管理 -> 关闭应用开关 -> 打开允许自启动
     *
     * @param context
     */
    private static void goHuaweiSetting(Context context) {
        try {
            showActivity(context, "com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity(context, "com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }

    public static boolean isXiaomi() {
        return Build.BRAND != null && (Build.BRAND.toLowerCase().equals("xiaomi")||
                Build.BRAND.toLowerCase().equals("redmi"));
    }

    /**
     * 跳转小米安全中心的自启动管理页面：操作步骤：授权管理 -> 自启动管理 -> 允许应用自启动
     *
     * @param context
     */
    private static void goXiaomiSetting(Context context) {
        if (context == null) {
            return;
        }
        showActivity(context, "com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }

    /**
     * 判断小米系统，是否已获取后台弹出界面权限
     *
     * @param context
     * @return
     */
    public static boolean canBackgroundStartMiui(Context context) {
        try {
        AppOpsManager ops = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int op = 10021; // >= 23
            // ops.checkOpNoThrow(op, uid, packageName)
            Method method = ops.getClass().getMethod("checkOpNoThrow",
                    int.class, int.class, String.class);
            Integer result = (Integer) method.invoke(ops, op, Process.myUid(),
                    context.getPackageName());
            return result == AppOpsManager.MODE_ALLOWED;
        } catch (Exception e) {
            Log.e(BackgrounderUtil.class.getSimpleName(), e.getMessage());
        }
        return false;
    }

    public static boolean isOPPO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("oppo");
    }

    /**
     * 跳转 OPPO 手机管家：操作步骤：权限隐私 -> 自启动管理 -> 允许应用自启动
     */
    private static void goOPPOSetting(Context context) {
        try {
            showActivity(context, "com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity(context, "com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity(context, "com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity(context, "com.coloros.safecenter");
                }
            }
        }
    }

    public static boolean isVIVO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("vivo");
    }

    /**
     * 跳转 VIVO 手机管家：操作步骤：权限管理 -> 自启动 -> 允许应用自启动
     *
     * @param context
     */
    private static void goVIVOSetting(Context context) {
        showActivity(context, "com.iqoo.secure");
    }

    public static boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("meizu");
    }

    /**
     * 跳转魅族手机管家：操作步骤：权限管理 -> 后台管理 -> 点击应用 -> 允许后台运行
     *
     * @param context
     */
    private static void goMeizuSetting(Context context) {
        showActivity(context, "com.meizu.safe");
    }

    private static boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("samsung");
    }

    /**
     * 跳转三星智能管理器：操作步骤：自动运行应用程序 -> 打开应用开关 -> 电池管理 -> 未监视的应用程序 -> 添加应用
     *
     * @param context
     */
    private static void goSamsungSetting(Context context) {
        try {
            showActivity(context, "com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity(context, "com.samsung.android.sm");
        }
    }

    private static boolean isLeTV() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("letv");
    }

    /**
     * 跳转乐视手机管家：操作步骤：自启动管理 -> 允许应用自启动
     *
     * @param context
     */
    private static void goLetvSetting(Context context) {
        showActivity(context, "com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity");
    }

    private static boolean isSmartisan() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("smartisan");
    }

    /**
     * 跳转手机管理：操作步骤：权限管理 -> 自启动权限管理 -> 点击应用 -> 允许被系统启动
     *
     * @param context
     */
    private static void goSmartisanSetting(Context context) {
        showActivity(context, "com.smartisanos.security");
    }


}
