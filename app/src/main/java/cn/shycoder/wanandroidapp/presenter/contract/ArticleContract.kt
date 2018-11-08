package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.Article

/**
 * Created by ITSoftware on 11/7/2018.
 */
interface ArticleContract {
    interface View : BaseRecyclerViewContract.View {

        fun loadedData(list: List<Article>)

        fun refreshedData(list: List<Article>)

    }

    interface Presenter : BaseRecyclerViewContract.Presenter<View> {

    }
}