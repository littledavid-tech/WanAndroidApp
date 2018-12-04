package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.UserService
import cn.shycoder.wanandroidapp.presenter.contract.MyCollectedArticleListContract
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MyCollectedArticlePresenterImpl() :
        BasePresenter<MyCollectedArticleListContract.View>(), MyCollectedArticleListContract.Presenter {
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
                .subscribe({
                    Logger.i("Loaded data in presenter!")
                    this.mTotalPageCount = it.data?.pageCount!!
                    Logger.i("Current Page:${this.mCurrentPageIndex} " +
                            "Total Page Count${this.mTotalPageCount}")
                    if (isRefresh)
                        view?.refreshedData(it.data!!.datas!!)
                    else
                        view?.loadedData(it.data!!.datas!!)
                }, {
                    this.disposeException(it)
                    it.printStackTrace()
                    Logger.e("Meet a error in RxJava")
                }, {

                }, {
                    this.addDisposable(it)
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
    override fun removeCollectedArticle(collectedArticleId: Int, originId: Int, position: Int) {
        UserService
                .instance
                .cancelCollect(collectedArticleId, originId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (0 != it.errorCode) {
                        this.view?.removeCollectedArticleFailed()
                    } else {
                        view?.removedCollectedArticle(position)
                    }
                }, {
                    it.printStackTrace()
                })
    }


}