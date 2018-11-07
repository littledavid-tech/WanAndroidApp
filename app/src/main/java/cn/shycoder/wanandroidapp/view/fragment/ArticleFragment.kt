package cn.shycoder.wanandroidapp.view.fragment

import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract

/**
 * Created by ITSoftware on 11/7/2018.
 */
class ArticleFragment()
    : BaseRecyclerViewFragment<ArticleContract.ArticlePresenter>(),
        ArticleContract.ArticleView {

    override fun <T> loadedData(list: List<T>) {

    }

    override fun <T> refreshedData(list: List<T>) {

    }

    override fun <T> createPresenter(): T {
        TODO()
    }

}