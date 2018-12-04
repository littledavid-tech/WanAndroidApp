package cn.shycoder.wanandroidapp.presenter.base

import cn.shycoder.wanandroidapp.utils.CommonUtils
import io.reactivex.disposables.Disposable

/**
 * Created by ShyCoder on 12/4/2018.
 */
open class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T> {
    override var view: T? = null
    private var mDisposeableList: MutableList<Disposable>? = null

    /**
     * 处理发生的异常
     * */
    override fun disposeException(throwable: Throwable) {
        if (CommonUtils.isNetworkException(throwable))
            view?.onNetworkException(throwable)
        else
            view?.onUnknownException(throwable)
    }

    /**
     * 添加RxJava 的Disposable对象到list中
     * 让Presenter销毁的时候一并断开连接
     * */
    fun addDisposable(disposable: Disposable) {
        if (mDisposeableList == null) {
            mDisposeableList = mutableListOf(disposable)
        } else {
            mDisposeableList!!.add(disposable)
        }
    }

    open override fun onDestroy() {
        view = null
        mDisposeableList?.forEach {
            it.dispose()
        }
        mDisposeableList = null
    }
}