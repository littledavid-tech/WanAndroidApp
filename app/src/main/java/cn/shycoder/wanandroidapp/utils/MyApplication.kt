package cn.shycoder.wanandroidapp.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
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
    }
}