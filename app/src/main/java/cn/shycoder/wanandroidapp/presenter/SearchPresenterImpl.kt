package cn.shycoder.wanandroidapp.presenter

import android.content.Context
import cn.shycoder.wanandroidapp.model.api.SearchService
import cn.shycoder.wanandroidapp.presenter.contract.SearchContract
import cn.shycoder.wanandroidapp.view.activity.SearchResultActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShyCoder on 12/3/2018.
 */
class SearchPresenterImpl : SearchContract.Presenter {

    override var view: SearchContract.View? = null
    override var disposable: Disposable? = null

    override fun loadHotkey() {
        SearchService.instance
                .getHotKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.view?.loadedHotkey(it.data!!)
                }, {
                    it.printStackTrace()
                })
    }

    override fun disposeSearchEvent(context: Context, key: String) {
        SearchResultActivity.show(context, key)
    }
}