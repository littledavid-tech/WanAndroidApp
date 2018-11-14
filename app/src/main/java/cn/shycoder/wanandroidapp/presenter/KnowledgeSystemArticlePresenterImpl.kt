package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.KnowledgeSystemArticleService
import cn.shycoder.wanandroidapp.presenter.contract.KnowledgeSystemArticleContract
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 知识体系下的文章的Presenter
 * @param cid 文章的分类id
 * */
class KnowledgeSystemArticlePresenterImpl(val cid: Int) : KnowledgeSystemArticleContract.Presenter {

    override var view: KnowledgeSystemArticleContract.View? = null
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

    private fun loadData(isRefresh: Boolean) {
        Logger.i("Load knowledge system articles from network")

        KnowledgeSystemArticleService
                .instance
                .getArticles(mCurrentPageIndex, this.cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            Logger.i("Loaded knowledge system data!")
                            this.mTotalPageCount = it.data?.pageCount!!
                            Logger.i("Current Page:${this.mCurrentPageIndex} " +
                                    "Total Page Count${this.mTotalPageCount}")
                            if (isRefresh)
                                view?.refreshedData(it.data!!.datas!!)
                            else
                                view?.loadedData(it.data!!.datas!!)
                        },
                        {
                            Logger.e("Meet a error in knowledge system!")
                            it.printStackTrace()
                        },
                        {
                        },
                        {
                            disposable = it
                        })
    }
}