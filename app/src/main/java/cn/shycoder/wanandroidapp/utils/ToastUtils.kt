package cn.shycoder.wanandroidapp.utils

import android.widget.Toast
import cn.shycoder.wanandroidapp.MyApplication

/**
 * 吐司工具类
 */
object ToastUtils {

    fun show(msg: String, length: Int = Toast.LENGTH_SHORT) {
        MyApplication.toast.setText(msg)
        MyApplication.toast.duration = length
        MyApplication.toast.show()
    }

    fun show(stringResId: Int, length: Int = Toast.LENGTH_SHORT) {
        MyApplication.toast.setText(stringResId)
        MyApplication.toast.duration = length
        MyApplication.toast.show()
    }

}