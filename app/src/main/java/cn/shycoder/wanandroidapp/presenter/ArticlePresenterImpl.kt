package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.HomeArticleService
import cn.shycoder.wanandroidapp.model.entity.Article
import cn.shycoder.wanandroidapp.model.entity.Paging
import cn.shycoder.wanandroidapp.model.entity.SuperEntity
import cn.shycoder.wanandroidapp.presenter.contract.ArticleContract
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticlePresenterImpl
    : ArticleContract.Presenter {

    override var view: ArticleContract.View? = null
    override var disposable: Disposable? = null

    private var mCurrentPageIndex = 0
    private var mTotalPageCount = 1


    override fun loadMore() {
        if (mCurrentPageIndex >= mTotalPageCount) {
            view?.enableLoadMore(false)
            view!!.enableLoadMore(false)
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
        Logger.i("Load data from network")
        HomeArticleService
                .instance
                .getArticles(mCurrentPageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Logger.i("Loaded data in presenter!")
                            this.mTotalPageCount = it.data?.pageCount!!
                            Logger.i("Current Page:${this.mCurrentPageIndex} " +
                                    "Total Page Count${this.mTotalPageCount}")
                            if (isRefresh)
                                view?.refreshedData(it.data!!.datas!!)
                            else
                                view?.loadedData(it.data!!.datas!!)
                        },
                        {
                            Logger.e("Meet a error in RxJava")
                        },
                        {

                        },
                        {
                            disposable = it
                        })
    }

    /**
     * 加载所有的banner
     * */
    override fun loadBanner() {
        HomeArticleService
                .instance
                .getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            val banners = it.data!!
                            view!!.loadedBanner(banners)
                        },
                        {
                            it.printStackTrace()
                            Logger.e("Meet a error in RxJava")
                        })
    }
}