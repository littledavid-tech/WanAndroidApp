package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.HomeArticleService
import cn.shycoder.wanandroidapp.model.api.UserService
import cn.shycoder.wanandroidapp.presenter.contract.MyCollectedArticleListContract
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShyCoder on 11/28/2018.
 */
class MyCollectedArticlePresenterImpl() : MyCollectedArticleListContract.Presenter {

    override var view: MyCollectedArticleListContract.View? = null
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
     * 加载收藏的文章
     * @param isRefresh 是否是刷新操作
     * */
    private fun loadData(isRefresh: Boolean) {
        Logger.i("Load data from network")
        UserService
                .instance
                .getCollectedArticleList(this.mCurrentPageIndex)
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
                            it.printStackTrace()
                            Logger.e("Meet a error in RxJava")
                        },
                        {

                        },
                        {
                            disposable = it
                        })
    }

    /**
     * 添加一个站外文章
     * */
    override fun addExternalLink() {

    }

    /**
     * 移除收藏的文章
     * */
    override fun removeCollectedArticle(articleId: Int, position: Int) {
        UserService
                .instance
                .cancelCollect(articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.removedCollectedArticle(position)
                }, {
                    it.printStackTrace()
                })
    }


}