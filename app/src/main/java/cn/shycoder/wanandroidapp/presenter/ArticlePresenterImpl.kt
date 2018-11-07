package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.HomeArticleService
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

/**
 * Created by ITSoftware on 11/7/2018.
 */
class ArticlePresenterImpl(override var view: ArticleContract.ArticleView?)
    : ArticleContract.ArticlePresenter {

    var currentPage = 0
    var totalPage = 1

    private var mDisposable: Disposable? = null

    override fun loadMore() {
        if (currentPage >= totalPage) {
            view?.enableLoadMore(false)
            return
        }
        loadData(false)
        currentPage++
    }

    override fun refreshData() {
        currentPage = 0
        totalPage = 1
        view?.enableLoadMore(true)
        loadData(true)
    }

    private fun loadData(isRefresh: Boolean) {
        HomeArticleService
                .instance
                .getArticles(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (isRefresh)
                                view?.refreshedData(it.data!!.datas!!)
                            else
                                view?.loadedData(it.data!!.datas!!)
                        },
                        {

                        },
                        {

                        },
                        {
                            mDisposable = it
                        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }
}