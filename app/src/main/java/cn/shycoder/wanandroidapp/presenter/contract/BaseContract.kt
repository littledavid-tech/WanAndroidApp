package cn.shycoder.wanandroidapp.presenter.contract

import io.reactivex.disposables.Disposable

/**
 * Created by ShyCoder on 11/8/2018.
 */
interface BaseContract {

    interface BaseView {

    }

    interface BasePresenter<T : BaseView> {

        var view: T?
        /**
         * RxJava Disposable对象
         * */
        var disposable: Disposable?

        /**
         * 将View被释放的时候调用此方法进行回收资源操作
         * */
        fun onDestroy() {
            //释放占用的View
            view = null
            //切断异步操作
            disposable?.dispose()
        }

    }
}