package cn.shycoder.wanandroidapp.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import cn.shycoder.wanandroidapp.model.entity.User
import com.orhanobut.logger.AndroidLogAdapter

/**
 * 自定义的Application类，用来获取一些全局的信息
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
        initLogger()
    }

    /**
     * 初始化Logger
     * */
    private fun initLogger() {
        com.orhanobut.logger.Logger.addLogAdapter(AndroidLogAdapter())
    }

    companion object {

        /**
         * 当前Application的Context
         * 注意，此context禁止进行Show Dialog的操作
         * */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        /**
         * 全局的Toast对象，为了避免Toast的重复弹出导致信息不能及时地显示
         * 所有的Toast操作都应该使用此Toast对象
         * */
        lateinit var toast: Toast

        /**
         * 当前APP的SP的名称
         * */
        val appSPName = "play_android_sp"

        fun getStringFromSP(key: String, defaultValue: String = ""): String {
            val sp = context.getSharedPreferences(appSPName, Context.MODE_PRIVATE)
            return sp.getString(key, defaultValue)!!
        }

        fun putStringToSP(key: String, value: String) {
            val sp = context.getSharedPreferences(appSPName, Context.MODE_PRIVATE)
            sp.edit().putString(key, value).apply()
        }

        fun removeSPByKey(key: String) {
            val sp = context.getSharedPreferences(appSPName, Context.MODE_PRIVATE)
            sp.edit().remove(key).apply()
        }

        //TODO 一个不得已的办法，通过静态变量来存储用户信息
        /**
         * 当前登录到系统中的用户
         * */
        var currentUser: User? = null
    }
}