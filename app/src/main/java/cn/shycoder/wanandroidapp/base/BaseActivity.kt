package cn.shycoder.wanandroidapp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by ITSoftware on 11/6/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var mUnbinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(getLayoutResId())
        //加载黄油刀
        this.mUnbinder = ButterKnife.bind(this)
    }

    override fun onPostResume() {
        super.onPostResume()
        doInit()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder.unbind()
    }

    abstract fun getLayoutResId(): Int

    abstract fun doInit()
}