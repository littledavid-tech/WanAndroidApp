package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.Article

interface ProjectListContract {
    interface View : BaseRecyclerViewContract.View {

        fun loadedData(list: List<Article>)

        fun refreshedData(list: List<Article>)
    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {

    }
}