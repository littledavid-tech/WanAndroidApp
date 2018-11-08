package cn.shycoder.wanandroidapp.presenter.contract


interface HomeContract {

    interface HomeView : BaseContract.BaseView {
        /**
         * 将Fragment显示出来
         * */
        fun showFragment()
    }

    interface HomePresenter : BaseContract.BasePresenter<HomeView> {
        /**
         * 创建主页的Fragment
         * */
        fun createHomeFragment()

        override fun onDestroy() {
            view = null
        }
    }
}