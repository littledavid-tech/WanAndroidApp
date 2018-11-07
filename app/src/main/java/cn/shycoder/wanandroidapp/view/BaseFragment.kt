package cn.shycoder.wanandroidapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.shycoder.wanandroidapp.utils.MyApplication

/**
 * 所有Fragment的基类
 */
abstract class BaseFragment : Fragment() {

    private lateinit var mUnbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(MyApplication.context).inflate(getLayoutResId(), container, false)
        mUnbinder = ButterKnife.bind(view)
        return view
    }

    abstract fun getLayoutResId(): Int

    abstract fun doInit()

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder
    }
}