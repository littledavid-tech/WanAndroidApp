package cn.shycoder.wanandroidapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.shycoder.wanandroidapp.presenter.contract.BaseContract

/**
 * 所有Fragment的基类
 */
abstract class BaseFragment<T : BaseContract.Presenter<*>> : Fragment() {

    /**
     * 解绑黄油刀的对象
     * */
    private lateinit var mUnbinder: Unbinder

    /**
     * 当前的Presenter对象
     * */
    var presenter: T? = null

//    private var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        if (mView != null) {
//            val parent = mView!!.parent as ViewGroup?
//            parent?.removeView(mView)
//            return mView
//        }
//        mView = inflater.inflate(getLayoutResId(), container, false)
//        mUnbinder = ButterKnife.bind(this, mView!!)
//        return mView
        val view = inflater.inflate(getLayoutResId(), container, false)
        mUnbinder = ButterKnife.bind(this, view!!)
        return view
    }

    override fun onResume() {
        super.onResume()
        this.presenter = createPresenter()
        doInit()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder.unbind()
        presenter?.onDestroy()
        presenter = null
    }

    abstract fun getLayoutResId(): Int

    abstract fun doInit()

    /**
     * 创建与当前View相绑定的Presenter
     * */
    abstract fun createPresenter(): T
}