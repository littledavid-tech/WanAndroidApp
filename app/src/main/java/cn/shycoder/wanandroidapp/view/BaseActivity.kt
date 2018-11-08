package cn.shycoder.wanandroidapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.shycoder.wanandroidapp.presenter.contract.BaseContract

/**
 * Created by ITSoftware on 11/6/2018.
 */
abstract class BaseActivity<T : BaseContract.BasePresenter<*>> : AppCompatActivity() {

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

    abstract fun getLayoutResId(): Int

    abstract fun doInit()

    abstract fun createPresenter(): T
}