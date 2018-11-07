package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.view.BaseView

/**
 * Created by ShyCoder on 11/7/2018.
 */
interface BasePresenter<T : BaseView> {
    var view: T?

    /**
     * 将View被释放的时候调用此方法进行回收资源操作
     * */
    fun onDestroy() {
        view = null
    }
}