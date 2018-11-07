package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.base.BasePresenter
import cn.shycoder.wanandroidapp.base.BaseView

/**
 * Created by ShyCoder on 11/7/2018.
 */
interface HomeContract {

    interface HomeView : BaseView {
        /**
         * 将Fragment显示出来
         * */
        fun showFragment()
    }

    interface HomePresenter<T : BaseView> : BasePresenter {

        var mView: T?

        /**
         * 创建主页的Fragment
         * */
        fun createHomeFragment()

        override fun onDestroy() {
            mView = null
        }
    }
}