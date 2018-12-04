package cn.shycoder.wanandroidapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.BindString
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.shycoder.wanandroidapp.R
import cn.shycoder.wanandroidapp.presenter.contract.BaseContract
import cn.shycoder.wanandroidapp.utils.ToastUtils

abstract class BaseActivity<T : BaseContract.Presenter<*>> :
        AppCompatActivity(),
        BaseContract.View {

    private lateinit var mUnbinder: Unbinder
    var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(getLayoutResId())
        //加载黄油刀
        this.mUnbinder = ButterKnife.bind(this)
        doInit()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder.unbind()
    }

    override fun onNetworkException(throwable: Throwable) {
        ToastUtils.show(R.string.app_networw_exception)
    }

    override fun onUnknownException(throwable: Throwable) {
        ToastUtils.show(R.string.app_unkown_exception)
    }

    abstract fun getLayoutResId(): Int

    open fun doInit() {
        presenter = createPresenter()
    }

    abstract fun createPresenter(): T
}