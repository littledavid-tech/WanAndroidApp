package cn.shycoder.wanandroidapp.presenter.contract

import android.content.Context
import android.support.v4.app.Fragment


interface HomeContract {

    interface View : BaseContract.View {
        /**
         * 将Fragment显示出来
         * */
        fun showFragment(fragment: Fragment)
    }

    interface Presenter : BaseContract.Presenter<View> {
        /**
         * 创建主页的Fragment
         * */
        fun createHomeFragment(menuId: Int): android.support.v4.app.Fragment

        fun getFragmentMap(): Map<Int, Fragment?>

        /**
         * 处理NavigationView的点击事件
         * */
        fun disposeNavEvent(context: Context, menuId: Int)

        override fun onDestroy() {
            view = null
        }
    }
}