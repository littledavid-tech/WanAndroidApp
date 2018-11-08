package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.presenter.contract.HomeContract
import io.reactivex.disposables.Disposable


class HomePresenterImpl()
    : HomeContract.HomePresenter {

    override var view: HomeContract.HomeView? = null

    override var disposable: Disposable? = null

    override fun createHomeFragment() {

    }

}