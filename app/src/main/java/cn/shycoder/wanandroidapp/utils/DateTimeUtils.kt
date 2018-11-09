package cn.shycoder.wanandroidapp.utils

import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Build
import android.os.SystemClock
import java.text.SimpleDateFormat
import java.util.*


/**
 * 日期时间的工具类
 * */
object DateTimeUtils {

    private val dateFormatter: SimpleDateFormat  by lazy { SimpleDateFormat("yyyy-MM-dd") }
    private val dateTimeFormatter: SimpleDateFormat  by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm") }

    /**
     * 获取当前的时间
     * @return 当前的时间的Date对象
     * */
    fun getCurrentTime(): Date {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar.getInstance().time
        } else {
            Date(SystemClock.currentThreadTimeMillis())
        }
    }

    /**
     * 获取格式化后的日期
     * @return 返回时期格式为 yyyy-MM-dd 的日期
     * */
    fun getCurrentDate(): String {
        return dateFormatter.format(getCurrentTime())
    }

    /**
     * 获取格式化后的时间
     * @return 返回时间格式为 yyyy-MM-dd HH:mm 的字符串
     * */
    fun getCurrentDateTime(): String {
        return dateTimeFormatter.format(getCurrentTime())
    }

}