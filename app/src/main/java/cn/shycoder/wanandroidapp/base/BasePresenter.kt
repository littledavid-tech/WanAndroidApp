package cn.shycoder.wanandroidapp.base

/**
 * Created by ShyCoder on 11/7/2018.
 */
interface BasePresenter {

    /**
     * 将View被释放的时候调用此方法进行回收资源操作
     * */
    fun onDestroy()
}