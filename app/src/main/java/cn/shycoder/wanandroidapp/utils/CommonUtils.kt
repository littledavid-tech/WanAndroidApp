package cn.shycoder.wanandroidapp.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

/**
 * Created by ShyCoder on 11/26/2018.
 */
object CommonUtils {

    /**
     * 检查隐式启动的Activity是否存在
     * @param intent 隐式启动Activity的Intent
     * @return 如果存在返回true 否则 反之
     * */
    fun isActivityExisted(context: Context, intent: Intent): Boolean {
        return context.packageManager.queryIntentActivities(intent
                , PackageManager.MATCH_DEFAULT_ONLY).isNotEmpty()
    }
}