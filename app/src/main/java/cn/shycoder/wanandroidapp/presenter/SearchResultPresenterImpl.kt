package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.SearchService
import cn.shycoder.wanandroidapp.presenter.base.BasePresenter
import cn.shycoder.wanandroidapp.presenter.contract.SearchResultContract
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShyCoder on 12/4/2018.
 */
class SearchResultPresenterImpl(private val key: String) :
        BasePresenter<SearchResultContract.View>(), SearchResultContract.Presenter {


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
        Logger.i("Load data from network")
        SearchService
                .instance
                .searchArticle(mCurrentPageIndex, key)
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
                            this.disposeException(it)
                            it.printStackTrace()
                        },
                        {

                        },
                        {
                            this.addDisposable(it)
                        })
    }


}