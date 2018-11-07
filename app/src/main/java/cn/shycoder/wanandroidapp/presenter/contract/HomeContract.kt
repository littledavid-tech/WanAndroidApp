package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.presenter.BasePresenter
import cn.shycoder.wanandroidapp.view.BaseView

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

    interface HomePresenter<T> : BasePresenter<HomeView> {
        /**
         * 创建主页的Fragment
         * */
        fun createHomeFragment()

        override fun onDestroy() {
            view = null
        }
    }
}