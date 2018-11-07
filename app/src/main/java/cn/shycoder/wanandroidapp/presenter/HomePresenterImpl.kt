package cn.shycoder.wanandroidapp.presenter

import cn.shycoder.wanandroidapp.presenter.contract.HomeContract


class HomePresenterImpl(override var view: HomeContract.HomeView?)
    : HomeContract.HomePresenter<HomeContract.HomeView> {

    override fun createHomeFragment() {

    }

}