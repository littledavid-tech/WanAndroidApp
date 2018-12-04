package cn.shycoder.wanandroidapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.base.BaseContract
import cn.shycoder.wanandroidapp.utils.ToastUtils

/**
 * 所有Fragment的基类
 */
abstract class BaseFragment<T : BaseContract.Presenter<*>>
    : Fragment(),
        BaseContract.View {

    /**
     * 解绑黄油刀的对象
     * */
    private lateinit var mUnbinder: Unbinder

    /**
     * 当前的Presenter对象
     * */
    var presenter: T? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutResId(), container, false)
        mUnbinder = ButterKnife.bind(this, view!!)
        doInit()
        return view
    }

    override fun onResume() {
        super.onResume()
        this.presenter = createPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder.unbind()
        presenter?.onDestroy()
        presenter = null
    }

    abstract fun getLayoutResId(): Int

    abstract fun doInit()

    override fun onNetworkException(throwable: Throwable) {
        ToastUtils.show(R.string.app_networw_exception)
    }

    override fun onUnknownException(throwable: Throwable) {
        ToastUtils.show(R.string.app_unkown_exception)
    }

    /**
     * 创建与当前View相绑定的Presenter
     * */
    abstract fun createPresenter(): T
}