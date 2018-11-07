package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract

/**
 * Created by ITSoftware on 11/7/2018.
 */
class ArticlePresenterImpl(override var view: ArticleContract.ArticleView?)
    : ArticleContract.ArticlePresenter {

    override fun loadMore() {
    }

    override fun refreshData() {
    }
}