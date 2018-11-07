package cn.shycoder.wanandroidapp.presenter

import android.support.v7.widget.RecyclerView
import cn.shycoder.wanandroidapp.base.BasePresenter
import cn.shycoder.wanandroidapp.base.BaseView

/**
 * Created by ShyCoder on 11/7/2018.
 */
interface BaseRecyclerViewContract {

    interface BaseRecyclerView : BaseView {

        fun <T> loadMoreData(list: List<T>)

        fun <T> refreshData(list: List<T>)

    }

    interface BaseRecyclerPresenter : BasePresenter {
        fun loadMore()

        fun refreshData()
    }

}