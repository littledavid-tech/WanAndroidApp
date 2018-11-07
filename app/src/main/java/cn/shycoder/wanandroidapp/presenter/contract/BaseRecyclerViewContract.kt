package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.presenter.BasePresenter
import cn.shycoder.wanandroidapp.view.BaseView

/**
 * 具有RecyclerView的Module的接口
 */
interface BaseRecyclerViewContract {


    interface BaseRecyclerView : BaseView {

        /**
         * 将更多的数据显示到界面上
         * */
        fun <T> loadedData(list: List<T>)

        /**
         * 刷新数据
         * */
        fun <T> refreshedData(list: List<T>)

        /**
         * 是否启用加载更多
         * */
        fun enableLoadMore(boolean: Boolean)

        /**
         * RecyclerView返回列表顶部
         * */
        fun scrollToTop()
    }

    interface BaseRecyclerPresenter<T : BaseRecyclerView> : BasePresenter<T> {
        /**
         * 加载等多数据
         * */
        fun loadMore()

        /**
         * 刷新数据
         * */
        fun refreshData()
    }

}