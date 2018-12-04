package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.model.api.ProjectService
import cn.shycoder.wanandroidapp.presenter.contract.ProjectListContract
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ProjectListPresenterImpl(val cid: Int)
    : BasePresenter<ProjectListContract.View>(), ProjectListContract.Presenter {

    private var mCurrentPageIndex = 1
    private var mTotalPageCount = 1

    override fun loadMore() {
        if (mCurrentPageIndex > mTotalPageCount) {
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
     * 加载项目列表的项目信息
     * @param isRefresh 是否是刷新操作
     * */
    private fun loadData(isRefresh: Boolean) {
        Logger.i("Load data from network")
        ProjectService
                .instance
                .getProjectList(this.mCurrentPageIndex, this.cid)
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
                    Logger.e("Meet a error in RxJava")
                }, {
                }, {
                    this.addDisposable(it)
                })
    }
}