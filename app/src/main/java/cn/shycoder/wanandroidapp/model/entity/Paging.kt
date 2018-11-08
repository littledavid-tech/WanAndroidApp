package cn.shycoder.wanandroidapp.model.entity

/**
 * Created by ITSoftware on 11/7/2018.
 */
class Paging<T>() {
    var curpage: Int = 1
    var datas: List<T>? = null
    var offset: Int = 0
    var over: Boolean = false
    var pageCount = 1
    var size: Int = 20
    var total: Int = 0
}