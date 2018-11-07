package cn.shycoder.wanandroidapp.presenter

import android.app.Fragment

/**
 * Created by ShyCoder on 11/7/2018.
 */
class HomePresenterImpl(override var mView: HomeContract.HomeView?)
    : HomeContract.HomePresenter<HomeContract.HomeView> {

    override fun createHomeFragment() {

    }

}