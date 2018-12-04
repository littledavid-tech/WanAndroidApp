package cn.shycoder.wanandroidapp.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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

    /**
     * 使用系统浏览器打开网页
     * @param url 需要打开的超链接
     * @return 如果没有浏览器则返回false
     * */
    fun openWebSiteInBrowser(context: Context, url: String): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (isActivityExisted(context, intent)) {
            context.startActivity(intent)
            return true
        }
        return false
    }

    /**
     * 判断异常是否是网络异常
     * */
    fun isNetworkException(throwable: Throwable): Boolean {
        return throwable is SocketTimeoutException || throwable is UnknownHostException
    }
}