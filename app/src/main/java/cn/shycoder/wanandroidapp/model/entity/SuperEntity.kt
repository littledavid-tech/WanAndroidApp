package cn.shycoder.wanandroidapp.model.entity

/**
 * Created by ITSoftware on 11/7/2018.
 */
open class SuperEntity<T> {
    var data: T? = null
    var errorCount: Int = 0
    var errorMsg: String = ""
}