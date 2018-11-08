package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.HomeArticleService
import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ITSoftware on 11/7/2018.
 */
class ArticlePresenterImpl
    : ArticleContract.ArticlePresenter {

    override var view: ArticleContract.ArticleView? = null
    override var disposable: Disposable? = null

    private var mCurrentPageIndex = 0
    private var mTotalPageCount = 1

    private var mDisposable: Disposable? = null

    override fun loadMore() {
        if (mCurrentPageIndex >= mTotalPageCount) {
            view?.enableLoadMore(false)
            return
        }
        loadData(false)
        mCurrentPageIndex++
    }

    override fun refreshData() {
        mCurrentPageIndex = 0
        mTotalPageCount = 1
        view?.enableLoadMore(true)
        loadData(true)
    }

    /**
     * 加载首页文章的信息
     * @param isRefresh 是否是刷新操作
     * */
    private fun loadData(isRefresh: Boolean) {
        HomeArticleService
                .instance
                .getArticles(mCurrentPageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            this.mTotalPageCount = it.data?.pageCount!!
                            Logger.i("Current Page:${this.mCurrentPageIndex} Total Page Count${this.mTotalPageCount}")
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