package cn.shycoder.wanandroidapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

object NetworkUtils {
    /***
     * 判断当前网络环境是否处于WiFi状态
     * @return 当前返回值是否处于WiFi环境
     * */
    fun isWiFi(): Boolean {
        val manager = MyApplication.context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (null != networkInfo && ConnectivityManager.TYPE_WIFI == networkInfo.type) {
            return true
        }
        return false
    }
}