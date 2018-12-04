package cn.shycoder.wanandroidapp.presenter.base

/**
 * Created by ShyCoder on 11/8/2018.
 */
interface BaseContract {

    interface View {
        fun onNetworkException(throwable: Throwable)

        fun onUnknownException(throwable: Throwable)
    }

    interface Presenter<T : View> {

        var view: T?

        /**
         * 处理发生的异常
         * */
        fun disposeException(throwable: Throwable)

        /**
         * 将View被释放的时候调用此方法进行回收资源操作
         * */
        fun onDestroy()
    }
}