package cn.shycoder.wanandroidapp.presenter.contract

import cn.shycoder.wanandroidapp.model.entity.Article

/**
 * Created by ITSoftware on 11/7/2018.
 */
interface ArticleContract {
    interface ArticleView : BaseRecyclerViewContract.BaseRecyclerView {

        fun loadedData(list: List<Article>)

        fun refreshedData(list: List<Article>)

    }

    interface ArticlePresenter : BaseRecyclerViewContract.BaseRecyclerPresenter<ArticleView> {

    }
}